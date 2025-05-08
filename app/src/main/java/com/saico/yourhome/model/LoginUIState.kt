package com.saico.yourhome.model

data class LoginUIState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)