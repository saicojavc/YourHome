package com.saico.yourhome.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saico.yourhome.splash.SplashScreen
import com.saico.yourhome.ui.navigation.route.home.HomeRoute
import com.saico.yourhome.ui.theme.YourHomeTheme
import com.saico.yourhouse.home.navigation.homeGraph
import com.saico.yourhouse.mylibrary.Signup.navigation.signupGraph
import com.saico.yourhouse.mylibrary.navigation.loginGraph
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {

            var showSplashScreen by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(1000)
                showSplashScreen = false
            }

            YourHomeTheme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (showSplashScreen){
                        SplashScreen()
                    }else{
                        MainContainer(
                            startDestination = viewModel.firstScreen,
                            auth = auth
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            viewModel.firstScreen = HomeRoute.RootRoute.route
        }
    }
}


@Composable
private fun MainContainer(
    startDestination: String,
    auth: FirebaseAuth,

    ){
    val navController = rememberNavController()

    Column {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.weight(1.0f)
        ) {

            loginGraph(navController, auth)

            signupGraph(navController, auth)

            homeGraph(navController, auth)


        }
    }
}
