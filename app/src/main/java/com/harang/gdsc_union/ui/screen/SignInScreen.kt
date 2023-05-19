package com.harang.gdsc_union.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun SignInScreen(
    viewModel: GdscUnionViewModel
) {
    val idHintTransition = updateTransition(viewModel.inputSignInId.collectAsState().value, label = "")
    val idHintUp by idHintTransition.animateDp(label = "") {
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
            text = "로그인"
        )
        Text(
            text = "계속 하시려면 로그인하세요."
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
                value = viewModel.inputSignInId.collectAsState().value,
                onValueChange = { viewModel.updateInputSignInId(it) },
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
                .height(40.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
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
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    text = "로그인"
                )
            }
        }
        val annotatedString = buildAnnotatedString {
            append("아직 계정이 없으신가요?  ")
            withStyle(
                style = SpanStyle(
                    color = Color.Red
                )
            ) {
                pushStringAnnotation(tag = "SignUp", annotation = "SignUp")
                append("회원가입")
            }
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        ClickableText(
            text = annotatedString,
            onClick = {
                offset ->
                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let { span ->
                    viewModel.updateIsSigningIn(false)
                }
            }
        )
    }
}