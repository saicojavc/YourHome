package com.saico.yourhome.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saico.yourhome.R

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "PERFECT CHOICE FOR YOUR FUTURE",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            lineHeight = 36.sp
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Our properties the masterpiece for every client with lasting value ",
            fontFamily = FontFamily.SansSerif,
            lineHeight = 24.sp
        )

        Image(
            painter = painterResource(id = R.drawable.intro_pic),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp)
                .size(400.dp)
        )

//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier.padding(top = 20.dp),
//            colors = androidx.compose.material3.ButtonColors(contentColor = Color.White,
//                disabledContainerColor = Color.Transparent,
//                disabledContentColor = Color.Transparent,
//                containerColor = Color(0xFF6FC1D0))
//        ) {
//            Text(
//                modifier = Modifier.padding(horizontal = 32.dp),
//                text = "Start"
//            )
//        }
    }
}