package com.harang.gdsc_union.ui.data

import androidx.annotation.DrawableRes

data class NavigationItemContent(
    val viewType: ViewType,
    @DrawableRes
    val icon: Int,
    val text: String
)
