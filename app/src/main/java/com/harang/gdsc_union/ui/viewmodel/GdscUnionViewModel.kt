package com.harang.gdsc_union.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.network.RetrofitService.Companion.retrofitService
import com.harang.gdsc_union.ui.network.message.SignUpRequest
import com.harang.gdsc_union.ui.network.message.SignUpResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GdscUnionViewModel(application: Application) : AndroidViewModel(application) {

    private val _isTeacher = MutableStateFlow(true)
    val isTeacher: StateFlow<Boolean> = _isTeacher

    fun updateIsTeacher(isTeacher: Boolean) {
        _isTeacher.update { isTeacher }
    }

    private val _splashScreenTimer = MutableStateFlow(2)
    val splashScreenTimer: StateFlow<Int> = _splashScreenTimer

    private val _isSignedIn = MutableStateFlow(true)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn

    private val _isSigningIn = MutableStateFlow(true)
    val isSigningIn: StateFlow<Boolean> = _isSigningIn

    private fun showSplashScreen() {
        viewModelScope.launch {
            while(_splashScreenTimer.value > 0) {
                _splashScreenTimer.update { it - 1 }
                delay(1000)
            }
        }
    }

    fun updateIsSigningIn(signingIn: Boolean) {
        _isSigningIn.update { signingIn }
    }

    // Sign Up Screen
    private val _inputSignUpId = MutableStateFlow("")
    val inputSignUpId: StateFlow<String> = _inputSignUpId
    private val _inputName = MutableStateFlow("")
    val inputName: StateFlow<String> = _inputName
    private val _inputPhoneNumber = MutableStateFlow("")
    val inputPhoneNumber: StateFlow<String> = _inputPhoneNumber
    // 0: None, 1: Teacher, 2: Student
    private val _selectedType = MutableStateFlow(0)
    val selectedType: StateFlow<Int> = _selectedType
    private val _inputWorkPlace = MutableStateFlow("")
    val inputWorkPlace: StateFlow<String> = _inputWorkPlace

    fun updateInputSignUpId(id: String) {
        _inputSignUpId.update { id }
    }

    fun updateInputName(name: String) {
        _inputName.update { name }
    }

    fun updateInputPhoneNumber(number: String) {
        _inputPhoneNumber.update { number }
    }

    fun updateSelectedType(type: Int) {
        _selectedType.update { type }
    }

    fun updateInputWorkPlace(workPlace: String) {
        _inputWorkPlace.update { workPlace }
    }

    // Sign In Screen
    private val _inputSignInId = MutableStateFlow("")
    val inputSignInId: StateFlow<String> = _inputSignInId

    fun updateInputSignInId(id: String) {
        _inputSignInId.update { id }
    }

    private val _uiState = MutableStateFlow(ViewType.MyPage)
    val uiState: StateFlow<ViewType> = _uiState

    // Search Screen
    private val _inputTeacherName = MutableStateFlow("")
    val inputTeacherName: StateFlow<String> = _inputTeacherName

    fun updateInputTeacherName(name: String) {
        _inputTeacherName.update { name }
    }

    fun updateUiState(viewType: ViewType) {
        _uiState.update { viewType }
    }

    fun signUp() {
        viewModelScope.launch {
            val signUpRequest = SignUpRequest(1)
            val signUpResponse: SignUpResponse = try {
                retrofitService.test1(signUpRequest)
            } catch(e: IOException) {
                e.printStackTrace()
                SignUpResponse(400)
            } catch(e: HttpException) {
                e.printStackTrace()
                SignUpResponse(400)
            }
            Log.e("signUpResponse", "${signUpResponse.value1}")
        }
    }

    init {
        showSplashScreen()
    }
}