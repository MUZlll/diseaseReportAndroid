package com.muz1i.diseasereportandroid.utils

import androidx.appcompat.app.AppCompatActivity

/**
 * @author: Muz1i
 * @date: 2021/5/9
 */
object FinishActivityAndToastUtils {
    fun finishActivity(
        activity: AppCompatActivity,
        isSuccess: Boolean,
        success: String,
        failed: String
    ) {
        if (isSuccess) {
            activity.finish()
            ToastUtils.showToast(success)
        } else {
            ToastUtils.showToast(failed)
        }
    }
}
