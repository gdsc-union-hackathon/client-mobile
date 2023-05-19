package com.harang.gdsc_union.ui.network.message

data class SignUpRequest (
    val loginId: String,
    val name: String,
    val phoneNum: String,
    val status: String,
    val workPlace: String
)

data class SignUpResponse (
    val userId: Int,
    val loginId: String,
    val name: String,
    val phoneNum: String,
    val status: String,
    val workPlace: String
)