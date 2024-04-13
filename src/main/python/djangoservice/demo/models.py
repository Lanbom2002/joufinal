from django.db import models

# Create your models here.
class Message(models.Model):
    question = models.CharField(max_length=255)
    answer = models.TextField()