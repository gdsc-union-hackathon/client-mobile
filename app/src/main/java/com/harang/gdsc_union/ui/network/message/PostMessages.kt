package com.harang.gdsc_union.ui.network.message

data class PostRequest (
    val loginId: String,
    val content: String,
    val teacherLoginId: String,
    val writer: String
)

data class PostResponse (
    val success: Boolean,
    val status: Int,
    val data: PostData,
    val timeStamp: String
)

data class PostData(
    val postId: Int,
    val writer: String,
    val content: String,
    val teacherLoginId: String,
    val checkRead: Boolean
)