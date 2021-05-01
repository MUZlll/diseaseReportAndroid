package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData
import com.muz1i.diseasereportandroid.bean.UserInfoData

/**
 * @author: Muz1i
 * @date: 2021/4/28
 */
class UserRepository {
    suspend fun getUserInfo(stuNum: String): ResultData<UserInfoData> {
        return RetrofitClient.userApiService.getUserInfo(stuNum)
    }

    suspend fun getUserList(pageNum: Int, pageSize: Int): ResultData<PageHelperData<UserInfoData>> {
        return RetrofitClient.userApiService.getUserList(pageNum, pageSize)
    }
}
