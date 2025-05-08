package com.saico.yourhome.ui.navigation.route.login

import com.saico.yourhome.ui.navigation.route.Route
import com.saico.yourhome.ui.navigation.route.home.HomeRoute

sealed interface SignupRoute: Route {

    data object RootRoute: SignupRoute{
        override val analyticsTag = "signup-flow"
        override val route = "signup"
    }

    data object SignupScreenRoute: SignupRoute{
        override val analyticsTag = "signup-screen-flow"
        override val route = "signup/signup-screen"

    }

}