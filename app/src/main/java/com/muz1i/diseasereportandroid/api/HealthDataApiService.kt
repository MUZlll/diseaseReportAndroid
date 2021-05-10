package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.HealthData
import com.muz1i.diseasereportandroid.bean.ResultData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: Muz1i
 * @date: 2021/5/9
 */
interface HealthDataApiService {
    @GET("health/data")
    suspend fun getSchoolData(@Query("day") day: String): ResultData<List<HealthData>>

    @GET("health/institute")
    suspend fun getDataByInstitute(@Query("institute") institute: String): ResultData<List<HealthData>>

    @GET("health/day")
    suspend fun getDataByDay(@Query("day") day: String): ResultData<List<HealthData>>
}
