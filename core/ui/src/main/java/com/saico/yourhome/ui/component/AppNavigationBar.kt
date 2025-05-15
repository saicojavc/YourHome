package com.saico.yourhome.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomAppBarDefaults.ContainerElevation,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        content = content
    )
}

@Composable
fun AppBottomAppBar(
    modifier: Modifier = Modifier,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomAppBarDefaults.ContainerElevation,
    content: @Composable RowScope.() -> Unit,
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        content = content
    )
}

@Composable
fun RowScope.AppNavigationBarItem(
    isSelected: Boolean,
    enable: Boolean = true,
    alwaysShowLabel: Boolean = false,
    label: @Composable () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        },
        label = label,
        selected = isSelected,
        onClick = onClick,
        enabled = enable,
        alwaysShowLabel = alwaysShowLabel,
    )
}
