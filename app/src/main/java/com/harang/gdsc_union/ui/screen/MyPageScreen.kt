package com.harang.gdsc_union.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.harang.gdsc_union.ui.content.MyHistory
import com.harang.gdsc_union.ui.content.TeacherProfile
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun MyPageScreen(
    viewModel: GdscUnionViewModel
) {
    when(viewModel.isTeacher.collectAsState().value) {
        true -> TeacherProfile()
        else -> MyHistory()
    }
}