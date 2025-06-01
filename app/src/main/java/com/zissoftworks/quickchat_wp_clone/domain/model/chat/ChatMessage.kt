package com.zissoftworks.quickchat_wp_clone.domain.model.chat

import android.graphics.Bitmap
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val sender: String,
    val content: String? = null,
    val type: MessageType = MessageType.TEXT,
    val fileUrl: String? = null,
    val cameraImage: Bitmap? = null,
    val timestamp: String = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date()),
    val isOwnMessage: Boolean = true
)

enum class MessageType {
    TEXT, IMAGE, DOCUMENT
}
