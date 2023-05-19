package com.harang.gdsc_union.ui.data

data class Posting(
    val postId: Int,
    val writer: String,
    val content: String,
    val teacherLoginId: String,
    val checkRead: Boolean
)
