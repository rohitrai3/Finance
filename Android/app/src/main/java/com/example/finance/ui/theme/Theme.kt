package com.example.finance.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    background = White,
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val TextInputDarkColorScheme = TextFieldColors(
    focusedTextColor = White,
    unfocusedTextColor = White,
    disabledTextColor = LightWhite,
    errorTextColor = Red,
    focusedContainerColor = Black,
    unfocusedContainerColor = Black,
    disabledContainerColor = Black,
    errorContainerColor = LighterRed,
    cursorColor = White,
    errorCursorColor = Red,
    textSelectionColors = TextSelectionColors(
        backgroundColor = Gray,
        handleColor = Gray
    ),
    focusedIndicatorColor = Gray,
    unfocusedIndicatorColor = Gray,
    disabledIndicatorColor = LightGray,
    errorIndicatorColor = Black,
    focusedLeadingIconColor = Black,
    unfocusedLeadingIconColor = Black,
    disabledLeadingIconColor = Black,
    errorLeadingIconColor = Black,
    focusedTrailingIconColor = Black,
    unfocusedTrailingIconColor = Black,
    disabledTrailingIconColor = Black,
    errorTrailingIconColor = Black,
    focusedLabelColor = White,
    unfocusedLabelColor = White,
    disabledLabelColor = LightWhite,
    errorLabelColor = Red,
    focusedPlaceholderColor = LightWhite,
    unfocusedPlaceholderColor = LightWhite,
    disabledPlaceholderColor = LightWhite,
    errorPlaceholderColor = LightRed,
    focusedSupportingTextColor = White,
    unfocusedSupportingTextColor = Black,
    disabledSupportingTextColor = Black,
    errorSupportingTextColor = Red,
    focusedPrefixColor = Black,
    unfocusedPrefixColor = Black,
    disabledPrefixColor = Black,
    errorPrefixColor = Black,
    focusedSuffixColor = Black,
    unfocusedSuffixColor = Black,
    disabledSuffixColor = Black,
    errorSuffixColor = Black
)

@Composable
fun FinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content,
        typography = Typography
    )
}
