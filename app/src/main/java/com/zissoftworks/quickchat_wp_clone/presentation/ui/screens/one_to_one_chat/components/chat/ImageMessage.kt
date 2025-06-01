package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage


@Composable
fun ImageMessage(message: ChatMessage) {
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
            if(message.cameraImage != null){
                Image(
                    bitmap = message.cameraImage.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
            else{
                Image(
                    painter = rememberAsyncImagePainter(model = message.fileUrl),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}