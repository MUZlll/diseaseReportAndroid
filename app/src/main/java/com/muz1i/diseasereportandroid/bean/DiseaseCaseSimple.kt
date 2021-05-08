package com.muz1i.diseasereportandroid.bean

/**
 * @author: Muz1i
 * @date: 2021/5/8
 */
data class DiseaseCaseSimple(
    val id: Int,
    val id_user: Int,
    var disease_name: String,
    var disease_level: String,
    var create_time: String,
    var remark: String
)
