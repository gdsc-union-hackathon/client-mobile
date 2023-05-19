package com.harang.gdsc_union.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComingStudents() {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "찾아오는 제자들",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}