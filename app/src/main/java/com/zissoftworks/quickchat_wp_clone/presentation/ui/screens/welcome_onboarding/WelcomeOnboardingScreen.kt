package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zissoftworks.quickchat_wp_clone.R
import com.zissoftworks.quickchat_wp_clone.presentation.navigation.Routes
import com.zissoftworks.quickchat_wp_clone.presentation.theme.QuickChatWpCloneTheme
import com.zissoftworks.quickchat_wp_clone.presentation.ui.components.PrimaryButton

@Composable
fun WelcomeOnboardingScreen(
    navController: NavController,
    viewModel: WelcomeOnboardingViewModel = hiltViewModel()
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val eventFlow = viewModel.event

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is WelcomeOnboardingUiEvent.NavigateToLogin -> {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.WelcomeOnboarding.route) { inclusive = true }
                    }
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            PrimaryButton(
                text = "Agree & Continue",
                isLoading = false,
                onClick = {
                    // isLoading = true
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboarding_image),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier.size(
                        width = (screenWidth.value * 0.9f).toFloat().dp,
                        height =(screenHeight.value * 0.3f).toFloat().dp,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Welcome to QuickChat",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.W400,
                        letterSpacing = 1.25.sp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Medium
                        )) {
                            append("to Accept the ")
                        }
                        withStyle(style = SpanStyle(
                            color = Color(0xFF0996EC),
                            fontWeight = FontWeight.Medium
                        )) {
                            append("Terms & Service")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(letterSpacing = 1.sp),
                )
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun WelcomeOnboardingScreenPreview(){
    QuickChatWpCloneTheme {
        val navController = rememberNavController()
        WelcomeOnboardingScreen(navController = navController)
    }
}