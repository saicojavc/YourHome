package com.saico.yourhouse.mylibrary.Signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.saico.yourhome.ui.component.ButtonP
import com.saico.yourhome.ui.component.TextFieldEmil
import com.saico.yourhome.ui.component.TextFieldPassword
import com.saico.yourhouse.mylibrary.model.UIState
import com.saico.yourhome.ui.R

@Composable
fun SignupScreen(navHostController: NavHostController, auth: FirebaseAuth) {

    val viewModel: SignupViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignupContent(
        uiState = uiState,
        onEmailChange = { viewModel.onEmailChanged(it) },
        onPasswordChange = { viewModel.onPasswordChanged(it) },
        onIsShowPasswordChange = viewModel::onShowHidePassword,
        navHostController = navHostController,
        auth = auth
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupContent(
    uiState: UIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onIsShowPasswordChange: () -> Unit,
    navHostController: NavHostController,
    auth: FirebaseAuth,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
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

    Column {
        Image(
            modifier = Modifier
                .padding(12.dp)
                .size(36.dp)
                .clickable {
                    navHostController.popBackStack()
                },
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Create account",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextFieldEmil(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.emailSignup,
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
                value = uiState.passwordSignup,
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
                hidden = uiState.isShowPassword,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Password,
                        contentDescription = null,
                        tint = Color(0xFF6FC1D0)
                    )
                }
            )

            ButtonP(
                onClick = {
                    show = true
                    auth.createUserWithEmailAndPassword(uiState.emailSignup, uiState.passwordSignup)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                show = false
                                Toast.makeText(context, "User create", Toast.LENGTH_SHORT).show()
                                navHostController.popBackStack()
                            } else {
                                show = false
                                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = uiState.signInButtonEnabled,
                content = {
                    Text(
                        text = "Sign Up",
                    )
                },
                shape = ButtonDefaults.textShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6FC1D0),
                )
            )

        }

    }
}