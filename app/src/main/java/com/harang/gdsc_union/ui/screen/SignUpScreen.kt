package com.harang.gdsc_union.ui.screen

import android.util.Log
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun SignUpScreen(
    viewModel: GdscUnionViewModel
) {
    val idHintTransition = updateTransition(viewModel.inputSignUpId.collectAsState().value, label = "")
    val idHintUp by idHintTransition.animateDp(label = "") {
        when(it) {
            "" -> 0.dp
            else -> (-15).dp
        }
    }
    val nameHintTransition = updateTransition(viewModel.inputName.collectAsState().value, label = "")
    val nameHintUp by nameHintTransition.animateDp(label = "") {
        when(it) {
            "" -> 0.dp
            else -> (-15).dp
        }
    }
    val phoneNumberHintTransition = updateTransition(viewModel.inputPhoneNumber.collectAsState().value, label = "")
    val phoneNumberHintUp by phoneNumberHintTransition.animateDp(label = "") {
        when(it) {
            "" -> 0.dp
            else -> (-15).dp
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "회원가입"
        )
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
                value = viewModel.inputSignUpId.collectAsState().value,
                onValueChange = { viewModel.updateInputSignUpId(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )
            Text(
                    modifier = Modifier
                        .offset(0.dp, idHintUp)
                        .padding(start = 20.dp),
            text = "Id",
            color = Color(0xff666666)
            )
        }
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
                value = viewModel.inputName.collectAsState().value,
                onValueChange = { viewModel.updateInputName(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )
            Text(
                modifier = Modifier
                    .offset(0.dp, nameHintUp)
                    .padding(start = 20.dp),
                text = "이름",
                color = Color(0xff666666)
            )
        }
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
                value = viewModel.inputPhoneNumber.collectAsState().value,
                onValueChange = { viewModel.updatePhoneNumber(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )
            Text(
                modifier = Modifier
                    .offset(0.dp, phoneNumberHintUp)
                    .padding(start = 20.dp),
                text = "전화번호",
                color = Color(0xff666666)
            )
        }
        val annotatedString = buildAnnotatedString {
            append("이미 계정이 있으신가요?")
            withStyle(
                style = SpanStyle(
                    color = Color.Red
                )
            ) {
                pushStringAnnotation(tag = "SignUp", annotation = "SignUp")
                append("로그인")
            }
        }
        ClickableText(
            text = annotatedString,
            onClick = {
                    offset ->
                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let { span ->
                        viewModel.updateIsSigningIn(true)
                    }
            }
        )
    }
}

@Composable
fun TestComposable() {
    Text(
        text = "hello World!"
    )
}