package com.harang.gdsc_union.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier
            .height(30.dp),
        backgroundColor = Color(0xffffffff),
        title = {
            Text(
                text = "TopBar",
                color = Color(0xff000000)
            )
        }
    )
}