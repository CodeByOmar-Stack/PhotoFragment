package com.deff.photofragment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = Color.Black,
    secondary = WarmGray,
    background = DarkBg,
    surface = DarkBg,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun PhotoFragmentTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
