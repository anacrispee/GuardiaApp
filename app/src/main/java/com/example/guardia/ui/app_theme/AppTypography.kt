package com.example.guardia.ui.app_theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.guardia.R

private const val large_size = 20
private const val medium_size = 16
private const val small_size = 14

data class AppTypography(
    val titleRegular: TitleRegular = TitleRegular,
    val titleBold: TitleBold = TitleBold,
    val titleSemiBold: TitleSemiBold = TitleSemiBold,
    val body: Body = Body
)

object TitleRegular {
    private val manropeRegular = FontFamily(
        Font(R.font.manrope_regular, FontWeight.Normal)
    )

    val title_lg: TextStyle = TextStyle(
        fontFamily = manropeRegular,
        fontSize = large_size.sp
    )
}

object TitleBold {
    private val manropeBold = FontFamily(
        Font(R.font.manrope_bold, FontWeight.Bold)
    )

    val title_lg_bold: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = large_size.sp
    )
}

object TitleSemiBold {
    private val manropeSemiBold = FontFamily(
        Font(R.font.manrope_semibold, FontWeight.SemiBold)
    )

    val title_md_semi_bold: TextStyle = TextStyle(
        fontFamily = manropeSemiBold,
        fontSize = medium_size.sp
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
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }