package com.zissoftworks.quickchat_wp_clone.domain.repository

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatItem
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatMockupRepository {
    fun getAllChats(): Flow<List<ChatItem>>
    fun getMessages(): Flow<List<ChatMessage>>
    fun addMessage(chatMessage: ChatMessage)
}