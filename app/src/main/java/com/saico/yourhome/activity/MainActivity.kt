package com.saico.yourhome.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saico.yourhome.splash.SplashScreen
import com.saico.yourhome.ui.theme.YourHomeTheme
import com.saico.yourhouse.mylibrary.navigation.loginGraph
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
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
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MainContainer(
    startDestination: String,

    ){
    val navController = rememberNavController()

    Column {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.weight(1.0f)
        ) {


            loginGraph(navController)


        }
    }
}
