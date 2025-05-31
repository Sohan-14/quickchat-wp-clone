package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login

sealed class LoginUiEvent{
    data object NavigateToHome : LoginUiEvent()
    data class ShowToast(val message: String) : LoginUiEvent()
}