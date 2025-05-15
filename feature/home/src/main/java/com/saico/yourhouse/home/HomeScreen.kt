package com.saico.yourhouse.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.saico.yourhome.ui.component.AppNavigationBar
import com.saico.yourhome.ui.component.AppNavigationBarItem
import com.saico.yourhouse.home.model.BottomAppBarItem
import com.saico.yourhouse.home.screens.FavoriteScreen
import com.saico.yourhouse.home.screens.MainHomeScreen
import com.saico.yourhouse.home.screens.SettingScreen

@Composable
fun HomeScreen(navHostController: NavHostController, auth: FirebaseAuth) {

    Content()

}

@Composable
fun Content(){

    var selectedBottomAppBarItem by remember { mutableStateOf(BottomAppBarItem.HOME)  }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        bottomBar = {
            AppNavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 0.dp,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AppNavigationBarItem(
                            isSelected = selectedBottomAppBarItem == BottomAppBarItem.FAVORITE,
                            label = {
                                Text(text = "Favorite")
                            },
                            icon = Icons.Rounded.Favorite,
                            contentDescription = "Favorite",
                            onClick = {
                                selectedBottomAppBarItem = BottomAppBarItem.FAVORITE
                            },
                        )
                        AppNavigationBarItem(
                            isSelected = selectedBottomAppBarItem == BottomAppBarItem.HOME,
                            label = {
                                Text(text = "Home")
                            },
                            icon = Icons.Rounded.Home,
                            contentDescription = "Home",
                            onClick = {
                                selectedBottomAppBarItem = BottomAppBarItem.HOME
                            },
                        )

                        AppNavigationBarItem(
                            isSelected = selectedBottomAppBarItem == BottomAppBarItem.PROFILE,
                            label = {
                                Text(text = "Profile")
                            },
                            icon = Icons.Rounded.Person,
                            contentDescription = "Profile",
                            onClick = {
                                selectedBottomAppBarItem = BottomAppBarItem.PROFILE
                            },
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->

        Row (
            modifier = Modifier.padding(paddingValues)
        ){
            when(selectedBottomAppBarItem){
                BottomAppBarItem.HOME ->{
                    MainHomeScreen()
                }
                BottomAppBarItem.FAVORITE ->{
                    FavoriteScreen()
                }
                BottomAppBarItem.PROFILE ->{
                   SettingScreen()
                }
            }
        }

    }
}