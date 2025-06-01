package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage

@Composable
fun TextMessage(message: ChatMessage){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (message.isOwnMessage) Arrangement.End else Arrangement.Start
    ) {
        Column(
            horizontalAlignment = if (message.isOwnMessage) Alignment.End else Alignment.Start,
            modifier = Modifier
                .widthIn(max = 300.dp)
        ) {
            if (!message.isOwnMessage) {
                Text(
                    text = message.sender,
                    color = Color(0xFF128C7E),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }

            Surface(
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomEnd = if (message.isOwnMessage) 0.dp else 12.dp,
                    bottomStart = if (message.isOwnMessage) 12.dp else 0.dp
                ),
                color = if (message.isOwnMessage) Color(0xFFDCF8C6) else Color.White,
                tonalElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = message.content ?: "",
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = message.timestamp,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}
