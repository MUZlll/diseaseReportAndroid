package com.muz1i.diseasereportandroid.viewmodel.healthdata

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.HealthData
import com.muz1i.diseasereportandroid.repository.HealthDataRepository

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class HealthDataViewModel : BaseViewModel() {
    val repository by lazy {
        HealthDataRepository()
    }

    val schoolData by lazy {
        MutableLiveData<List<HealthData>>()
    }

    val dataByInstitute by lazy {
        MutableLiveData<List<HealthData>>()
    }

    val dataByDay by lazy {
        MutableLiveData<List<HealthData>>()
    }

    fun getSchoolData(day: String) {
        catchEx({
            val result = repository.getSchoolData(day).getResultData()
            schoolData.value = result
        })
    }

    fun getDataByInstitute(institute: String) {
        catchEx({
            val result = repository.getDataByInstitute(institute).getResultData()
            dataByInstitute.value = result
        })
    }

    fun getDataByDay(day: String) {
        catchEx({
            val result = repository.getDataByDay(day).getResultData()
            dataByDay.value = result
        })
    }
}
