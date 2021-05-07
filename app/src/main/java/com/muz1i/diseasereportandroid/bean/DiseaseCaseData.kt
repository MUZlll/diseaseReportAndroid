package com.muz1i.diseasereportandroid.bean

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
data class DiseaseCaseData(
    val id: Int,
    val user: UserInfoData,
    val diseaseLevel: String,
    val diseaseName: String,
    val createTime: String,
    val remark: String
)
