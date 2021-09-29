from django.urls import path
from . import views

app_name = 'dancinggo_ai'

urlpatterns = [
    # articles/index/ ->index 
    path('', views.ai, name='ai'),
]