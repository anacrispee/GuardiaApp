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
private const val tiny_size = 12

data class AppTypography(
    val titleRegular: TitleRegular = TitleRegular,
    val titleBold: TitleBold = TitleBold,
    val titleSemiBold: TitleSemiBold = TitleSemiBold,

    val bodyRegular: BodyRegular = BodyRegular,
    val bodyMedium: BodyMedium = BodyMedium,
    val bodyBold: BodyBold = BodyBold,
    val bodySemiBold: BodySemiBold = BodySemiBold
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

    val title_md: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = medium_size.sp
    )

    val title_lg: TextStyle = TextStyle(
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

object BodyRegular {
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

object BodyMedium {
    private val manropeMedium = FontFamily(
        Font(R.font.manrope_medium, FontWeight.Medium)
    )

    val body_medium: TextStyle = TextStyle(
        fontFamily = manropeMedium,
        fontSize = medium_size.sp
    )

    val body_small: TextStyle = TextStyle(
        fontFamily = manropeMedium,
        fontSize = small_size.sp
    )
}

object BodyBold {
    private val manropeBold = FontFamily(
        Font(R.font.manrope_bold, FontWeight.Bold)
    )

    val body_small: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = small_size.sp
    )

    val body_tiny: TextStyle = TextStyle(
        fontFamily = manropeBold,
        fontSize = tiny_size.sp
    )
}

object BodySemiBold {
    private val manropeSemiBold = FontFamily(
        Font(R.font.manrope_semibold, FontWeight.SemiBold)
    )

    val body_medium: TextStyle = TextStyle(
        fontFamily = manropeSemiBold,
        fontSize = medium_size.sp
    )

    val body_small: TextStyle = TextStyle(
        fontFamily = manropeSemiBold,
        fontSize = small_size.sp
    )

    val body_tiny: TextStyle = TextStyle(
        fontFamily = manropeSemiBold,
        fontSize = tiny_size.sp
    )
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }