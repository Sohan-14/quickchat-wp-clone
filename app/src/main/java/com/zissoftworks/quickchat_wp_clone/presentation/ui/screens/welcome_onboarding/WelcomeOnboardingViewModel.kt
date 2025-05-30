package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeOnboardingViewModel @Inject constructor() : ViewModel() {
    private val _event = MutableSharedFlow<WelcomeOnboardingUiEvent>()
    val event = _event.asSharedFlow()

    fun navigateToLogin(){
        viewModelScope.launch {
            delay(2000)
            _event.emit(WelcomeOnboardingUiEvent.NavigateToLogin)
        }
    }
}