package com.muz1i.diseasereportandroid.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muz1i.diseasereportandroid.utils.LoadState
import kotlinx.coroutines.launch

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
open class BaseViewModel : ViewModel() {
    //加载状态
    val loadState by lazy {
        MutableLiveData<LoadState>()
    }

    fun catchEx(tryMethod: suspend () -> Unit, catchMethod: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                tryMethod()
            } catch (ex: Exception) {
                catchMethod()
            }
        }
    }
}
