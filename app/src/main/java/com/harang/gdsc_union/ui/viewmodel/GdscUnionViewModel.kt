package com.harang.gdsc_union.ui.viewmodel

import android.app.Application
import android.provider.Settings.Global
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harang.gdsc_union.ui.data.GlobalConstants
import com.harang.gdsc_union.ui.data.Posting
import com.harang.gdsc_union.ui.data.TeacherInfo
import com.harang.gdsc_union.ui.data.ViewType
import com.harang.gdsc_union.ui.network.RetrofitService.Companion.retrofitService
import com.harang.gdsc_union.ui.network.message.GetPostingsResponse
import com.harang.gdsc_union.ui.network.message.GetTeachersListResponse
import com.harang.gdsc_union.ui.network.message.PostRequest
import com.harang.gdsc_union.ui.network.message.PostResponse
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
    private val _isMyPostingShowing = MutableStateFlow(false)
    val isMyPostingShowing: StateFlow<Boolean> = _isMyPostingShowing
    private val _mySelectedPosting = MutableStateFlow(Posting(0, ", ", "", "", false))
    val mySelectedPostingContent: StateFlow<Posting> = _mySelectedPosting

    fun updateMySelectedPosting(content: Posting) {
        _mySelectedPosting.update {content}
    }
    fun updateIsMyPostingShowing(isShowing: Boolean) {
        _isMyPostingShowing.update { isShowing }
    }

    private val _loginId = MutableStateFlow("")
    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName
    private val _userPhoneNumber = MutableStateFlow("")
    val userPhoneNumber: StateFlow<String> = _userPhoneNumber
    private val _isUserTeacher = MutableStateFlow(true)
    val isUserTeacher: StateFlow<Boolean> = _isUserTeacher
    private val _userWorkPlace = MutableStateFlow("")
    val userWorkPlace: StateFlow<String> = _userWorkPlace
    private val _postings = MutableStateFlow<ArrayList<Posting>>(arrayListOf())
    val postings: StateFlow<ArrayList<Posting>> = _postings
    private val _myHistory = MutableStateFlow<ArrayList<Posting>>(arrayListOf())
    val myHistory: StateFlow<ArrayList<Posting>> = _myHistory

    fun updateIsUserTeacher(isTeacher: Boolean) {
        _isUserTeacher.update { isTeacher }
    }

    private val _splashScreenTimer = MutableStateFlow(4)
    val splashScreenTimer: StateFlow<Int> = _splashScreenTimer

    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn
    private val _isSigningIn = MutableStateFlow(true)
    val isSigningIn: StateFlow<Boolean> = _isSigningIn
    private val _isShowingTeacherProfile = MutableStateFlow(false)
    val isShowingTeacherProfile: StateFlow<Boolean> = _isShowingTeacherProfile

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

    fun updateIsShowingTeacherProfile(isShowing: Boolean) {
        _isShowingTeacherProfile.update { isShowing }
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
    private val _selectedTeacherId = MutableStateFlow("")
    val selectedTeacherId: StateFlow<String> = _selectedTeacherId
    private val _selectedTeacherName = MutableStateFlow("")
    val selectedTeacherName: StateFlow<String> = _selectedTeacherName
    private val _selectedTeacherWorkPlace = MutableStateFlow("")
    val selectedTeacherWorkPlace: StateFlow<String> = _selectedTeacherWorkPlace
    private val _searchedTeachersList = MutableStateFlow<ArrayList<TeacherInfo>>(arrayListOf())
    val searchedTeachersList: StateFlow<ArrayList<TeacherInfo>> = _searchedTeachersList
    private val _isWritingPost = MutableStateFlow(false)
    val isWritingPost: StateFlow<Boolean> = _isWritingPost
    private val _postingContent = MutableStateFlow("")
    val postingContent: StateFlow<String> = _postingContent

    fun updatePostingContent(content: String) {
        _postingContent.update { content }
    }

    fun updateIsWritingPost(isWriting: Boolean) {
        _isWritingPost.update { isWriting }
    }

    fun updateInputTeacherName(name: String) {
        _inputTeacherName.update { name }
    }

    fun updateSelectedTeacher(id: String, name: String, workPlace: String?) {
        _selectedTeacherId.update { id }
        _selectedTeacherName.update { name }
        _selectedTeacherWorkPlace.update { workPlace ?: "" }
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
                _loginId.update { signUpResponse.loginId }
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
                    if(_inputSignInId.value == "teacher") {
                        _loginId.update { "teacher" }
                        _userName.update { "최영재" }
                        _isUserTeacher.update { true }
                        _userWorkPlace.update { signInResponse.data.workPlace ?: "동문고등학교" }
                        _selectedTeacherName.update { "최영재" }
                        _selectedTeacherWorkPlace.update { "동문고등학교" }
                    } else {
                        _loginId.update { "student" }
                        _userName.update { "유준성" }
                        _isUserTeacher.update { false }
                        _userWorkPlace.update { signInResponse.data.workPlace ?: "" }
                    }

                    _userPhoneNumber.update { signInResponse.data.phoneNum }
//                    _isUserTeacher.update { when(signInResponse.data.status) {
//                        "학생" -> {
//                            getMyHistory()
//                            false
//                        }
//                        else -> true
//                    } }
                    getMyHistory()
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
                Log.e("getTeachersList", GlobalConstants.BASE_URL + "/user/search/${_inputTeacherName.value}")
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
            if(getTeachersListResponse != null) {
                _searchedTeachersList.update {
                    val arrList = ArrayList<TeacherInfo>()
                    for(info in getTeachersListResponse.data) {
                        val teacherInfo = TeacherInfo(
                            userId = info.userId,
                            name = info.name,
                            phoneNum = info.phoneNum,
                            loginId = info.loginId,
                            workPlace = info.workPlace,
                            status = info.status
                        )
                        arrList.add(teacherInfo)
                    }
                    arrList
                }
            } else {
                Toast.makeText(getApplication(), "선생님 정보 불러오기 실패\n다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getTeacherProfile() {
        viewModelScope.launch {

        }
    }

    fun post() {
        viewModelScope.launch {
            val postRequest = PostRequest(
                loginId = _userId.value,
                content = _postingContent.value,
                teacherLoginId = _selectedTeacherId.value,
                writer = _userName.value
            )
            val postResponse: PostResponse? = try {
                retrofitService.post(postRequest)
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("getTeachersList", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("getTeachersList", "HttpException")
                null
            }
            if(postResponse != null) {Toast.makeText(getApplication(), "전송 완료!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getApplication(), "업로드 실패\n다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMyHistory() {
        viewModelScope.launch {
            val getPostingsResponse: GetPostingsResponse? = try {
                Log.e("getMyHistory", GlobalConstants.BASE_URL + "/post/main/" + "${_loginId.value}")
                retrofitService.getPostings(GlobalConstants.BASE_URL + "/post/main/" + "${_loginId.value}")
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("getMyHistory", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("getMyHistory", "HttpException")
                null
            }
            Log.e("getMyHistory", "${getPostingsResponse}")
            if(getPostingsResponse != null) {
                _myHistory.update {
                    val arrList = ArrayList<Posting>()
                    for(posting in getPostingsResponse.data) {
                        val post = Posting(
                            postId = posting.postId,
                            writer = posting.writer,
                            content = posting.content,
                            teacherLoginId = posting.teacherLoginId,
                            checkRead = posting.checkRead
                        )
                        arrList.add(post)
                    }
                    arrList
                }
            } else {

            }
        }
    }

    fun getPostingsList() {
        viewModelScope.launch {
            val getPostingsResponse: GetPostingsResponse? = try {
                retrofitService.getPostings(GlobalConstants.BASE_URL + "/${_selectedTeacherId.value}")
            } catch(e: IOException) {
                e.printStackTrace()
                Log.e("getTeachersList", "IOException")
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                Log.e("getTeachersList", "HttpException")
                null
            }
            if(getPostingsResponse != null) {
                _postings.update {
                    val arrList = ArrayList<Posting>()
                    for(posting in getPostingsResponse.data) {
                        val post = Posting(
                            postId = posting.postId,
                            writer = posting.writer,
                            content = posting.content,
                            teacherLoginId = posting.teacherLoginId,
                            checkRead = posting.checkRead
                        )
                        arrList.add(post)
                    }
                    arrList
                }
            } else {

            }
        }
    }

    init {
        showSplashScreen()
    }
}