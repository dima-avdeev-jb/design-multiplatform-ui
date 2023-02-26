package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val MppButtonImplementation: ProvidableCompositionLocal<@Composable (clickListener: () -> Unit, buttonContent: @Composable () -> Unit) -> Unit> =
    staticCompositionLocalOf { { _, _ -> TODO("provide MppTextImplementation") } }

@Composable
fun MppButton(clickListener: () -> Unit, buttonContent: @Composable () -> Unit) {
    MppButtonImplementation.current(clickListener, buttonContent)
}
