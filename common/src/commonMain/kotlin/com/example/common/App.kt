package com.example.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    ProvideMaterialElements {
        Column(Modifier.fillMaxSize()) {
            var counter:Int by remember { mutableStateOf(0) }
            MppText("Hello $counter")
            MppButton(clickListener = {counter++}) {
                MppText("Click me")
            }
        }
    }
}

val MppTextImplementation: ProvidableCompositionLocal<@Composable (text: String, size:Int) -> Unit> =
    staticCompositionLocalOf { { _, _ -> TODO("provide MppTextImplementation") } }

val MppButtonImplementation: ProvidableCompositionLocal<@Composable (clickListener: () -> Unit, content: @Composable () -> Unit) -> Unit> =
    staticCompositionLocalOf { { _, _ -> TODO("provide MppTextImplementation") } }

@Composable
fun MppText(text: String, size: Int = 24) {
    MppTextImplementation.current(text, size)
}

@Composable
fun MppButton(clickListener: () -> Unit, content: @Composable () -> Unit) {
    MppButtonImplementation.current(clickListener, content)
}

@Composable
fun ProvideMaterialElements(content: @Composable ()->Unit) {
    CompositionLocalProvider(
        MppTextImplementation provides { text, size ->
            Text(
                text = text,
                fontSize = size.sp,
            )
        },
        MppButtonImplementation provides {clickListener, content ->
            Button(onClick = clickListener) {
                Icon(Icons.Default.Home, null)
                content()
            }
        },
        content = content
    )
}
