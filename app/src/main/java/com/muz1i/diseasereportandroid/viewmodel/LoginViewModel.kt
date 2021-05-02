package com.muz1i.diseasereportandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseViewModel
import com.muz1i.diseasereportandroid.bean.LoginData
import com.muz1i.diseasereportandroid.bean.ResultData
import com.muz1i.diseasereportandroid.repository.LoginRepository
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.utils.ToastUtils

/**
 * @author: Muz1i
 * @date: 2021/4/24
 */
class LoginViewModel : BaseViewModel() {

    private val repository by lazy {
        LoginRepository()
    }

    //登陆结果
    val loginResult by lazy {
        MutableLiveData<ResultData<Int>>()
    }

    //id
    val id by lazy {
        MutableLiveData<String>()
    }

    //password
    val password by lazy {
        MutableLiveData<String>()
    }

    val identity by lazy {
        val mutableLiveData = MutableLiveData<Int>()
        mutableLiveData.value = R.id.user_permission
        mutableLiveData
    }

    fun login() {
        //更新状态
        loadState.value = LoadState.LOADING
        catchEx({
            val loginData = LoginData(id.value!!.toLong(), password.value.toString())
            val result = when (identity.value) {
                R.id.user_permission -> repository.userLogin(loginData)
                R.id.doctor_permission -> repository.doctorLogin(loginData)
                R.id.admin_permission -> repository.adminLogin(loginData)
                else -> null
            }
            loginResult.value = result
            loadState.value = LoadState.SUCCESS
        }, {
            loadState.value = LoadState.ERROR
            ToastUtils.showToast("网络异常，请稍后重试")
        })
    }
}
