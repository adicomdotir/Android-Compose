package com.example.composepractice

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

val myFlow = flow<Int> {
    for (i in 1..20) {
        emit(i)
        delay(1000L)
    }
}

@Composable
fun CounterApp() {


    val currentValue = myFlow.collectAsState(initial = 0).value

//    LaunchedEffect(key1 = "key", block = {
//        CoroutineScope(Dispatchers.Main).launch {
//            f.collect {
//                value = it
//                delay(1000L)
//            }
//        }
//    })




//    val count = counterViewModel.count
    Log.e("TAG", "CounterApp: ")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Counter App")
                }
            )
        },
        content = {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.e("TAG", "Column: ")
                Text(
                    text = "Count: ${currentValue}",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                    }
                ) {
                    Log.e("TAG", "Button: ")
                    Text(text = "Increase")
                }
            }
        }
    )
}