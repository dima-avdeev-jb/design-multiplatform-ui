package com.example.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun App() {
    var cupertinoActive by remember { mutableStateOf(false) }
    Row {
        Switch(cupertinoActive, onCheckedChange = { cupertinoActive = it })
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (cupertinoActive) {
            CupertinoStyle {
                Usage("CupertinoStyle")
            }
        } else {
            MaterialStyle {
                Usage("MaterialStyle")
            }
        }
    }
}

@Composable
fun Usage(styleText: String) {
    Column {
        Text(styleText)
        var counter: Int by remember { mutableStateOf(0) }
        Button(clickListener = { counter++ }) {
            Text("Click me $counter")
        }

        var textState by remember { mutableStateOf("Text field state") }
        TextField(textState, onValueChange = { textState = it })
    }
}
