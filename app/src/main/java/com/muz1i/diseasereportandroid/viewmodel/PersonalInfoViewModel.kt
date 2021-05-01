package com.muz1i.diseasereportandroid.viewmodel

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
class PersonalInfoViewModel : BaseViewModel() {
    private val userRepository by lazy {
        UserRepository()
    }

    val userInfo by lazy {
        MutableLiveData<UserInfoData>()
    }

    fun getUserInfo(stuNum: String) {
        loadState.value = LoadState.LOADING
        viewModelScope.launch {
            userInfo.value = userRepository.getUserInfo(stuNum).getResultData()
            loadState.value = LoadState.SUCCESS
        }
    }
}
