package com.example.composepractice

import ComposePracticeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CounterActivity : ComponentActivity() {
    private val counterViewModel = CounterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    val state by counterViewModel.state.observeAsState(CounterState())
                    val storedData by counterViewModel.storedData.observeAsState(mutableListOf())

                    Counter(model = state, storedData = storedData, onIntentReceived = {
                        counterViewModel.processIntent(intent = it)
                    })
                }
            }
        }
    }
}

@Composable
fun Counter(
    model: CounterState, storedData: List<Int>, onIntentReceived: (CounterIntent) -> Unit
) {
    val myState = remember {
        mutableStateOf<Int>(0)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Count: ${model.count}",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth(0.5f)
                .padding(vertical = 16.dp)
        )

        Button(onClick = {
            onIntentReceived(CounterIntent.Increment)
        }) {
            Text(text = "Increment")
        }

        Button(onClick = {
            onIntentReceived(CounterIntent.Decrement)
        }) {
            Text(text = "Decrement")
        }

        Button(onClick = {
            onIntentReceived(CounterIntent.Reset)
        }) {
            Text(text = "Reset".uppercase())
        }

        Button(onClick = {
            onIntentReceived(CounterIntent.RandomNumber)
        }) {
            Text(text = "Random Number")
        }

        LazyColumn() {
            items(storedData) {
                Text(text = it.toString())
            }
        }


    }
}

data class CounterState(val count: Int = 0)

sealed class CounterIntent {
    object Increment : CounterIntent()
    object Decrement : CounterIntent()
    object Reset : CounterIntent()
    object RandomNumber : CounterIntent()
}

class CounterViewModel : ViewModel() {
    private val dataSource = DataSource()

    private val _state = MutableLiveData(CounterState())
    val state: LiveData<CounterState> = _state
    private val _storedData = MutableLiveData<List<Int>>(mutableListOf())
    val storedData: LiveData<List<Int>> = _storedData

    fun processIntent(intent: CounterIntent) {
        when (intent) {
            CounterIntent.Increment -> {
                val newCount = _state.value?.count?.plus(1) ?: 0
                _state.value = CounterState(newCount)
            }

            CounterIntent.Decrement -> {
                val newCount = _state.value?.count?.minus(1) ?: 0
                _state.value = CounterState(newCount)
            }

            CounterIntent.Reset -> {
                val newCount = 0
                _state.value = CounterState(newCount)
            }

            CounterIntent.RandomNumber -> {
                val newCount = Random.nextInt(-100, 100)
                _state.value = CounterState(newCount)
            }
        }
        saveData(state.value?.count ?: 0)
    }

    private fun saveData(number: Int) {
        dataSource.addToList(number)
        fetchData()
    }

    private fun fetchData() {
        _storedData.value = dataSource.fetchData()
    }
}

class DataSource {
    private val _storedData = mutableListOf<Int>()

    fun fetchData(): List<Int> {
        return _storedData.toList()
    }

    fun addToList(number: Int) {
        _storedData.add(number)
    }
}