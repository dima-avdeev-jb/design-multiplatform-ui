package com.example.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@Preview
@Composable
fun AppPreview() {
    configure(conf = {
        MppTextField.onlyDesktopTooltip("This is tooltip")
    }) {
        App()
    }
}

val MppTextField.onlyDesktopTooltip by MppTextField.property<String, MppTextField>{"default"}
