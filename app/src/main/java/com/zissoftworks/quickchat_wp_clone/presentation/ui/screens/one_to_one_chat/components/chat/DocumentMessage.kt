package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage

@Composable
fun DocumentMessage(message: ChatMessage) {
    val alignment = if (message.isOwnMessage) Alignment.End else Alignment.Start
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (message.isOwnMessage) Color(0xFFDCF8C6) else Color.White
        ) {
            Row(modifier = Modifier.padding(12.dp)) {
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(message.fileUrl ?: "")
            }
        }
    }
}
