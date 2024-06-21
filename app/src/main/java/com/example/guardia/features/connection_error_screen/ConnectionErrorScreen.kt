package com.example.guardia.features.connection_error_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.guardia.R
import com.example.guardia.ui.uikit.generic_screens.GenericScreen

@Composable
fun ConnectionErrorScreen(
    navController: NavHostController,
    fromScreen: String
) {
    GenericScreen(
        image = R.drawable.ic_wifi,
        title = R.string.empty_state_screen_title,
        subtitle = R.string.empty_state_screen_subtitle,
        onButtonClick = {
            navController.navigate(fromScreen)
        },
        buttonText = R.string.try_again_button
    )
}