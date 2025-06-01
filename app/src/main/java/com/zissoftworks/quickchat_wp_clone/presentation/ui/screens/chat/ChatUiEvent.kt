package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.chat

sealed class ChatUiEvent{
    data object NavigateToOneToOneChat : ChatUiEvent()
}