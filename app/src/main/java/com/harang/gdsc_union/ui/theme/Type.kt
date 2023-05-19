package com.harang.gdsc_union.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.harang.gdsc_union.R

val Typography = Typography(
    defaultFontFamily = FontFamily(
        Font(R.font.nanumgothic_regular, FontWeight.Normal),
        Font(R.font.nanumgothic_bold, FontWeight.Bold),
        Font(R.font.nanumgothic_extrabold, FontWeight.ExtraBold)
    )
)