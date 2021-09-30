# from django.shortcuts import render
from rest_framework.decorators import api_view
from django.http import JsonResponse
from .test import test

# Create your views here.
@api_view(['POST'])
def upload_image(request):
   print(request.FILES['image'].file)
   image_file = request.FILES['image'].file
   test(image_file)

   result_json = { 'result': 'ok' }
   return JsonResponse(result_json)