package com.harang.gdsc_union.ui.content

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.harang.gdsc_union.ui.data.NavigationItemContent
import com.harang.gdsc_union.ui.viewmodel.GdscUnionViewModel

@Composable
fun BottomBar(
    viewModel: GdscUnionViewModel,
    navigationItemContentList: List<NavigationItemContent>
) {
    NavigationBar(
        modifier = Modifier
            .height(50.dp)
    ) {
        navigationItemContentList.forEachIndexed { _, navItem ->
            NavigationBarItem(
                selected = viewModel.uiState.collectAsState().value == navItem.viewType,
                onClick = {
                    viewModel.updateUiState(navItem.viewType)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navItem.icon),
                        contentDescription = navItem.text,
                        modifier = Modifier
                            .fillMaxSize(0.6f)
                    )
                }
            )
        }
    }
}