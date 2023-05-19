package com.harang.gdsc_union.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.R

@Composable
fun TeacherProfile() {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.carnation),
            contentDescription = ""
        )
        Text(
            text = "name"
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(listOf(1, 2, 3, 4)) {_, it ->
                PostCard(it)
            }
        }
    }
}

@Composable
fun PostCard(
    idx: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .width(100.dp)
                .height(100.dp)
                .background(
                    color = Color(0xffbbeebb),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$idx"
            )
        }
    }
}