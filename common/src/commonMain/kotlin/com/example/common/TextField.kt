package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

// todo check performance with a lot of arguments
val MppTextFieldImplementation: ProvidableCompositionLocal<@Composable (
    text: String,
    onValueChange: (String) -> Unit,
) -> Unit> =
    staticCompositionLocalOf { { _, _ -> error("provide MppTextFieldImplementation") } }


@Composable
fun TextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    MppTextFieldImplementation.current(text, onValueChange)
}
