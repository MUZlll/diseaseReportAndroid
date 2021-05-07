package com.muz1i.diseasereportandroid.viewmodel.diseasecase

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.repository.DiseaseRepository

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class DiseaseCaseViewModel : BaseViewModel() {

    private val repository by lazy {
        DiseaseRepository()
    }

    val diseaseCaseList by lazy {
        MutableLiveData<List<DiseaseCaseData>>()
    }

    fun getDiseaseCaseList(level: String?, institute: String?, pageNum: Int, pageSize: Int) {
        catchEx({
            val result =
                repository.getDiseaseCaseList(level, institute, pageNum, pageSize).getResultData()
            diseaseCaseList.value = result.list
        })
    }

    fun addDiseaseCase(diseaseCaseData: DiseaseCaseData) {
        catchEx({
            val result = repository.addDiseaseCase(diseaseCaseData)
            addSuccess.value = result.success
        }, {
            addSuccess.value = false
        })
    }

    fun editDiseaseCase(diseaseCaseData: DiseaseCaseData) {
        catchEx({
            val result = repository.editDiseaseCase(diseaseCaseData)
            editSuccess.value = result.success
        }, {
            editSuccess.value = false
        })
    }

    fun deleteDiseaseCase(id: Int) {
        catchEx({
            val result = repository.deleteDiseaseCase(id)
            deleteSuccess.value = result.success
        }, {
            deleteSuccess.value = false
        })
    }
}
