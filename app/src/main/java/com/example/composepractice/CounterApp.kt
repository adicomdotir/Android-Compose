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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun CounterApp(counterViewModel: CounterViewModel) {
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
            Log.e("TAG", "CounterApp: ${it}", )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.e("TAG", "Column: ")
                Text(
                    text = "Count: ${counterViewModel.count}",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        counterViewModel.increaseCount()
                    }
                ) {
                    Log.e("TAG", "Button: ")
                    Text(text = "Increase")
                }
            }
        }
    )
}

class CounterViewModel : ViewModel() {
    private var _count by mutableStateOf(0)
    val count: Int get() = _count

    fun increaseCount() {
        _count += 1
    }
}