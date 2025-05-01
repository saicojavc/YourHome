package com.saico.yourhouse.mylibrary

import android.annotation.SuppressLint
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saico.yourhome.ui.R
import com.saico.yourhome.ui.component.ButtonP
import com.saico.yourhome.ui.component.TextButtonBorder
import com.saico.yourhome.ui.component.TextFieldEmil
import com.saico.yourhome.ui.component.TextFieldPassword
import com.saico.yourhouse.mylibrary.model.UIState


@Composable
fun LoginScreen(

) {


    Content(
    )

}

@Composable
fun Content(
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
                    .padding(top = 30.dp)
                    .size(200.dp)
            )
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
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
                        value = email,
                        errorMessage = "",
                        onValueChange = {
                            email = it.trim()
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
                        value = password,
                        errorMessage = "",
                        onValueChange = {
                            password = it.trim()
                        },
                        label = "Password",
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus(true)
//                            onSingInPress()
                            }
                        ),
                        onHiddenChange = {},
                        hidden = false, //cambiar,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null,
                                tint = Color(0xFF6FC1D0)
                            )
                        }
                    )
//                Spacer(modifier = Modifier.height(8.dp))

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

                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = true,
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
                }
            }

        }
    }


}