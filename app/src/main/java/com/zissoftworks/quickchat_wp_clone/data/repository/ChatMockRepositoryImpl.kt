package com.zissoftworks.quickchat_wp_clone.data.repository

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatItem
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import com.zissoftworks.quickchat_wp_clone.domain.repository.ChatMockupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class ChatMockRepositoryImpl : ChatMockupRepository {

    override fun getAllChats(): Flow<List<ChatItem>> {
        return flow {
            emit(loadDummyChats())
        }
    }

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())

    override fun addMessage(message: ChatMessage) {
        _chatMessages.value = _chatMessages.value + message
    }

    override fun getMessages(): Flow<List<ChatMessage>> = _chatMessages

    fun clearMessages() {
        _chatMessages.value = emptyList()
    }

    private fun loadDummyChats(): List<ChatItem> {
        val profileImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4ShH0LRKN7I84YMG9VfFrv-vTnMv7qLPnrQ&s"

        return listOf(
            ChatItem(profileImage, "Emma Johnson", "Let's finalize the project timeline.", "09:15"),
            ChatItem(profileImage, "David Smith", "I've sent over the report draft.", "10:45"),
            ChatItem(profileImage, "Sophia Williams", "Looking forward to our meeting.", "13:30"),
            ChatItem(profileImage, "James Brown", "Could you review the latest update?", "15:00"),
            ChatItem(profileImage, "Olivia Davis", "Please share the client feedback.", "16:50", 1),
            ChatItem(profileImage, "Team Sync", "Meeting starts in 10 minutes.", "Yesterday"),
            ChatItem(profileImage, "Liam Miller", "I'll be joining remotely.", "Yesterday", 2),
            ChatItem(profileImage, "Ava Wilson", "Document has been uploaded.", "28/05/2025"),
            ChatItem(profileImage, "Noah Taylor", "Invoice is ready for review.", "27/05/2025")
        )
    }


}