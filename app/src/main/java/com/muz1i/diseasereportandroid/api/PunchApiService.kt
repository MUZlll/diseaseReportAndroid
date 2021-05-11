package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.PunchData
import com.muz1i.diseasereportandroid.bean.PunchTableData
import com.muz1i.diseasereportandroid.bean.ResultData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author: Muz1i
 * @date: 2021/5/11
 */
interface PunchApiService {
    @GET("punch/isPunched")
    suspend fun isPunched(@Query("id") id: String): ResultData<Boolean>

    @GET("punch/items")
    suspend fun getPunchItems(): ResultData<List<String>>

    @POST("punch/insert")
    suspend fun punch(@Body punchData: List<String>): ResultData<Unit>

    @GET("punch/list")
    suspend fun getPunchList(): ResultData<List<PunchTableData>>

    @POST("punch/publish")
    suspend fun publicPunchTask(@Body punchItems: List<String>): ResultData<Unit>

    @GET("punch/delete")
    suspend fun deletePunchTask(@Query("tableName") tableName: String): ResultData<Unit>

    @GET("punch/punches")
    suspend fun getPunchData(@Query("tableName") tableName: String): ResultData<PunchData>
}
