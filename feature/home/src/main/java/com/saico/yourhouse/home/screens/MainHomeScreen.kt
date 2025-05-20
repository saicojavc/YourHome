package com.saico.yourhouse.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bathroom
import androidx.compose.material.icons.rounded.Bed
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import  com.saico.yourhome.ui.R
import com.saico.yourhome.ui.navigation.route.login.LoginRoute

@Composable
fun MainHomeScreen(
    auth: FirebaseAuth,
    navHostController: NavHostController
) {

    val typeHouseIcons = listOf(
        R.drawable.cat_1,
        R.drawable.cat_2,
        R.drawable.cat_3,
        R.drawable.cat_4,
        R.drawable.cat_5
    )
    val houses = listOf(
        R.drawable.house_1,
        R.drawable.house_2,
        R.drawable.house_3,
        R.drawable.house_4,
    )

    Content(
        navHostController = navHostController,
        auth = auth,
        typeHouseIcons = typeHouseIcons,
        houses = houses
    )

}

@Composable
fun Content(
    typeHouseIcons: List<Int>,
    auth: FirebaseAuth,
    navHostController: NavHostController,
    houses: List<Int>
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Rounded.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        auth.signOut()
                        navHostController.navigate(
                            LoginRoute.LoginScreenRoute.route
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(paddingValues)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(typeHouseIcons) { typeHouseIcon ->
                    TypeHouseList(
                        typeHouseIcon = typeHouseIcon
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                items(houses){ house ->
                    HouseCart(
                        house = house
                    )
                }
            }
        }
    }

}

@Composable
fun HouseCart(house: Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {},

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(
                modifier = Modifier
                    .width(310.dp)
                    .height(120.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = house),
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .width(310.dp)
//                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "House Type",
                    fontWeight = FontWeight.Bold,
                )

                Row(
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = "4.3",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = null
                )
                Text(
                    text = "Miami",
                )
            }
            Row(
                modifier = Modifier
                    .width(310.dp)
//                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Bed,
                        contentDescription = null
                    )
                    Text(
                        text = "2",
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Icon(
                        imageVector = Icons.Rounded.Bathroom,
                        contentDescription = null
                    )
                    Text(
                        text = "2",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End
                ){

                    Text(
                        text = "$500.000",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TypeHouseList(
    typeHouseIcon: Int
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
                .padding(12.dp),


            ) {
            Image(
                modifier = Modifier
                    .size(35.dp),
                painter = painterResource(id = typeHouseIcon),
                contentDescription = null,
            )

        }
        Text("type")
    }

}