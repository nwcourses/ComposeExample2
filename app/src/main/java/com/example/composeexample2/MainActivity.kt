package com.example.composeexample2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeexample2.ui.theme.ComposeExample2Theme
import androidx.compose.material3.TextField as TextField

// Messaging App
// This example demonstrates a prototype for a messaging app layout using LazyColumn and a material theme.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExample2Theme(dynamicColor = true){


                Surface(
                    Modifier.fillMaxSize(),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.background,
                    shadowElevation = 1.dp
                ) {

                    Column {
                        Spacer(Modifier.height(32.dp))
                        AddableList()
                    }
                }
            }
        }
    }

    @Composable
    fun AddableList() {
        // IMPORTANT adding to the list does not trigger a re-render
        // so we have to re-create the list
        var items1 by remember { mutableStateOf(listOf<String>()) }
        var currentItem by remember { mutableStateOf("") }
        Row {

            TextField(

                currentItem,
                modifier = Modifier.weight(2.0f),
              //  modifier = Modifier.fillMaxWidth(),
                onValueChange = { currentItem = it })


            Button( modifier = Modifier.weight(1.0f).padding(start =8.dp),onClick = {
                val items2: MutableList<String> = items1.toMutableList()
                items2.add(currentItem)
                items1 = items2
            }) { Text("Add message") }
        }

        Spacer(Modifier.height(32.dp))
        ListItems(items1)
    }

    @Composable
    fun ListItems(items1: List<String>) {
        LazyColumn {
            var i = 0
            items(items1) { message ->
                val isOdd = i % 2 == 1
                /// horizontalArrangement to Arrangement.End

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = if (i % 2 == 1) Arrangement.End else Arrangement.Start) {
                    Surface(
                        Modifier.padding(start = if (isOdd) 30.dp else 0.dp, end = if(isOdd) 0.dp else 30.dp, top= 8.dp, bottom = 8.dp),
                        shadowElevation = 5.dp,
                        color = if (isOdd) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium,

                    ) {
                        Text(
                            message,
                            modifier = Modifier.padding(8.dp),
                            color = if (isOdd) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary,
                        )
                        i++
                    }
                }
            }
        }
    }
}

