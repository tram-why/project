"""Enjoyly2 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""

from django.conf.urls import url, include
from django.contrib import admin
from django.views.generic import TemplateView
from enjoyly import views
from rest_framework import routers
from enjoyly.views import UserCreateAPIView, UserLoginAPIView, EventCreateAPIView

router = routers.DefaultRouter()
router.register(r'user',views.UserViewSet)
router.register(r'event', views.EventViewSet)
router.register(r'location', views.LocationViewSet)
router.register(r'joinedevent', views.JoinedEventViewSet)

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^api/v1.0/', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework') ),
    url(r'^api/v1.0/register/', UserCreateAPIView.as_view(), name='register'),
    url(r'^api/v1.0/login/', UserLoginAPIView.as_view(), name='login'),
    url(r'^api/v1.0/createevent', EventCreateAPIView.as_view(), name='create event')
]
