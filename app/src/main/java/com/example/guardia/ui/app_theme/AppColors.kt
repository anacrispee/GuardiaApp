package com.example.guardia.ui.app_theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Primary = Primary,
    secondary: Secondary = Secondary
) {
    val primary by mutableStateOf(primary)
    val secondary by mutableStateOf(secondary)
}

object Primary {
    val dark_pink = Color(0xFFB81D4C)
    val light_pink = Color(0xFFD24F76)
    val lighter_pink = Color(0xFFF9E8ED)
    val darkest_pink = Color(0xFFA2113D)
    val dark_grey = Color(0xFF5F5F5F)
    val light_grey = Color(0xFF969696)
}

object Secondary {
    val background = Color(0xFFF5F5F5)
    val lighter_grey = Color(0xFFD9D9D9)
    val lighter = Color(0xFFFFFFFF)
}

internal val LocalColors = staticCompositionLocalOf { AppColors() }