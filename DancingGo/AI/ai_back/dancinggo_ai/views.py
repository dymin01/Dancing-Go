# from django.shortcuts import render
from rest_framework.decorators import api_view
from django.http import JsonResponse
from .test import test
import base64

# Create your views here.
@api_view(['POST'])
def upload_image(request):
   base64Image = request.data['image']
   image = base64.b64decode(base64Image[22:])
   # image = base64Image.encode()
   # print(image)
   filename = 'testImage.jpg'
   with open(filename, "wb") as f:
      f.write(image)
   test(filename)

   result_json = { 'result': 'ok' }
   return JsonResponse(result_json)