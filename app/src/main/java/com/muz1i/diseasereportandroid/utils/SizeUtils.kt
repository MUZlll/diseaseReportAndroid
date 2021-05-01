package com.muz1i.diseasereportandroid.utils

import android.content.Context

/**
 * @author: Muz1i
 * @date: 2021/5/1
 */
object SizeUtils {
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
