package com.muz1i.diseasereportandroid.bean

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
data class ResultData<T>(val success: Boolean, val code: Int, val message: String, private val data: T) {
    companion object {
        const val SUCCESS_CODE = 20000
    }

    fun getResultData(): T {
//        if (code == SUCCESS_CODE) {
            return data
//        } else {
//            throw ApiException(code, message)
//        }
    }
}
