package com.harang.gdsc_union.ui.network

import android.provider.Settings.Global
import com.harang.gdsc_union.ui.data.GlobalConstants
import com.harang.gdsc_union.ui.network.message.GetPostingsResponse
import com.harang.gdsc_union.ui.network.message.GetTeachersListResponse
import com.harang.gdsc_union.ui.network.message.PostRequest
import com.harang.gdsc_union.ui.network.message.PostResponse
import com.harang.gdsc_union.ui.network.message.SignInRequest
import com.harang.gdsc_union.ui.network.message.SignInResponse
import com.harang.gdsc_union.ui.network.message.SignUpRequest
import com.harang.gdsc_union.ui.network.message.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface GdscUnionApiService {
    @POST("/user/signup")
    suspend fun signUp(
        @Body body: SignUpRequest
    ): SignUpResponse?

    @POST("/user/signin")
    suspend fun signIn(
        @Body body: SignInRequest
    ): SignInResponse?

    @GET
    suspend fun getTeachersList(
        @Url path: String
    ): GetTeachersListResponse?

    @POST("/post/create")
    suspend fun post(
        @Body body: PostRequest
    ): PostResponse?

    @GET
    suspend fun getPostings(
        @Url path: String
    ): GetPostingsResponse?
}