package com.saico.yourhome.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ButtonP(
    modifier: Modifier = Modifier,
    label: String = "",
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    content: @Composable RowScope.() -> Unit = { Text(text = label) },
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        content = content,
        shape = shape,
        colors = colors
    )
}
@Composable
fun TextButtonP(
    modifier: Modifier = Modifier,
    textButtonStyle: TextButtonStyle = TextButtonStyle.DEFAULT,
    label: String = "",
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = { Text(text = label) },
    onClick: () -> Unit,
) {
    val textColor = when (textButtonStyle) {
        TextButtonStyle.DEFAULT -> MaterialTheme.colorScheme.primary
        TextButtonStyle.DISMISS -> MaterialTheme.colorScheme.error
    }

    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor
        ),
        content = content
    )
}

@Composable
fun TextButtonBorder(
    modifier: Modifier = Modifier,
    textButtonStyle: TextButtonStyle = TextButtonStyle.DEFAULT,
    label: String = "",
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = { Text(text = label) },
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    onClick: () -> Unit,
    border: BorderStroke = BorderStroke(
        4.dp,
        MaterialTheme.colorScheme.outline
    )
) {
    val textColor = when (textButtonStyle) {
        TextButtonStyle.DEFAULT -> MaterialTheme.colorScheme.primary
        TextButtonStyle.DISMISS -> MaterialTheme.colorScheme.error
    }

    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = border,
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor
        ),
        contentPadding = contentPadding,
        content = content
    )
}

enum class TextButtonStyle {
    DEFAULT, DISMISS
}
