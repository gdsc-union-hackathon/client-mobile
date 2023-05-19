package com.harang.gdsc_union.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.ui.content.PostCard
import com.harang.gdsc_union.ui.content.TeacherProfile
import com.harang.gdsc_union.ui.data.TeacherInfo
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun SearchScreen(
    viewModel: GdscUnionViewModel
) {
    if(viewModel.isUserTeacher.collectAsState().value) {
        ComingStudents()
    } else {
        BackHandler(enabled = true) {
            viewModel.updateIsShowingTeacherProfile(false)
        }
        if(viewModel.isShowingTeacherProfile.collectAsState().value) {
            TeacherProfile(
                viewModel = viewModel
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(0xffbbeebb),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        value = viewModel.inputTeacherName.collectAsState().value,
                        onValueChange = { viewModel.updateInputTeacherName(it) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                viewModel.getTeachersList()
                                viewModel.updateInputTeacherName("")
                                viewModel.updateIsWritingPost(false)
                            }
                        ),
                    )
                    if(viewModel.inputTeacherName.collectAsState().value == "") {
                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp),
                            text = "스승님 이름을 입력하세요",
                            color = Color(0xff666666)
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                val teacherList = viewModel.searchedTeachersList.collectAsState().value
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
//            contentPadding = PaddingValues(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(teacherList) {_, it ->
                        TeacherItem(
                            viewModel = viewModel,
                            info = it
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeacherItem(
    viewModel: GdscUnionViewModel,
    info: TeacherInfo
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = Color(0xffbbeebb),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    viewModel.updateSelectedTeacher(info.loginId, info.name, info.workPlace)
                    viewModel.getTeacherProfile()
                    viewModel.updateIsShowingTeacherProfile(true)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${info.name}, ${info.status}"
            )
        }
    }
}