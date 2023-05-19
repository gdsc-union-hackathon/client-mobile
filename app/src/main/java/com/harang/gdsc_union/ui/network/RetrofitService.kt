package com.harang.gdsc_union.ui.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private const val BASE_URL = "https://eaf07d91-a1e3-4579-b93f-3d9b1ea64956.mock.pstmn.io"

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: GdscUnionApiService by lazy {
            retrofit.create(GdscUnionApiService::class.java)
        }
    }
}