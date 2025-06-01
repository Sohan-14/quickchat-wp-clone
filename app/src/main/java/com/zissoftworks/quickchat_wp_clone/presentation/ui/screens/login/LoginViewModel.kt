package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zissoftworks.quickchat_wp_clone.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _selectedCountry = mutableStateOf("Bangladesh" to "+880")
    val selectedCountry: State<Pair<String, String>> = _selectedCountry

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    fun onCountrySelected(country: Pair<String, String>) {
        _selectedCountry.value = country
    }

    fun onPhoneNumberChange(number: String) {
        _phoneNumber.value = number
    }

    fun getFullPhoneNumber(): String {
        return selectedCountry.value.second + phoneNumber.value
    }


    private val _event = MutableSharedFlow<LoginUiEvent>()
    val event = _event.asSharedFlow()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun navigateToHome(){
        _isLoading.value = true
        if(selectedCountry.value.second.isEmpty() || phoneNumber.value.isEmpty()){
            viewModelScope.launch {
                _event.emit(LoginUiEvent.ShowToast("Fill the phone number"))
                _isLoading.value = false
            }
        }
        else{
            viewModelScope.launch {
                loginUseCase(getFullPhoneNumber())
                    .collect { isSuccess ->
                        if (isSuccess) {
                            _event.emit(LoginUiEvent.NavigateToHome)
                        } else {
                            _event.emit(LoginUiEvent.ShowToast("Something went wrong!!"))
                        }
                        _isLoading.value = false
                    }
            }
        }

    }

}