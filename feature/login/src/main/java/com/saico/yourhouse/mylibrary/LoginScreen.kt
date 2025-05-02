package com.saico.yourhouse.mylibrary

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.saico.yourhome.ui.R
import com.saico.yourhome.ui.component.ButtonP
import com.saico.yourhome.ui.component.TextButtonBorder
import com.saico.yourhome.ui.component.TextFieldEmil
import com.saico.yourhome.ui.component.TextFieldPassword
import com.saico.yourhome.ui.navigation.route.home.HomeRoute
import com.saico.yourhome.ui.navigation.route.login.SignupRoute
import com.saico.yourhouse.mylibrary.model.UIState


@Composable
fun LoginScreen(navHostController: NavHostController, auth: FirebaseAuth) {

    val viewModel: LoginViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Content(
        state = uiState,
        onEmailChange = { viewModel.onEmailChanged(it) },
        onPasswordChange = { viewModel.onPasswordChanged(it) },
        onIsShowPasswordChange = viewModel::onShowHidePassword,
        navHostController = navHostController,
        auth = auth
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Content(
    state: UIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onIsShowPasswordChange: () -> Unit,
    navHostController: NavHostController,
    auth: FirebaseAuth,
) {
    var show by remember { mutableStateOf(false) }

    if (show) {
        Dialog(
            onDismissRequest = { show = show },
            content = {
                Surface(
                    modifier = Modifier
                        .size(50.dp),
                    shape = ShapeDefaults.Small,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }

            }
        )

    }

    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
            ) {
                Text(
                    text = "Login with:",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButtonBorder(
                    modifier = Modifier
                        .fillMaxWidth(),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.googlelogo),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp, 26.dp)
                        )
                        Text(
                            text = "Google",
                            color = Color(0xFF6FC1D0)
                        )
                    },
                    onClick = {

                    },
                    border = BorderStroke(
                        2.dp,
                        Color(0xFF6FC1D0)
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.intro_pic),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = "Login",
                        textAlign = TextAlign.Start,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TextFieldEmil(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.email,
                        errorMessage = "",
                        onValueChange = {
                            onEmailChange(it.trim())
                        },
                        label = "Email",
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus(true)
//                            onSingInPress()
                            }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = null,
                                tint = Color(0xFF6FC1D0)
                            )
                        }
                    )

                    TextFieldPassword(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.password,
                        errorMessage = "",
                        onValueChange = {
                            onPasswordChange(it.trim())
                        },
                        label = "Password",
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus(true)
//                            onSingInPress()
                            }
                        ),
                        onHiddenChange = onIsShowPasswordChange,
                        hidden = state.isShowPassword, //cambiar,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null,
                                tint = Color(0xFF6FC1D0)
                            )
                        }
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Forgot password?",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    ButtonP(
                        onClick = {
                            show = true
                            auth.signInWithEmailAndPassword(state.email, state.password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        show = false
                                        Toast.makeText(context, "Is Success", Toast.LENGTH_SHORT)
                                            .show()
                                        navHostController.navigate(
                                            HomeRoute.HomeScreenRoute.route
                                        )
                                    } else {
                                        show = false
                                        Toast.makeText(
                                            context,
                                            "Email or password incorrect or not exist",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = state.signInButtonEnabled,
                        content = {
                            Text(
                                text = "Login",
                            )
                        },
                        shape = ButtonDefaults.textShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6FC1D0),
                        )
                    )
                    TextButtonBorder(
                        modifier = Modifier
                            .fillMaxWidth(),
                        content = {

                            Text(
                                text = "Do you have an account?",
                                color = Color(0xFF6FC1D0)
                            )
                        },
                        onClick = {
                            navHostController.navigate(
                                SignupRoute.SignupScreenRoute.route
                            )
                        },
                        border = BorderStroke(
                            2.dp,
                            Color(0xFF6FC1D0)
                        )
                    )
                }
            }

        }
    }


}