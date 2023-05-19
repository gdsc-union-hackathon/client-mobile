package com.harang.gdsc_union.ui.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        const val BASE_URL = "https://ec322d8e-041e-4c0d-afd9-d4cfebab274a.mock.pstmn.io"
//        const val BASE_URL = "http://43.202.36.2:8080"

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: GdscUnionApiService by lazy {
            retrofit.create(GdscUnionApiService::class.java)
        }
    }
}