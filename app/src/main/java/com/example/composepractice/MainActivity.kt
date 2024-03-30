package com.example.composepractice

import ComposePracticeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepractice.navigation.NavigationItems
import com.example.composepractice.screens.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    MyApp {
                        NavHost(
                            navController = navController,
                            startDestination = NavigationItems.Home.route
                        ) {
                            composable(NavigationItems.Home.route) {
                                HomeScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}

@Preview
@Composable
fun Profile() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#f2f1f6"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            Modifier
                .height(250.dp)
                .background(color = Color(android.graphics.Color.parseColor("#32357a")))
        ) {
            val (topImg, profile, title, back, pen) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.arc_3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topImg) {
                        bottom.linkTo(parent.bottom)
                    })

            Image(
                painter = painterResource(id = R.drawable.user_2),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(profile) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(topImg.bottom)
                    })

            Text(
                text = "Profile",
                style = TextStyle(color = Color.White, fontSize = 30.sp),
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 32.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                })

            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(back) {
                        start.linkTo(parent.start, margin = 24.dp)
                        top.linkTo(parent.top, margin = 24.dp)
                    })

            Image(
                painter = painterResource(id = R.drawable.write),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(pen) {
                        top.linkTo(profile.top)
                        start.linkTo(profile.end)
                    })
        }

        Text(
            text = "Alex Flores",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            color = Color(android.graphics.Color.parseColor("#32357a"))
        )

        Text(
            text = "alexflores@gmail.com",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(android.graphics.Color.parseColor("#747679"))
        )

        Spacer(modifier = Modifier.height(22.dp))

        RowItem()
        RowItem(title = "Calendar", resourceId = R.drawable.btn_2)
        RowItem(title = "Gallery", resourceId = R.drawable.btn_3)
        RowItem(title = "My Playlist", resourceId = R.drawable.btn_4)
        RowItem(title = "Share", resourceId = R.drawable.btn_5)
        RowItem(title = "Logout", resourceId = R.drawable.btn_6)
    }
}

@Composable
fun RowItem(title: String = "Notification", resourceId: Int = R.drawable.btn_1) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
            .height(55.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable { })
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable { })
        }
    }
}