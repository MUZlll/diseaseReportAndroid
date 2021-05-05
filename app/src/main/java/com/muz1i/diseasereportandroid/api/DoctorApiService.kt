package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author: Muz1i
 * @date: 2021/5/4
 */
interface DoctorApiService {
    @GET("doctor/get/list")
    suspend fun getDoctorList(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): ResultData<PageHelperData<DoctorInfoData>>

    @GET("doctor/get")
    suspend fun getDoctorDetail(@Query("id") id: Int): ResultData<DoctorInfoData>

    @GET("doctor/delete")
    suspend fun deleteDoctor(@Query("id") id: Int): ResultData<Unit>

    @POST("doctor/edit")
    suspend fun editDoctor(@Body doctorInfoData: DoctorInfoData): ResultData<Unit>
    
    @POST("doctor/create")
    suspend fun createDoctor(@Body doctorInfoData: DoctorInfoData): ResultData<Unit>

}
