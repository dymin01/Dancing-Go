from django.urls import path
from . import views

app_name = 'dancinggo_ai'

urlpatterns = [
    # articles/index/ ->index 
    path('', views.upload_image, name='upload_image'),
    path('sample/', views.sample, name='sample'),
]