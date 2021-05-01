package com.muz1i.diseasereportandroid.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
object RetrofitClient {
    private const val BASE_URL = "http://1.14.154.171:8001/diseasereport/"

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val loginApiService: LoginApiService = retrofit.create(LoginApiService::class.java)
    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
}
