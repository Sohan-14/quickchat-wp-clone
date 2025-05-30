package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding

sealed class WelcomeOnboardingUiEvent{
    data object NavigateToLogin : WelcomeOnboardingUiEvent()
}