package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zissoftworks.quickchat_wp_clone.R

@Composable
fun ChatInputBar(
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onReceivedClick: () -> Unit,
    onAttachmentClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = onAttachmentClick) {
                Image(
                    painter = painterResource(id = R.drawable.attachment_icon),
                    contentDescription = "Send as a Sender",
                    modifier = Modifier.run { size(24.dp) }
                )
            }

            TextField(
                value = message,
                onValueChange = onMessageChange,
                placeholder = { Text("Message") },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(100.dp)),
                shape = RoundedCornerShape(100.dp),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    errorContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )

            IconButton(onClick = onReceivedClick, modifier = Modifier.padding(0.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_down_circle_fill),
                    contentDescription = "Send as a Receiver",
                    modifier = Modifier.run { size(48.dp) }
                )
            }
            IconButton(onClick = onSendClick, modifier = Modifier.padding(0.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_up_circle_fill),
                    contentDescription = "Send as a Sender",
                    modifier = Modifier.run { size(48.dp) }
                )
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
    }
}


@Composable
@Preview(showSystemUi = true)
fun ChatInputBarPreview(){
    ChatInputBar(
        message = "Hi",
        onMessageChange = {  },
        onSendClick = {  },
        onReceivedClick = {  },
        onAttachmentClick = {  },
    )
}