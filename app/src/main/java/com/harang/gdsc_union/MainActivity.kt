package com.harang.gdsc_union

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.harang.gdsc_union.ui.theme.GdscunionTheme
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

class MainActivity : ComponentActivity() {

    val viewModel: GdscUnionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GdscunionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GdscUnionApp(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}