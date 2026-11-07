package com.aurora03.postscodingchallengecompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    //Fondo general
    background = backgroundHomeGreenDark,

    //Fondo tarjetas
    surface = backgroundCardGreenDark,

    //Botones
    primary = buttonBackgroundGreenDark,
    onPrimary = buttonTextWhiteDark,

    //Textos
    onBackground = textIconsGrayDark,
    onSurface = textPublicationGrayDark,
    onSurfaceVariant = titlePublicationsGreenDark,

    //Iconos
    secondary = iconsGreenDark,
    tertiary = backgroundCardGreenDark,

    //Bordes
    outline = borderGreenDark,

    //Etiquetas
    secondaryContainer = backgroundHomeGreenDark,
    onSecondaryContainer = titleHomeGrayDark
)

private val LightColorScheme = lightColorScheme(
    //Fondo general
    background = backgroundHomeGreenLight,

    //Fondo tarjetas
    surface = backgroundCardWhiteLight,

    //Botones
    primary = buttonBackgroundGreenLight,
    onPrimary = buttonTextWhiteLight,

    //Textos
    onBackground = textIconsGrayLight,
    onSurface = textPublicationGrayLight,
    onSurfaceVariant = titlePublicationsGreenLight,

    //Iconos
    secondary = iconsGreenLight,
    tertiary = backgroundCardWhiteLight,


    //Bordes
    outline = borderGreenlight,

    //Etiquetas
    secondaryContainer = backgroundHomeGreenLight,
    onSecondaryContainer = titleHomeGrayLight
)

@Composable
fun PostsCodingChallengeComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}