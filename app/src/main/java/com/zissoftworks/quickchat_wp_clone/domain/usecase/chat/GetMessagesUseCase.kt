package com.zissoftworks.quickchat_wp_clone.domain.usecase.chat

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import com.zissoftworks.quickchat_wp_clone.domain.repository.ChatMockupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val repository: ChatMockupRepository
) {
    operator fun invoke() : Flow<List<ChatMessage>> = repository.getMessages()
}