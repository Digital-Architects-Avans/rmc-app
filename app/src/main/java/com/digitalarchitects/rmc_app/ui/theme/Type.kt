package com.digitalarchitects.rmc_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R

// Initialize fonts
val NotoSans = FontFamily(
    Font(R.font.notosans_black, FontWeight.Black),
    Font(R.font.notosans_black_italic, FontWeight.Black, FontStyle.Italic)
)

val Chivo = FontFamily(
    Font(R.font.chivo_regular, FontWeight.Normal),
    Font(R.font.chivo_medium, FontWeight.Medium)
)

// Set headlines
val Typography = Typography(
    // Display type for Rent My Car Logo (Welcome and My account screen)
    displayLarge = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    // Display type for licence plate / date on vehicle- and rental details (bottom sheet)
    displayMedium = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Display type for licence plate on vehicle list item (list item)
    displaySmall = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    // Title type for page title (e.g. My rentals), Vehicle on vehicle details (bottom sheet)
    titleLarge = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Title type for list item titles and sub-titles on vehicle details (bottom sheet)
    titleMedium = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // Title type for tabs
    titleSmall = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // Body type for normal text (e.g. Welcome screen), Value of text fields
    bodyLarge = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // Body type for medium text (e.g. Labels on buttons)
    bodyMedium = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // Body type for small text (e.g. Labels on text fields)
    bodySmall = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // Label for buttons, filter chips, status pill, location, price/distance on rental list item
    labelLarge = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // Label for info labels (location/price on vehicle list item, status/date on rental list item)
    labelMedium = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // Label for small info label on search filters
    labelSmall = TextStyle(
        fontFamily = Chivo,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)