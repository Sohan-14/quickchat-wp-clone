package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zissoftworks.quickchat_wp_clone.R

data class AttachmentOption(
    val icon: Int,
    val label: String,
    val backgroundColor: Color,
    val type: AttachmentType
)
enum class AttachmentType {
    Document, Camera, Gallery
}


@Composable
fun AttachmentBottomSheet(
    onOptionSelected: (String) -> Unit
) {
    val options = listOf(
        AttachmentOption(R.drawable.document_icon, "Document", Color(0xFF8E7CFA), type = AttachmentType.Document),
        AttachmentOption(R.drawable.camera_icon, "Camera", Color(0xFFFF5B8C), type = AttachmentType.Camera),
        AttachmentOption(R.drawable.gallery_icon, "Gallery", Color(0xFFB47EF2), type = AttachmentType.Gallery),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onOptionSelected(option.label) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(option.backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = option.icon),
                            contentDescription = option.label,
                            modifier = Modifier.run { size(24.dp) }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(option.label, fontSize = 13.sp)
                }
            }
        }
    }
}
