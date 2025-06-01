package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components

import androidx.compose.runtime.Composable
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.MessageType
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat.DocumentMessage
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat.ImageMessage
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat.TextMessage

@Composable
fun ChatMessageItem(message: ChatMessage) {
    when (message.type) {
        MessageType.TEXT -> TextMessage(message)
        MessageType.IMAGE -> ImageMessage(message)
        MessageType.DOCUMENT -> DocumentMessage(message)
    }
}
