package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.bean.DiseaseCaseSimple
import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
class DiseaseRepository {
    suspend fun getDiseaseCaseList(
        level: String?,
        institute: String?,
        pageNum: Int,
        pageSize: Int
    ): ResultData<PageHelperData<DiseaseCaseData>> {
        return RetrofitClient.diseaseCaseApiService.getDiseaseCaseList(
            level,
            institute,
            pageNum,
            pageSize
        )
    }

    suspend fun addDiseaseCase(diseaseCaseData: DiseaseCaseData): ResultData<Unit> {
        return RetrofitClient.diseaseCaseApiService.addDiseaseCase(diseaseCaseData)
    }

    suspend fun editDiseaseCase(diseaseCaseData: DiseaseCaseData): ResultData<Unit> {
        return RetrofitClient.diseaseCaseApiService.editDiseaseCase(diseaseCaseData)
    }

    suspend fun deleteDiseaseCase(id: Int): ResultData<Unit> {
        return RetrofitClient.diseaseCaseApiService.deleteDiseaseCase(id)
    }

    suspend fun getDiseaseCaseById(id: Int): ResultData<DiseaseCaseSimple> {
        return RetrofitClient.diseaseCaseApiService.getDiseaseCaseById(id)
    }
}
