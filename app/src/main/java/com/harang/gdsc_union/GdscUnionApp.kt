package com.harang.gdsc_union

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.harang.gdsc_union.ui.content.BottomBar
import com.harang.gdsc_union.ui.content.GdscUnionContent
import com.harang.gdsc_union.ui.content.TopBar
import com.harang.gdsc_union.ui.data.NavigationItemContent
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.screen.SignInScreen
import com.harang.gdsc_union.ui.screen.SignUpScreen
import com.harang.gdsc_union.ui.screen.SplashScreen
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
            icon = R.drawable.baseline_person_36,
            text = "MyPage"
        ),
        NavigationItemContent(
            viewType = ViewType.Search,
            icon = R.drawable.baseline_search_36,
            text = "Search"
        )
    )
    if(viewModel.splashScreenTimer.collectAsState().value > 0) {
        SplashScreen()
    } else {
        if(viewModel.isSignedIn.collectAsState().value) {
            Scaffold(
                topBar = {
                    TopBar(
                        when(viewModel.uiState.collectAsState().value) {
                            ViewType.MyPage -> "내 활동"
                            else -> "스승님 검색"
                        }
                    )
                },
                bottomBar = {
                    BottomBar(
                        viewModel = viewModel,
                        navigationItemContentList
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    GdscUnionContent(
                        viewModel = viewModel
                    )
                }
            }
        } else {
            if(viewModel.isSigningIn.collectAsState().value) {
                SignInScreen(
                    viewModel = viewModel
                )
            } else {
                SignUpScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}