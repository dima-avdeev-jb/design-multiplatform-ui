package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val MppTextImplementation: ProvidableCompositionLocal<@Composable (text: String, size: Int) -> Unit> =
    staticCompositionLocalOf { { _, _ -> error("provide MppTextImplementation") } }

@Composable
fun MppText(text: String, size: Int = 24, config: MppTextConfig.() -> Unit = {}) {
    ProvideConfig(config = {
        config(object : MppTextConfig, ProvideConfig by this {})
    }) {
        MppTextImplementation.current(text, size)
    }
}

val MppTextColor = staticCompositionLocalOf { Color.Unspecified }
fun MppTextConfig.color(color: Color) {
    provide(MppTextColor provides color)
}

interface MppTextConfig : ProvideConfig
