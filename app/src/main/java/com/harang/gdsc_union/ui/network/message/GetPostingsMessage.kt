package com.harang.gdsc_union.ui.network.message

data class GetPostingsResponse (
    val success: Boolean,
    val status: Int,
    val data: ArrayList<GetPostingsData>
)

data class GetPostingsData(
    val postId: Int,
    val writer: String,
    val content: String,
    val teacherLoginId: String,
    val checkRead: Boolean
)