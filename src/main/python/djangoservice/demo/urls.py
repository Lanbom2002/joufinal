from django.urls import path
from .views import disease_info_api

urlpatterns = [
    path('disease-info/', disease_info_api, name='disease_info_api'),
]
