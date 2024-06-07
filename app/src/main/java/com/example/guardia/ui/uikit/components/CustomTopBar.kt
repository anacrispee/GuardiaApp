package com.example.guardia.ui.uikit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: Int,
    showNavigationButton: Boolean = false,
    onNavigationBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                style = AppTheme.typography.titleBold.title_lg,
                color = AppTheme.colors.secondary.background
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.primary.dark_pink
        ),
        navigationIcon = {
            if (showNavigationButton) {
                IconButton(
                    onClick = onNavigationBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        tint = AppTheme.colors.secondary.background,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    CustomTopBar(
        title = R.string.app_name
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreviewWithIcon() {
    CustomTopBar(
        title = R.string.app_name,
        showNavigationButton = true
    )
}