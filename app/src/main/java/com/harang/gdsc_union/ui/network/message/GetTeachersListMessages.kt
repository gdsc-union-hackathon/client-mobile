package com.harang.gdsc_union.ui.network.message

data class GetTeachersListResponse (
    val success: Boolean,
    val status: Int,
    val data: ArrayList<GetTeachersListData>,
    val timeStamp: String
)

data class GetTeachersListData (
    val userId: Int,
    val name: String,
    val phoneNum: String,
    val loginId: String,
    val workPlace: String?,
    val status: String
)