package com.example.composepractice.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.aec.domain.model.Blog
import coil.compose.rememberImagePainter

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val res = viewModel.blogs.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = res.error, modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    LazyColumn {
        res.data?.let { blogs ->
            items(blogs) { blog ->
                PostItem(blog)
            }
        }
    }
}


@Composable
fun PostItem(blog: Blog) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            CircularImage(50.0, 50.0, 25.0, blog.owner.picture)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "${blog.owner.firstName} ${blog.owner.lastName}")
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberImagePainter(data = blog.image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Text(
            text = blog.text,
            modifier = Modifier.padding(12.dp),
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
    }
}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {
    Card(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp), shape = RoundedCornerShape(radius.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}
