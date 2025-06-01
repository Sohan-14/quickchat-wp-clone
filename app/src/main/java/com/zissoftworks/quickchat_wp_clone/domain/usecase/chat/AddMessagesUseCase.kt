package com.zissoftworks.quickchat_wp_clone.domain.usecase.chat

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import com.zissoftworks.quickchat_wp_clone.domain.repository.ChatMockupRepository
import javax.inject.Inject

class AddMessagesUseCase @Inject constructor(
    private val repository: ChatMockupRepository
) {
    operator fun invoke(chatMessage : ChatMessage){
        repository.addMessage(chatMessage = chatMessage)
    }
}