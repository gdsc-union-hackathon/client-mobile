package com.harang.gdsc_union.ui.network.message

data class SignInRequest (
    val loginId: String
)

data class SignInResponse (
    val success: Boolean,
    val status: Int,
    val data: SignInData,
    val timeStamp: String,
    val error: String,
    val path: String
)

data class SignInData (
    val userId: Int,
    val name: String,
    val phoneNum: String,
    val loginId: String,
    val workPlace: String?,
    val status: String
)