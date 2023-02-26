package com.example.common

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun MaterialElements(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        MppTextImplementation provides { text, size ->
            Text(
                text = text,
                fontSize = size.sp,
                color = MppTextColor.current,
            )
        },
        MppButtonImplementation provides { clickListener, buttonContent ->
            Button(onClick = clickListener) {
                buttonContent()
            }
        },
        MppTextFieldImplementation provides { text: String, onValueChange: (String) -> Unit ->
            TextField(
                text,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = MppTextField.color.provider.current,
                    fontSize = MppTextField.size.provider.current.sp,
                )
            )
        },
        content = content
    )
}
