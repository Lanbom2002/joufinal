from django.contrib import admin
from .models import Message

# 定义自定义Admin类
class MessageAdmin(admin.ModelAdmin):
    # 设置列表页显示的字段
    list_display = ('question',)
    # 添加搜索栏，允许通过question字段搜索
    search_fields = ('question',)

# 使用自定义的Admin类注册Message模型
admin.site.register(Message, MessageAdmin)