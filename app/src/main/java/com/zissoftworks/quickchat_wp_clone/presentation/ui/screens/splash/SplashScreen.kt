package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zissoftworks.quickchat_wp_clone.presentation.navigation.Routes
import com.zissoftworks.quickchat_wp_clone.presentation.theme.QuickChatWpCloneTheme
import com.zissoftworks.quickchat_wp_clone.presentation.ui.components.AppLogo
import com.zissoftworks.quickchat_wp_clone.presentation.ui.components.BrandingBottomLayout

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
){

    val eventFlow = viewModel.event

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is SplashUiEvent.NavigateToHome -> {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
                is SplashUiEvent.NavigateToWelcomeOnboarding -> navController.navigate(Routes.WelcomeOnboarding.route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            AppLogo()
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BrandingBottomLayout()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    QuickChatWpCloneTheme {
        val navController = rememberNavController()
        SplashScreen(navController = navController)
    }
}