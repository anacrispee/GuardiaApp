package com.example.guardia.features.feature_shelters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.ui.app_theme.AppTheme

@Composable
fun FindSheltersScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SheltersScreen",
            style = AppTheme.typography.titleBold.title_lg,
            color = AppTheme.colors.primary.dark_grey
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SheltersScreenPreview() {
    FindSheltersScreen(
        navController = rememberNavController()
    )
}