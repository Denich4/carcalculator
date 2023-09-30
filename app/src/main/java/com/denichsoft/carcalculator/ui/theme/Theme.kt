package com.denichsoft.carcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkFront,
    onPrimary = LightFront,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = DarkBack,
    surface = DarkFront,
)

private val LightColorPalette = lightColors(
    primary = LightFront,
    onPrimary = DarkBack,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = LightBack,
    surface = LightFront,
    onSurface = DarkBack

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CarcalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}