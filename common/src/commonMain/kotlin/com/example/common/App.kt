package com.example.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App() {
    var cupertinoActive by remember { mutableStateOf(false) }
    Switch(cupertinoActive, onCheckedChange = { cupertinoActive = it })
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (cupertinoActive) {
            CupertinoElements {
                Usage()
            }
        } else {
            MaterialElements {
                Usage()
            }
        }
    }
}

@Composable
fun Usage() {
    Column {
        var counter: Int by remember { mutableStateOf(0) }
        MppText("Hello $counter") {
            color(Color.Red)
        }
        MppButton(clickListener = { counter++ }) {
            MppText("Click me")
        }
    }
}
