package com.muz1i.diseasereportandroid.api

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
data class ApiException(val code: Int, override val message: String) : RuntimeException()
