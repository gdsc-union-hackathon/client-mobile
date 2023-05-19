package com.harang.gdsc_union.ui.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.harang.gdsc_union.R
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun TeacherProfile(
    viewModel: GdscUnionViewModel
) {
    val selectedTeacherName = viewModel.selectedTeacherName.collectAsState().value
    val selectedTeacherWorkPlace = viewModel.selectedTeacherWorkPlace.collectAsState().value
    val isWritingPost = viewModel.isWritingPost.collectAsState().value
    if(isWritingPost) {
        PostWritingDialog(
            viewModel = viewModel,
            isWritingPost = {
                viewModel.updateIsWritingPost(it)
            }
        ) {
            viewModel.updateIsWritingPost(false)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = painterResource(id = R.drawable.carnation),
            contentDescription = ""
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(
                    text = selectedTeacherName
                )
                Text(
                    text = selectedTeacherWorkPlace
                )
            }
            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )
            if(!viewModel.isUserTeacher.collectAsState().value) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .clip(
                            shape = RoundedCornerShape(50)
                        )
                        .background(
                            color = Color(0xffbbeebb),
                            shape = RoundedCornerShape(50)
                        )
                        .clickable {
                            viewModel.updateIsWritingPost(true)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "메일 추가하기"
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .clip(
                            shape = RoundedCornerShape(50)
                        )
                        .background(
                            color = Color(0xffbbeebb),
                            shape = RoundedCornerShape(50)
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "찾아가기"
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
//            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
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
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(
                    shape = GenericShape { size, _ ->
                        moveTo(0f, size.height * 0.18f)
                        lineTo(size.width, size.height * 0.18f)
                        lineTo(size.width, size.height * 0.82f)
                        lineTo(0f, size.height * 0.82f)
                    }
                )
                .clickable {
                    Log.e("mail", "clicked")
                },
            painter = painterResource(id = R.drawable.mail_image),
            contentDescription = ""
        )
    }
}

@Composable
fun PostWritingDialog(
    viewModel: GdscUnionViewModel,
    isWritingPost: (Boolean) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties()
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(600.dp)
                .background(
                    color = Color(0xffeeffee),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                value = viewModel.postingContent.collectAsState().value,
                onValueChange = { viewModel.updatePostingContent(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.post()
                        viewModel.updatePostingContent("")
                    }
                ),
            )
            if(viewModel.postingContent.collectAsState().value == "") {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = "내용을 입력하세요",
                    color = Color(0xff666666)
                )
            }
        }
    }
}