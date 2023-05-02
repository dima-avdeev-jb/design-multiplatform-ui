package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

// todo check performance with a lot of arguments
val MppTextImplementation: ProvidableCompositionLocal<@Composable (text: String, size: Int) -> Unit> =
    staticCompositionLocalOf { { _, _ -> error("provide MppTextImplementation") } }

@Composable
fun Text(text: String, size: Int = 24) {
    MppTextImplementation.current(text, size)
}
