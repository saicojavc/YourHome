package com.saico.yourhome.activity

import androidx.lifecycle.ViewModel
import com.saico.yourhome.ui.navigation.route.login.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor(): ViewModel() {

    val firstScreen = LoginRoute.RootRoute.route
}