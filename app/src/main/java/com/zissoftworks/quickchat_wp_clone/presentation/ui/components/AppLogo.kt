package com.zissoftworks.quickchat_wp_clone.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zissoftworks.quickchat_wp_clone.R


@Composable
fun AppLogo (size: Dp = 120.dp) {
    Image(
        painter = painterResource(id = R.drawable.whatsapp_logo),
        contentDescription = "App Logo",
        modifier = Modifier.run { size(size) }
    )
}