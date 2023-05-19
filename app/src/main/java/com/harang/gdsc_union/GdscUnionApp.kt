package com.harang.gdsc_union

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.harang.gdsc_union.ui.content.BottomBar
import com.harang.gdsc_union.ui.content.GdscUnionContent
import com.harang.gdsc_union.ui.content.TopBar
import com.harang.gdsc_union.ui.data.NavigationItemContent
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GdscUnionApp(
    viewModel: GdscUnionViewModel
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            viewType = ViewType.MyPage,
            icon = R.drawable.ic_launcher_foreground,
            text = "MyPage"
        )
    )
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        GdscUnionContent()
    }
}