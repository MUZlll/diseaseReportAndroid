package com.muz1i.diseasereportandroid.viewmodel.manage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.repository.UserRepository
import com.muz1i.diseasereportandroid.utils.LoadState
import kotlinx.coroutines.launch

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class UserManageViewModel : BaseViewModel() {
    private val userRepository by lazy {
        UserRepository()
    }
    val userList by lazy {
        MutableLiveData<List<UserInfoData>>()
    }

    fun getUserList(pageNum: Int, pageSize: Int) {
        viewModelScope.launch {
            try {
                val result = userRepository.getUserList(pageNum, pageSize).getResultData()
                loadState.value = LoadState.SUCCESS
                userList.value = result.list
            } catch (ex: Exception) {
                loadState.value = LoadState.ERROR
            }
        }
    }
}
