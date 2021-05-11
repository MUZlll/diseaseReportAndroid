package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.PunchData
import com.muz1i.diseasereportandroid.bean.PunchTableData
import com.muz1i.diseasereportandroid.bean.ResultData

/**
 * @author: Muz1i
 * @date: 2021/5/11
 */
class PunchRepository {
    suspend fun isPunched(id: String): ResultData<Boolean> {
        return RetrofitClient.punchApiService.isPunched(id)
    }

    suspend fun getPunchItems(): ResultData<List<String>> {
        return RetrofitClient.punchApiService.getPunchItems()
    }

    suspend fun punch(punchData: List<String>): ResultData<Unit> {
        return RetrofitClient.punchApiService.punch(punchData)
    }

    suspend fun getPunchList(): ResultData<List<PunchTableData>> {
        return RetrofitClient.punchApiService.getPunchList()
    }

    suspend fun publicPunchTask(punchItems: List<String>): ResultData<Unit> {
        return RetrofitClient.punchApiService.publicPunchTask(punchItems)
    }

    suspend fun deletePunchTask(tableName: String): ResultData<Unit> {
        return RetrofitClient.punchApiService.deletePunchTask(tableName)
    }

    suspend fun getPunchData(tableName: String): ResultData<PunchData> {
        return RetrofitClient.punchApiService.getPunchData(tableName)
    }
}
