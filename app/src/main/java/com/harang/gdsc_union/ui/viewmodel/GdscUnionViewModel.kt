package com.harang.gdsc_union.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.network.RetrofitService.Companion.retrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GdscUnionViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ViewType.MyPage)
    val uiState: StateFlow<ViewType> = _uiState

    fun updateUiState(viewType: ViewType) {
        _uiState.update { viewType }
    }

    fun signIn() {
        viewModelScope.launch {

        }
    }
}