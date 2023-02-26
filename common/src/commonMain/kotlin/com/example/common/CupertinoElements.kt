package com.example.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CupertinoElements(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        MppTextImplementation provides { text, size ->
            Text(
                text = text,
                fontSize = size.sp,
            )
        },
        MppButtonImplementation provides { clickListener, buttonContent ->
            Box(Modifier.clickable(onClick = clickListener)) {
                ProvideTextStyle(value = TextStyle.Default.copy(color = Color.Blue)) {
                    buttonContent()
                }
            }
        },
        content = content
    )
}
