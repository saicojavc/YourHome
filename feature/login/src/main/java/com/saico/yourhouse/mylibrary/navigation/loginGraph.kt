package com.saico.yourhouse.mylibrary.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.saico.yourhome.ui.navigation.route.login.LoginRoute
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.saico.yourhouse.mylibrary.LoginScreen

fun NavGraphBuilder.loginGraph(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    onLoginWithGoogle: () -> Unit,
    ) {
    navigation(
        startDestination = LoginRoute.LoginScreenRoute.route,
        route = LoginRoute.RootRoute.route
    ){
        composable(route = LoginRoute.LoginScreenRoute.route){
            LoginScreen(
                navHostController = navHostController,
                auth = auth,
                onLoginWithGoogle = onLoginWithGoogle
                )
        }
    }
}