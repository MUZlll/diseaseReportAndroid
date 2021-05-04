package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.LoginData
import com.muz1i.diseasereportandroid.bean.ResultData

/**
 * @author: Muz1i
 * @date: 2021/4/24
 */
class LoginRepository {

    suspend fun userLogin(loginData: LoginData): ResultData<Unit> {
        return RetrofitClient.loginApiService.userLogin(loginData)
    }

    suspend fun doctorLogin(loginData: LoginData): ResultData<Unit> {
        return RetrofitClient.loginApiService.doctorLogin(loginData)
    }

    suspend fun adminLogin(loginData: LoginData): ResultData<Unit> {
        return RetrofitClient.loginApiService.adminLogin(loginData)
    }
}
