package com.saico.yourhouse.home


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.saico.yourhome.ui.navigation.route.login.LoginRoute

@Composable
fun HomeScreen(navHostController: NavHostController, auth: FirebaseAuth) {
    Text(
        modifier = Modifier.clickable {
            auth.signOut()
            navHostController.navigate(
                LoginRoute.LoginScreenRoute.route
            )
        },
        text = "Home Screen"
    )
}