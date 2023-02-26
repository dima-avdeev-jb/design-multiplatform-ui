package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val MppTextFieldImplementation: ProvidableCompositionLocal<@Composable (
    text: String,
    onValueChange: (String) -> Unit,
) -> Unit> =
    staticCompositionLocalOf { { _, _ -> error("provide MppTextFieldImplementation") } }


object MppTextField : WithConfig() {
    @Composable
    operator fun invoke(
        text: String,
        onValueChange: (String) -> Unit,
        conf: ProvideScope.(MppTextField) -> Unit = {}
    ) {
        configure(
            conf = { conf(this@MppTextField) }
        ) {
            MppTextFieldImplementation.current(text, onValueChange)
        }
    }
}

val MppTextField.color by MppTextField.property<Color, MppTextField> { Color.Black }
val MppTextField.font by MppTextField.property<String, MppTextField> { "some font" }
val MppTextField.size by MppTextField.property<Int, MppTextField> { 30 }
