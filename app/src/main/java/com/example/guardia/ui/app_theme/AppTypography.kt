package com.example.guardia.ui.app_theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.guardia.R

private const val small_size = 12
private const val medium_size = 14
private const val default_size = 16
private const val large_size = 20

data class AppTypography(
    val title: Title = Title,
    val body: Body = Body
)

object Title {
    private val manropeBold = FontFamily(
        Font(R.font.manrope_bold, FontWeight.Bold)
    )

    val title_small: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = small_size.sp
    )

    val title_medium: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = medium_size.sp
    )

    val title_default: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = default_size.sp
    )

    val title_large: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = large_size.sp
    )
}

object Body {
    private val manropeRegular = FontFamily(
        Font(R.font.manrope_regular, FontWeight.Normal)
    )

    val body_small: TextStyle = TextStyle(
        fontFamily = manropeRegular,
        fontSize = small_size.sp
    )

    val body_medium: TextStyle = TextStyle(
        fontFamily = manropeRegular,
        fontSize = medium_size.sp
    )

    val body_default: TextStyle = TextStyle(
        fontFamily = manropeRegular,
        fontSize = default_size.sp
    )

    val body_large: TextStyle = TextStyle(
        fontFamily = manropeRegular,
        fontSize = large_size.sp
    )
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }