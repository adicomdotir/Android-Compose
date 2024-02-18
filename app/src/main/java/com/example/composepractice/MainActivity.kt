package com.example.composepractice

import BasicsCodelabTheme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel> { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Surface {
                    MyApp(viewModel = mainViewModel)
                }
            }
        }
    }

    @Composable
    private fun MyApp(viewModel: MainViewModel) {
        val items by viewModel.items.collectAsState()
        val itemsLiveData by viewModel.itemsLiveData.observeAsState(emptyList())

        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        text = "My App ${items.size} - ${itemsLiveData.size}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            },
            content = {
                ItemList(itemsLiveData)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.addItem()
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "")
                }
            }
        )
    }

    @Composable
    private fun ItemList(items: List<Item>) {
        val ctx = LocalContext.current
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = it.name,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        IconButton(
                            onClick = {
                                Toast.makeText(ctx, "$it Tapped", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "item_delete"
                            )
                        }
                    }
                }
            }
        }
    }
}