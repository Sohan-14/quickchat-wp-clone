package com.zissoftworks.quickchat_wp_clone.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.home.HomeScreen
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login.LoginScreen
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.splash.SplashScreen
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding.WelcomeOnboardingScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable( Routes.WelcomeOnboarding.route) {
            WelcomeOnboardingScreen(navController = navController)
        }
        composable( Routes.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }

    }
}
