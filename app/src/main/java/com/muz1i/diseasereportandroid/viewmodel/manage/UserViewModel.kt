package com.muz1i.diseasereportandroid.viewmodel.manage

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.repository.UserRepository
import com.muz1i.diseasereportandroid.utils.LoadState

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class UserViewModel : BaseViewModel() {

    private val userRepository by lazy {
        UserRepository()
    }

    val userList by lazy {
        MutableLiveData<List<UserInfoData>>()
    }

    val userDetail by lazy {
        MutableLiveData<UserInfoData>()
    }

    fun getUserList(pageNum: Int, pageSize: Int) {
        catchEx({
            val result = userRepository.getUserList(pageNum, pageSize).getResultData()
            loadState.value = LoadState.SUCCESS
            userList.value = result.list
        }, {
            loadState.value = LoadState.ERROR
        })
    }

    fun getUserDetail(stuNum: String) {
        catchEx({
            val result = userRepository.getUserInfo(stuNum).getResultData()
            userDetail.value = result
        })
    }

    fun editUserInfo(userInfoData: UserInfoData) {
        catchEx({
            val result = userRepository.editUserInfo(userInfoData)
            editSuccess.value = result.success
        }, {
            editSuccess.value = false
        })
    }

    fun deleteUser(id: Int) {
        catchEx({
            val result = userRepository.deleteUser(id)
            deleteSuccess.value = result.success
        }, {
            deleteSuccess.value = false
        })
    }

    fun addUser(userInfoData: UserInfoData) {
        catchEx({
            val result = userRepository.addUser(userInfoData)
            addSuccess.value = result.success
        }, {
            addSuccess.value = false
        })
    }
}
