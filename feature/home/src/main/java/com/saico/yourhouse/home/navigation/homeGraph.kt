package com.saico.yourhouse.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saico.yourhome.ui.navigation.route.home.HomeRoute
import com.saico.yourhouse.home.HomeScreen

fun NavGraphBuilder.homeGraph(navHostController: NavHostController){
    navigation(
        startDestination = HomeRoute.HomeScreenRoute.route,
        route = HomeRoute.RootRoute.route
    ){
        composable(route = HomeRoute.HomeScreenRoute.route){
            HomeScreen()
        }
    }
}