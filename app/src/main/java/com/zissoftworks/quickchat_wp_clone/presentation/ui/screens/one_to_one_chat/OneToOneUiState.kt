package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat

import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage

data class OneToOneUiState(
    val messages: List<ChatMessage> = emptyList(),
    val currentMessage: String = ""
)
