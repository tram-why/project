from django.contrib import admin

from enjoyly.models import Event, Location, JoinedEvent

# Register your models here.

admin.site.register(Event)

admin.site.register(Location)

admin.site.register(JoinedEvent)