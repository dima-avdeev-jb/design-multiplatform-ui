package com.example.common

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.sp

@Composable
fun MaterialElements(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        MppTextImplementation provides { text, size ->
            Text(
                text = text,
                fontSize = size.sp,
            )
        },
        MppButtonImplementation provides { clickListener, buttonContent ->
            Button(onClick = clickListener) {
                buttonContent()
            }
        },
        content = content
    )
}
