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
//            checkingButtonLoginState()
        }
    }

    fun onShowHidePassword() {
        viewModelScope.launch {
            _uiState.update { it.copy(isShowPassword = it.isShowPassword.not(), eventError = null) }
        }
    }

}