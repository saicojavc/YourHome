package com.saico.yourhome.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldP(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: @Composable (() -> Unit)? = {},
    enabled: Boolean = true,
    singleLine: Boolean = true,
    errorMessage: String? = null,
    maxLines: Int = if (singleLine) 1 else 5,
    maxLength: Int? = null,
    shape: Shape = ShapeDefaults.Medium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: androidx.compose.material.TextFieldColors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color(0xFF6FC1D0),
        cursorColor = Color(0xFF6FC1D0),
        focusedBorderColor = Color(0xFF6FC1D0),
        unfocusedBorderColor = Color(0xFF6FC1D0),
        focusedLabelColor = Color(0xFF6FC1D0),
        unfocusedLabelColor = Color(0xFF6FC1D0)
    ),
    trailingIcon: @Composable (() -> Unit)? = {},
    leadingIcon: @Composable (() -> Unit)? = {},
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (maxLength != null) {
                    if (it.length <= maxLength) {
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            maxLines = maxLines,
            label = {
                ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
                    Text(text = label)
                }
            },
            textStyle = textStyle,
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = colors,
//            isError = errorMessage == null,
            shape = shape,
            visualTransformation = visualTransformation,
            trailingIcon = if (errorMessage != null) {
                trailingIcon
            } else {
                {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            leadingIcon = if (errorMessage != null) {
                leadingIcon
            } else {
                {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
        )
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun TextFieldEmil(
    modifier: Modifier = Modifier,
    label: String = "Email",
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 40,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email, imeAction = imeAction
    ),
    keyboardActions: KeyboardActions,
    trailingIcon: @Composable (() -> Unit)? = {},
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    TextFieldP(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        maxLength = maxLength,
        label = label,
        errorMessage = errorMessage,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon
    )
}

@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    label: String = "Password",
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 40,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
    ),
    keyboardActions: KeyboardActions,
    hidden: Boolean,
    onHiddenChange: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    TextFieldP(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        maxLength = maxLength,
        label = label,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        errorMessage = errorMessage,
        visualTransformation = if (hidden) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onHiddenChange) {
                val vector = if (hidden) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility
                val description = if (hidden) "Hide password" else "Show password"
                Icon(
                    imageVector = vector,
                    contentDescription = null,
                    tint = Color(0xFF6FC1D0)
                )
            }
        },
        leadingIcon = leadingIcon,
    )
}