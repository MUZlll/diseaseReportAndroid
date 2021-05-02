package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData
import com.muz1i.diseasereportandroid.bean.UserInfoData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author: Muz1i
 * @date: 2021/4/28
 */
interface UserApiService {

    @GET("user/get")
    suspend fun getUserInfo(@Query("studentNum") stuNum: String): ResultData<UserInfoData>

    @GET("user/get/list")
    suspend fun getUserList(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): ResultData<PageHelperData<UserInfoData>>

    @POST("user/edit")
    suspend fun editUserInfo(@Body userInfoData: UserInfoData): ResultData<Int>
}
