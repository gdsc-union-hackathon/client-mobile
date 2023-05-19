package com.harang.gdsc_union.ui.content

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "TopBar"
            )
        }
    )
}