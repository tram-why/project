from __future__ import unicode_literals
from django.contrib.auth.models import User

from django.db import models

# Create your models here.

TYPECHOICE = (
    (1, 'movies'),
    (2, 'walks'),
    (3, 'sport'),
    (4, 'parties'),
    (5, 'drink')
)

class Event(models.Model):
    user = models.ForeignKey(User)
    name = models.CharField(max_length=64)
    description = models.CharField(max_length=256)
    budget = models.IntegerField()
    numberOfPeople = models.IntegerField()
    date = models.DateField()
    time = models.TimeField()
    type = models.CharField(max_length=1, choices=TYPECHOICE)
    phone = models.CharField(max_length=20)
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