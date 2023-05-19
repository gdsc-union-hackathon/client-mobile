package com.harang.gdsc_union.ui.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.screen.MyPageScreen
import com.harang.gdsc_union.ui.screen.SearchScreen
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun GdscUnionContent(
    viewModel: GdscUnionViewModel
) {
    when(viewModel.uiState.collectAsState().value) {
        ViewType.MyPage -> {
            MyPageScreen()
        }
        else -> {
            SearchScreen(
                viewModel = viewModel
            )
        }
    }
}