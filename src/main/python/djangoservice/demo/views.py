from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from .models import Message
import os
import sys

from .src.service.ChatBot.chatbot import get_disease_info


def disease_info_api(request):
    if request.method == 'GET':
        disease_name = request.GET.get('disease_name', None)
        if disease_name:
            answer = get_disease_info(disease_name)
            # 存储到数据库
            message = Message(question=disease_name, answer=answer)
            message.save()

            return JsonResponse({'question': disease_name, 'answer': answer})
        else:
            return JsonResponse({'error': 'No disease name provided'}, status=400)
    else:
        return JsonResponse({'error': 'Invalid request method'}, status=405)
