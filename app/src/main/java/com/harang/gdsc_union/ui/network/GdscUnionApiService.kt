package com.harang.gdsc_union.ui.network

import com.harang.gdsc_union.ui.network.message.SignUpRequest
import com.harang.gdsc_union.ui.network.message.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GdscUnionApiService {
    @POST("/test1")
    suspend fun test1(
        @Body body: SignUpRequest
    ): SignUpResponse
}