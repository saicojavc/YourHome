package com.saico.yourhome.ui.navigation.route.login

import com.saico.yourhome.ui.navigation.route.Route

sealed interface LoginRoute: Route {
    data object RootRoute: LoginRoute{
        override val analyticsTag = "login-flow"
        override val route = "login"
    }
    data object LoginScreenRoute: LoginRoute{
        override val analyticsTag = "login-screen-flow"
        override val route = "login/login-screen"
    }

}