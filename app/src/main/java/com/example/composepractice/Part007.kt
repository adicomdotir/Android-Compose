package com.example.composepractice

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MainPart007() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var textFieldState by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            OutlinedTextField(
                value = textFieldState,
                label = {
                    Text("Enter your name")
                },
                singleLine = true,
                onValueChange = { textFieldState = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello ${textFieldState}")
                }
            }) {
                Text(text = "Pls greet me")
            }
        }
    }
}