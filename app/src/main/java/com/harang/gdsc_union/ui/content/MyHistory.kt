package com.harang.gdsc_union.ui.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.harang.gdsc_union.R
import com.harang.gdsc_union.ui.data.Posting
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun MyHistory(
    viewModel: GdscUnionViewModel
) {
    val selectedTeacherWorkPlace = viewModel.selectedTeacherWorkPlace.collectAsState().value
    if(viewModel.isMyPostingShowing.collectAsState().value) {
        MyPostingDialog(
            viewModel = viewModel,
            isWritingPost = {
                viewModel.updateIsMyPostingShowing(it)
            }) {
            viewModel.updateIsMyPostingShowing(false)
        }
    }

    val name = viewModel.userName.collectAsState().value
    val history = viewModel.myHistory.collectAsState().value
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
        Text(
            text = name
        )
        Text(
            text = selectedTeacherWorkPlace
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
//            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            itemsIndexed(history) {_, it ->
                MyPosting(
                    viewModel = viewModel,
                    post = it
                )
            }
        }
    }
}

@Composable
fun MyPosting(
    viewModel: GdscUnionViewModel,
    post: Posting
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
                    viewModel.updateIsMyPostingShowing(true)
                    viewModel.updateMySelectedPosting(post)
                },
            painter = painterResource(id = R.drawable.mail_image),
            contentDescription = ""
        )
    }
}

@Composable
fun MyPostingDialog(
    viewModel: GdscUnionViewModel,
    isWritingPost: (Boolean) -> Unit,
    onDismissRequest: () -> Unit
) {
    val posting = viewModel.mySelectedPostingContent.collectAsState().value
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
            Column(

            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = posting.teacherLoginId,
                    color = Color(0xff666666)
                )
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = posting.content,
                    color = Color(0xff666666)
                )
            }
        }
    }
}