package com.zissoftworks.quickchat_wp_clone.presentation.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object WelcomeOnboarding : Routes("welcome_onboarding")
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object OneToOneChat : Routes("OneToOneChat")
}