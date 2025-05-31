package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {
    private val _event = MutableSharedFlow<SplashUiEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _event.emit(SplashUiEvent.NavigateToWelcomeOnboarding)
        }
    }
}