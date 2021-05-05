package com.muz1i.diseasereportandroid.repository

import com.muz1i.diseasereportandroid.api.RetrofitClient
import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.bean.PageHelperData
import com.muz1i.diseasereportandroid.bean.ResultData

/**
 * @author: Muz1i
 * @date: 2021/5/4
 */
class DoctorRepository {
    suspend fun getDoctorList(
        pageNum: Int,
        pageSize: Int
    ): ResultData<PageHelperData<DoctorInfoData>> {
        return RetrofitClient.doctorApiService.getDoctorList(pageNum, pageSize)
    }

    suspend fun getDoctorDetail(id: Int): ResultData<DoctorInfoData> {
        return RetrofitClient.doctorApiService.getDoctorDetail(id)
    }

    suspend fun deleteDoctor(id: Int): ResultData<Unit> {
        return RetrofitClient.doctorApiService.deleteDoctor(id)
    }

    suspend fun editDoctor(doctorInfoData: DoctorInfoData): ResultData<Unit> {
        return RetrofitClient.doctorApiService.editDoctor(doctorInfoData)
    }

    suspend fun createDoctor(doctorInfoData: DoctorInfoData): ResultData<Unit> {
        return RetrofitClient.doctorApiService.createDoctor(doctorInfoData)
    }
}
