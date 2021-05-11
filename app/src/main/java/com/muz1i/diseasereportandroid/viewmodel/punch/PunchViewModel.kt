package com.muz1i.diseasereportandroid.viewmodel.punch

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.PunchData
import com.muz1i.diseasereportandroid.bean.PunchTableData
import com.muz1i.diseasereportandroid.repository.PunchRepository
import com.muz1i.diseasereportandroid.utils.LoadState

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class PunchViewModel : BaseViewModel() {
    private val repository by lazy {
        PunchRepository()
    }

    val isPunched by lazy {
        MutableLiveData<Boolean>()
    }

    val punchItems by lazy {
        MutableLiveData<List<String>>()
    }

    val punchSuccess by lazy {
        MutableLiveData<Boolean>()
    }

    val punchList by lazy {
        MutableLiveData<List<PunchTableData>>()
    }

    val publicTaskSuccess by lazy {
        MutableLiveData<Boolean>()
    }

    val deleteTaskSuccess by lazy {
        MutableLiveData<Boolean>()
    }

    val punchData by lazy {
        MutableLiveData<PunchData>()
    }

    fun isPunched(id: String) {
        catchEx({
            val result = repository.isPunched(id).getResultData()
            isPunched.value = result
        })
    }

    fun getPunchItems() {
        loadState.value = LoadState.LOADING
        catchEx({
            val result = repository.getPunchItems().getResultData()
            punchItems.value = result
            loadState.value = LoadState.SUCCESS
        })
    }

    fun punch(punchData: List<String>) {
        catchEx({
            val result = repository.punch(punchData)
            punchSuccess.value = result.success
        })
    }

    fun getPunchList() {
        catchEx({
            val result = repository.getPunchList().getResultData()
            punchList.value = result
        })
    }

    fun publicPunchTask(punchItems: List<String>) {
        catchEx({
            val result = repository.publicPunchTask(punchItems)
            publicTaskSuccess.value = result.success
        })
    }

    fun deletePunchTask(tableName: String) {
        catchEx({
            val result = repository.deletePunchTask(tableName)
            deleteTaskSuccess.value = result.success
        })
    }

    fun getPunchData(tableName: String) {
        catchEx({
            val result = repository.getPunchData(tableName).getResultData()
            punchData.value = result
        })
    }
}
