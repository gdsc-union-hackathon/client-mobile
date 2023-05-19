package com.harang.gdsc_union

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.harang.gdsc_union.ui.theme.GdscunionTheme
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GdscUnionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.signUp()
//        val bottomNavigationBarHeight = resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"))
//        Log.e("", "$bottomNavigationBarHeight")
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = true
        setContent {
            GdscunionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setStatusBarColor(
                        color = Color(0xffffffff),
                        darkIcons = true
                    )
                    Column() {
//                        Spacer(
//                            modifier = Modifier
//                                .height(30.dp)
//                        )
                        GdscUnionApp(
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}