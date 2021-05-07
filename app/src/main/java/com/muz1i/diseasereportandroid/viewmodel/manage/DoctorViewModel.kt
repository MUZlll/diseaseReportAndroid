package com.muz1i.diseasereportandroid.viewmodel.manage

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.repository.DoctorRepository
import com.muz1i.diseasereportandroid.utils.LoadState

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class DoctorViewModel : BaseViewModel() {

    private val doctorRepository by lazy {
        DoctorRepository()
    }

    val doctorList by lazy {
        MutableLiveData<List<DoctorInfoData>>()
    }

    val doctorDetail by lazy {
        MutableLiveData<DoctorInfoData>()
    }

    fun getDoctorList(pageNum: Int, pageSize: Int) {
        catchEx({
            val result = doctorRepository.getDoctorList(pageNum, pageSize).getResultData()
            doctorList.value = result.list
        }, {
            loadState.value = LoadState.ERROR
        })
    }

    fun getDoctorDetail(id: Int) {
        catchEx({
            val result = doctorRepository.getDoctorDetail(id).getResultData()
            doctorDetail.value = result
        })
    }

    fun deleteDoctor(id: Int) {
        catchEx({
            val result = doctorRepository.deleteDoctor(id)
            deleteSuccess.value = result.success
        }, {
            deleteSuccess.value = false
        })
    }

    fun editDoctorInfo(doctorInfo: DoctorInfoData) {
        catchEx({
            val result = doctorRepository.editDoctor(doctorInfo)
            editSuccess.value = result.success
        }, {
            editSuccess.value = false
        })
    }

    fun addDoctor(doctorInfo: DoctorInfoData) {
        catchEx({
            val result = doctorRepository.createDoctor(doctorInfo)
            addSuccess.value = result.success
        }, {
            addSuccess.value = false
        })
    }
}
