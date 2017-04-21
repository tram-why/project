from __future__ import unicode_literals

from datetime import date

from django.contrib.auth.models import User

from django.db import models

# Create your models here.
from django.utils import timezone

TYPECHOICE = (
    (0, 'no type'),
    (1, 'movies'),
    (2, 'walks'),
    (3, 'sport'),
    (4, 'parties'),
    (5, 'drink')
)

SEXCHOICE = (
    ('male', 'male'),
    ('female', 'female')
)

class Event(models.Model):
    user = models.ForeignKey(User)
    name = models.CharField(max_length=64)
    description = models.CharField(max_length=256)
    budget = models.IntegerField()
    number_of_people = models.IntegerField()
    type = models.CharField(max_length=1, choices=TYPECHOICE)
    date = models.DateField(default='1960-01-11')
    time = models.TimeField(default="12:00")
    latitude = models.FloatField(blank=True)
    longtitude = models.FloatField(blank=True)
    def __str__(self):
        return str(self.name)

class Location(models.Model):
    user = models.OneToOneField(User)
    longtitude = models.FloatField()
    latitude = models. FloatField()
    def __str__(self):
        return str(self.user)

class JoinedEvent(models.Model):
    user = models.ForeignKey(User)
    event = models.ForeignKey(Event)
    def __str__(self):
        return str(self.event.name + " " + self.event.user.email)

class AnotherUser(models.Model):
    user = models.ForeignKey(User)
    phone = models.CharField(max_length=20)
    sex = models.CharField(max_length=6, choices=SEXCHOICE)
    birthday = models.DateField(default='1960-01-11')
    def age(self):
        import datetime
        return int((datetime.date.today() - self.birthday).days / 365.25)
    def __str__(self):
        return str(self.user)


