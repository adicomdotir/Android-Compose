package com.example.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(title =  {
                    Text(text = "Food App")
                })
                RecipeList(recipes = listOf(Recipe(R.drawable.food, "Title", mutableListOf("Salt", "Eggs", "Mushrooms")), Recipe(R.drawable.food, "Title", mutableListOf("Salt", "Eggs", "Mushrooms"))))
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier) {
    val image = painterResource(id = recipe.imageResource)
    androidx.compose.material.Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(painter = image, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxWidth()
                .height(144.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(recipe.title, style = MaterialTheme.typography.h4)
                for (ing in recipe.ingredients) {
                    Text(text = ". $ing")
                }
            }
        }
    }
}

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn() {
        items(recipes, itemContent = { item -> RecipeCard(recipe = item, Modifier.padding(16.dp))})
    }
}