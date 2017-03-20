from django.contrib.auth import get_user_model
from django.contrib.auth.models import User
from rest_framework import serializers
from rest_framework.fields import CharField, EmailField, Field, SerializerMethodField, IntegerField
from rest_framework.test import APIRequestFactory

from enjoyly.models import Event, Location, JoinedEvent

factory = APIRequestFactory()
request = factory.get('/')

User = get_user_model()

class UserSerializer(serializers.HyperlinkedModelSerializer):
    location = SerializerMethodField()
    joined_event = SerializerMethodField()
    class Meta:
        model = User
        fields = ('url', 'id', 'first_name', 'last_name', 'email', 'location', 'joined_event')

    def get_location(self, obj):
        l_qs = Location.objects.filter(user_id=obj.id)
        location = LocationSerializer(l_qs,many=True, context={'request':request}).data
        return location

    def get_joined_event(self, obj):
        e_qs = JoinedEvent.objects.filter(user_id=obj.id)
        event = JoinedEventSerializer(e_qs, many=True, context={'request':request}).data
        return event

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

class EventSerializer(serializers.ModelSerializer):
    class Meta:
        model = Event
        fields = ('url', 'id', 'user_id', 'name', 'description', 'budget', 'numberOfPeople')

class EventCreateSerializer(serializers.ModelSerializer):
    user_id = IntegerField()
    class Meta:
        model = Event
        fields = ('user_id', 'name', 'description', 'budget', 'numberOfPeople')

    def create(self, data):
        user_id = data['user_id']
        name = data['name']
        description = data['description']
        budget = data['budget']
        numberOfPeople = data['numberOfPeople']
        event_obj = Event(
            user_id = user_id,
            name = name,
            description= description,
            budget=budget,
            numberOfPeople=numberOfPeople
        )
        event_obj.save()
        return data

class LocationSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Location
        fields = ('latitude', 'longtitude')

class JoinedEventSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = JoinedEvent
        fields = ('event_id',)