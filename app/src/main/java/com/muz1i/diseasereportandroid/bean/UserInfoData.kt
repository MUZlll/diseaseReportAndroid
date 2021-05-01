package com.muz1i.diseasereportandroid.bean

/**
 * @author: Muz1i
 * @date: 2021/4/28
 */
data class UserInfoData(
    val id: Int,
    val studentNum: String,
    val name: String,
    val password: String,
    val sex: String,
    val institute: String,
    val major: String,
    val grade: String,
    val clazz: String,
    val dormitory: String
)
