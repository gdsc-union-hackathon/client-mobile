package com.harang.gdsc_union.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.harang.gdsc_union.ui.data.GlobalConstants
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.network.RetrofitService.Companion.retrofitService
import com.harang.gdsc_union.ui.network.message.GetTeachersListResponse
import com.harang.gdsc_union.ui.network.message.SignInRequest
import com.harang.gdsc_union.ui.network.message.SignInResponse
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

    // My Info
    private val _userId = MutableStateFlow(0)
    val userId: StateFlow<Int> = _userId
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName
    private val _userPhoneNumber = MutableStateFlow("")
    val userPhoneNumber: StateFlow<String> = _userPhoneNumber
    private val _isUserTeacher = MutableStateFlow(true)
    val isUserTeacher: StateFlow<Boolean> = _isUserTeacher
    private val _userWorkPlace = MutableStateFlow("")
    val userWorkPlace: StateFlow<String> = _userWorkPlace

    fun updateIsUserTeacher(isTeacher: Boolean) {
        _isUserTeacher.update { isTeacher }
    }

    private val _splashScreenTimer = MutableStateFlow(2)
    val splashScreenTimer: StateFlow<Int> = _splashScreenTimer

    private val _isSignedIn = MutableStateFlow(false)
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
    private val _inputIsTeacher = MutableStateFlow(false)
    val inputIsTeacher: StateFlow<Boolean> = _inputIsTeacher
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

    fun updateInputIsTeacher(type: Boolean) {
        _inputIsTeacher.update { type }
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
    fun updateUiState(viewType: ViewType) {
        _uiState.update { viewType }
    }

    // Search Screen
    private val _inputTeacherName = MutableStateFlow("")
    val inputTeacherName: StateFlow<String> = _inputTeacherName
    fun updateInputTeacherName(name: String) {
        _inputTeacherName.update { name }
    }



    fun signUp() {
        viewModelScope.launch {
            val signUpRequest = SignUpRequest(_inputSignUpId.value, _inputName.value, _inputPhoneNumber.value,
                when(_inputIsTeacher.value) {
                    true -> "선생님"
                    else -> "학생"
                }, _inputWorkPlace.value)
            val signUpResponse: SignUpResponse? = try {
                retrofitService.signUp(signUpRequest)
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("signUp", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("signUp", "HttpException")
                null
            }
            Log.e("signUpResponse", "${signUpResponse}")
            if(signUpResponse != null) {
                Toast.makeText(getApplication(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                _isSigningIn.update { true }
                _userId.update { signUpResponse.userId }
                _userName.update { signUpResponse.name }
                _userPhoneNumber.update { signUpResponse.phoneNum }
                _isUserTeacher.update { when(signUpResponse.status) {
                    "학생" -> false
                    else -> true
                } }
                _userWorkPlace.update { signUpResponse.workPlace }
            } else {
                Toast.makeText(getApplication(), "회원가입 실패\n다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            val signInRequest = SignInRequest(_inputSignInId.value)
            val signInResponse: SignInResponse? = try {
                retrofitService.signIn(signInRequest)
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("signIn", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("signIn", "HttpException")
                null
            }
            Log.e("signUpResponse", "${signInResponse}")
            if(signInResponse != null) {
                if(signInResponse.success) {
                    _isSigningIn.update { true }
                    _userId.update { signInResponse.data.userId }
                    _userName.update { signInResponse.data.name }
                    _userPhoneNumber.update { signInResponse.data.phoneNum }
                    _isUserTeacher.update { when(signInResponse.data.status) {
                        "학생" -> false
                        else -> true
                    } }
                    _userWorkPlace.update { signInResponse.data.workPlace ?: "" }
                    _isSignedIn.update { true }
                } else {
                    Toast.makeText(getApplication(), "존재하지 않는 아이디입니다\n다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(getApplication(), "로그인 실패\n다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getTeachersList() {
        viewModelScope.launch {
            val getTeachersListResponse: GetTeachersListResponse? = try {
                retrofitService.getTeachersList(GlobalConstants.BASE_URL + "/user/search/${_inputTeacherName.value}")
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("getTeachersList", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("getTeachersList", "HttpException")
                null
            }
            Log.e("getTeachersList", "${getTeachersListResponse}")
        }
    }

    init {
        showSplashScreen()
    }
}