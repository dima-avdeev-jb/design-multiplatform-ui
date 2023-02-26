package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ProvideConfig(config: ProvideConfig.() -> Unit, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        *buildList {
            ProvideConfig { add(it) }.config()
        }.toTypedArray()
    ) {
        content()
    }
}

