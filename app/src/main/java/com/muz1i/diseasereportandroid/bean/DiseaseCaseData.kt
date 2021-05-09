package com.muz1i.diseasereportandroid.bean

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
data class DiseaseCaseData(
    val id: Int?,
    var user: UserInfoData?,
    var diseaseLevel: String,
    var diseaseName: String,
    var createTime: String,
    var remark: String
)
