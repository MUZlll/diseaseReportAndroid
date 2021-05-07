package com.muz1i.diseasereportandroid.api

import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
interface DiseaseCaseApiService {
    @POST("case/list/get")
    suspend fun getDiseaseCaseList(
        @Query("disease_level") level: String?,
        @Query("institute") institute: String?,
        @Query("page_no") pageNum: Int,
        @Query("page_size") pageSize: Int
    ): ResultData<PageHelperData<DiseaseCaseData>>

    @POST("case/add/request")
    suspend fun addDiseaseCase(@Body diseaseCaseData: DiseaseCaseData): ResultData<Unit>

    @POST("case/edit/request")
    suspend fun editDiseaseCase(@Body diseaseCaseData: DiseaseCaseData): ResultData<Unit>

    @GET("case/delete")
    suspend fun deleteDiseaseCase(@Query("id") id: Int): ResultData<Unit>
}
