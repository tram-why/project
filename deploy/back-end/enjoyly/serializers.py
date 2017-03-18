from django.contrib.auth import get_user_model
from django.contrib.auth.models import User
from rest_framework import serializers
from rest_framework.fields import CharField, EmailField, Field, SerializerMethodField
from rest_framework.test import APIRequestFactory

factory = APIRequestFactory()
request = factory.get('/')

User = get_user_model()

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'id', 'first_name', 'last_name', 'email', 'friends', 'friends_requests')

class UserCreateSerializer(serializers.HyperlinkedModelSerializer):
    first_name = CharField()
    last_name = CharField()
    email = EmailField(label='Email')
    class Meta:
        model = User
        fields = ['first_name', 'last_name', 'email', 'password']
        extra_kwargs = {"password" : {"write_only": True}}

    def validate(self, data):
        email = data['email']
        user_qs = User.objects.filter(email=email)
        if user_qs.exists():
            raise serializers.ValidationError("This user has already registered")
        return data

    def create(self, validated_data):
        first_name = validated_data['first_name']
        last_name = validated_data['last_name']
        email = validated_data['email']
        password = validated_data['password']
        user_obj = User(
            username = email,
            first_name = first_name,
            last_name = last_name,
            email = email,
        )
        user_obj.set_password(password)
        user_obj.save()
        return validated_data

class UserLoginSerializer(serializers.ModelSerializer):
    email = EmailField(label= 'Email')
    class Meta:
        model = User
        fields = ['email', 'password',]
        extra_kwargs = {"password" : {"write_only": True}}

    def validate(self, data):
        user_obj = None
        email = data.get("email", None)
        password = data['password']
        user = User.objects.filter(email = email)
        user = user.exclude(email__isnull = True).exclude(email__iexact = '')
        if user.exists() and user.count()==1:
            user_obj = user.first()
        else:
            raise serializers.ValidationError("This email is not valid")
        if user_obj:
            if not user_obj.check_password(password):
                raise serializers.ValidationError("Incorrect password")

        return data