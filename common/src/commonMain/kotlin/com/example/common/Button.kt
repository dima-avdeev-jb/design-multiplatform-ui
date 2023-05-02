package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

// todo check performance with a lot of arguments
val MppButtonImplementation: ProvidableCompositionLocal<@Composable (clickListener: () -> Unit, buttonContent: @Composable () -> Unit) -> Unit> =
    staticCompositionLocalOf { { _, _ -> error("provide MppTextImplementation") } }

@Composable
fun Button(clickListener: () -> Unit, buttonContent: @Composable () -> Unit) {
    MppButtonImplementation.current(clickListener, buttonContent)
}
