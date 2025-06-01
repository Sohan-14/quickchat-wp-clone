package com.zissoftworks.quickchat_wp_clone.domain.model.chat

data class ChatItem(
    val profileImageUrl: String,
    val name: String,
    val message: String,
    val time: String,
    val unreadCount: Int = 0
)
