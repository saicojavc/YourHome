package com.saico.yourhome.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saico.yourhome.GoogleAuthUiClient
import com.saico.yourhome.splash.SplashScreen
import com.saico.yourhome.ui.navigation.route.home.HomeRoute
import com.saico.yourhome.ui.theme.YourHomeTheme
import com.saico.yourhouse.home.navigation.homeGraph
import com.saico.yourhouse.mylibrary.Signup.navigation.signupGraph
import com.saico.yourhouse.mylibrary.navigation.loginGraph
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private val viewModel: MainActivityViewModel by viewModels()

    private val googleAuthClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }


    @SuppressLint("StateFlowValueCalledInComposition")
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
                    val navController = rememberNavController()

                    if (showSplashScreen){
                        SplashScreen()
                    }else{

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = viewModel.uiState.value.isSignInSuccessful) {
                            if (viewModel.uiState.value.isSignInSuccessful){
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate( HomeRoute.RootRoute.route)
                                viewModel.resetState()
                            }
                        }
                        MainContainer(
                            navController = navController,
                            startDestination = viewModel.firstScreen,
                            auth = auth,
                            onLoginWithGoogle = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )

                        LaunchedEffect(Unit) {
                            while (true) {

                                if (viewModel.uiState.value.isSignInSuccessful) {
                                    navController.navigate(HomeRoute.RootRoute.route) {
                                        popUpTo(0) // Elimina el historial de navegación para evitar volver al login
                                    }

                                    viewModel.resetState()
                                }
                                delay(3000) // Espera 3 segundos antes de repetir
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null || googleAuthClient.getSignedInUser() != null){
            viewModel.firstScreen = HomeRoute.RootRoute.route
        }
    }
}


@Composable
private fun MainContainer(
    startDestination: String,
    auth: FirebaseAuth,
    onLoginWithGoogle: () -> Unit,
    navController: NavHostController,

    ){
//    val navController = rememberNavController()

    Column {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.weight(1.0f)
        ) {

            loginGraph(navController, auth, onLoginWithGoogle)

            signupGraph(navController, auth)

            homeGraph(navController, auth)


        }
    }
}
