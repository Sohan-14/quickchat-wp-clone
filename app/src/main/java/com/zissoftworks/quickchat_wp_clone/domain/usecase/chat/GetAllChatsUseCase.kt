package com.zissoftworks.quickchat_wp_clone.domain.usecase.chat

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatItem
import com.zissoftworks.quickchat_wp_clone.domain.repository.ChatMockupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllChatsUseCase @Inject constructor(
    private val repository: ChatMockupRepository
) {
    operator fun invoke() : Flow<List<ChatItem>> {
        return repository.getAllChats()
    }
}