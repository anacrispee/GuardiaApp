package com.example.guardia.ui.uikit.generic_screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme

@Composable
fun GenericEmptyStateScreen(
    @DrawableRes image: Int,
    title: Int,
    subtitle: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = image),
            tint = AppTheme.colors.primary.dark_grey,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.titleBold.title_md,
            color = AppTheme.colors.primary.dark_grey,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        Text(
            text = stringResource(id = subtitle),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.bodyMedium.body_small,
            color = AppTheme.colors.primary.light_grey,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GenericEmptyStateScreenPreview() {
    GenericEmptyStateScreen(
        image = R.drawable.magnifying_glass,
        title = R.string.empty_state_screen_title,
        subtitle = R.string.empty_state_screen_subtitle
    )
}