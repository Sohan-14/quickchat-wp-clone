package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.splash

sealed class SplashUiEvent{
    data object NavigateToHome : SplashUiEvent()
    data object NavigateToWelcomeOnboarding : SplashUiEvent()
}