package com.saico.yourhouse.mylibrary.model

data class UIState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val type: String = "user",
    val phone: String = "",
    val emailSignup: String = "",
    val emailError: Boolean = false,
    val errorText: String? = null,
    val password: String = "",
    val passwordSignup: String = "",
    val isShowPassword: Boolean = false,
    val passwordErrorText: String? = null,
    val isShowLoading: Boolean = false,
    val signInButtonEnabled: Boolean = false,
    val isShowInfoDialog: Boolean = false,
    val eventError: Int? = null,
)