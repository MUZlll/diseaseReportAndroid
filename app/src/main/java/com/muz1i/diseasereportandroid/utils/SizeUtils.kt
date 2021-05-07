package com.muz1i.diseasereportandroid.utils

import com.muz1i.diseasereportandroid.base.BaseApplication

/**
 * @author: Muz1i
 * @date: 2021/5/1
 */
object SizeUtils {
    fun dip2px(dpValue: Float): Int {
        val scale = BaseApplication.appContext.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
