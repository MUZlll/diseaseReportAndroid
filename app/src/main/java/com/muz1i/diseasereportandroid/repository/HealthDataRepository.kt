package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.HealthData
import com.muz1i.diseasereportandroid.bean.ResultData

/**
 * @author: Muz1i
 * @date: 2021/5/9
 */
class HealthDataRepository {
    suspend fun getSchoolData(day: String): ResultData<List<HealthData>> {
        return RetrofitClient.healthDataApiService.getSchoolData(day)
    }
    
    suspend fun getDataByInstitute(institute: String): ResultData<List<HealthData>> {
        return RetrofitClient.healthDataApiService.getDataByInstitute(institute)
    }

    suspend fun getDataByDay(day: String): ResultData<List<HealthData>> {
        return RetrofitClient.healthDataApiService.getDataByDay(day)
    }
}
