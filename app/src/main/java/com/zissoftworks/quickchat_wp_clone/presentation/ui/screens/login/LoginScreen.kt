package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zissoftworks.quickchat_wp_clone.presentation.navigation.Routes
import com.zissoftworks.quickchat_wp_clone.presentation.theme.QuickChatWpCloneTheme
import com.zissoftworks.quickchat_wp_clone.presentation.ui.components.PrimaryButton
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login.components.CountryPhoneInputDropdown
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding.WelcomeOnboardingUiEvent

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val selectedCountry by viewModel.selectedCountry
    val phoneNumber by viewModel.phoneNumber

    val eventFlow = viewModel.event
    val isLoading by viewModel.isLoading

    val context = LocalContext.current


    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToHome -> {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
                is LoginUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            PrimaryButton(
                text = "Login",
                isLoading = isLoading,
                onClick = viewModel::navigateToHome,
                buttonMargin = 28.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter your phone number",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "QuickChat will need to verify your phone\nNumber. Carerier charge may apply",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "What's my number?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                CountryPhoneInputDropdown(
                    selectedCountry = selectedCountry,
                    onCountrySelected =  viewModel::onCountrySelected,
                    phoneNumber = phoneNumber,
                    onPhoneNumberChange = viewModel::onPhoneNumberChange
                )
            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    QuickChatWpCloneTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}