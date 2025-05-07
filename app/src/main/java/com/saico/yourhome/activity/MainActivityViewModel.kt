package com.saico.yourhome.activity

import androidx.lifecycle.ViewModel
import com.saico.yourhome.SignInResult
import com.saico.yourhome.ui.navigation.route.login.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class MainActivityViewModel @Inject constructor(): ViewModel() {

    var firstScreen = LoginRoute.RootRoute.route

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _uiState.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }

    }

    fun resetState() {
        _uiState.update { LoginUIState() }
    }
}


data class LoginUIState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)