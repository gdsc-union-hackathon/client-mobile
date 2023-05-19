package com.harang.gdsc_union.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun SearchScreen(
    viewModel: GdscUnionViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 20.dp, end = 20.dp)
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
    }
}