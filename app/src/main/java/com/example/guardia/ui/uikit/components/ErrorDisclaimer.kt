package com.example.guardia.ui.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guardia.R

@Composable
fun ErrorDisclaimer(
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, color = Color.Red,
                shape = MaterialTheme.shapes.small
            )
            .background(
                color = Color.Red.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorDisclaimerPreview() {
    ErrorDisclaimer(
        text = stringResource(R.string.login_screen_error_message)
    )
}