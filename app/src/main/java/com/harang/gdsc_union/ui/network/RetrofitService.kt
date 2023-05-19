package com.harang.gdsc_union.ui.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: GdscUnionApiService by lazy {
            retrofit.create(GdscUnionApiService::class.java)
        }
    }
}