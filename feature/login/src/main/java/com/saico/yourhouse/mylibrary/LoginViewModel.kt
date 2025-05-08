package com.saico.yourhouse.mylibrary

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saico.yourhouse.mylibrary.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    // Estado de la UI
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    fun onEmailChanged(value: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    email = value,
                    emailError = false,
                    eventError = null,
                    errorText = null,
                )
            }
            checkingButtonLoginState()
        }
    }

    fun onPasswordChanged(value: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    password = value,
                    eventError = null,
                    errorText = null,
                )
            }
            checkingButtonLoginState()
        }
    }

    fun onShowHidePassword() {
        viewModelScope.launch {
            _uiState.update { it.copy(isShowPassword = it.isShowPassword.not(), eventError = null) }
        }
    }
    private fun checkingButtonLoginState() {
        val email = _uiState.value.email.isNotEmpty()
        val pass = _uiState.value.password.length >= MIN_PASSWORD_LENGTH
        _uiState.update { it.copy(signInButtonEnabled = (email && pass)) }
    }
}