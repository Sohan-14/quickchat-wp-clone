package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.welcome_onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeOnboardingViewModel @Inject constructor() : ViewModel() {
    private val _event = MutableSharedFlow<WelcomeOnboardingUiEvent>()
    val event = _event.asSharedFlow()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun navigateToLogin(){
        _isLoading.value = true
        viewModelScope.launch {
            delay(2000)
            _event.emit(WelcomeOnboardingUiEvent.NavigateToLogin)
            _isLoading.value = false
        }
    }
}