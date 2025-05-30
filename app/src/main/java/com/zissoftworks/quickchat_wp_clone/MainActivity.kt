package com.zissoftworks.quickchat_wp_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zissoftworks.quickchat_wp_clone.presentation.navigation.AppNavGraph
import com.zissoftworks.quickchat_wp_clone.presentation.theme.QuickChatWpCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickChatWpCloneTheme {
                AppNavGraph()
            }
        }
    }
}