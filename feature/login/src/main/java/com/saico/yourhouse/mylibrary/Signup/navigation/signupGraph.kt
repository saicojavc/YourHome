package com.saico.yourhouse.mylibrary.Signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import com.saico.yourhome.ui.navigation.route.login.SignupRoute
import com.saico.yourhouse.mylibrary.Signup.SignupScreen

fun NavGraphBuilder.signupGraph(navHostController: NavHostController, auth: FirebaseAuth){
    navigation(
        startDestination = SignupRoute.SignupScreenRoute.route,
        route = SignupRoute.RootRoute.route
    ){
        composable(route = SignupRoute.SignupScreenRoute.route){
            SignupScreen(
                navHostController = navHostController,
                auth = auth)
        }
    }
}