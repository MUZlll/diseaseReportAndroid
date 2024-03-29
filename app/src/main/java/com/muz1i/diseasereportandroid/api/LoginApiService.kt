package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.LoginData
import com.muz1i.diseasereportandroid.bean.ResultData
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
interface LoginApiService {
    @POST("user/login")
    suspend fun userLogin(@Body loginData: LoginData): ResultData<Unit>

    @POST("doctor/login")
    suspend fun doctorLogin(@Body loginData: LoginData): ResultData<Unit>

    @POST("admin/login")
    suspend fun adminLogin(@Body loginData: LoginData): ResultData<Unit>
}
