package com.example.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MainActivityContent()
                }
            }
        }
    }

    @Composable
    fun MainActivityContent() {
        val celsius = remember {
            mutableStateOf(0.0)
        }
        val newCelsius = remember {
            mutableStateOf("")
        }
        var isError by rememberSaveable {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(image = R.drawable.food, description = "tmp image")
            Spacer(modifier = Modifier.height(16.dp))
            EnterTemperature(temperature = newCelsius.value, changed = { newCelsius.value = it }, isError = isError)
            Spacer(modifier = Modifier.height(16.dp))
            ConvertButton {
                isError = true
                newCelsius.value.toDoubleOrNull()?.let {
                    celsius.value = it
                    isError = false
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TemperatureText(celsius = celsius.value)
            Text(isError.toString())
        }
    }

    @Composable
    fun EnterTemperature(temperature: String, changed: (String) -> Unit, isError: Boolean) {
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        if (isError) error("Please use valid number")
                    },
                value = temperature,
                onValueChange = changed,
                label = {
                    Text(if (isError) "Enter a temperature in Celsius*" else "Enter a temperature in Celsius")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = isError,
            )
            if (isError) {
                Text(
                    "error",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
        }
    }

    private fun inputValueIsValid(temperature: String): Boolean {
        temperature.toDoubleOrNull()?.let {
            return true
        }
        return false
    }

    @Composable
    fun TemperatureText(celsius: Double) {
        val fahrenheit = (celsius.toDouble() * 9 / 5) + 32
        Text("$celsius Celsius is $fahrenheit Fahrenheit")
    }

    @Composable
    fun ConvertButton(clicked: () -> Unit) {
        Button(onClick = clicked) {
            Text("Convert")
        }
    }

    @Composable
    fun Header(image: Int, description: String) {
        Image(
            painter = painterResource(id = image),
            contentDescription = description,
            modifier = Modifier
                .height(180.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}