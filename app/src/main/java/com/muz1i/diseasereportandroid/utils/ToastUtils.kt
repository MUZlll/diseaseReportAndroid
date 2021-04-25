package com.muz1i.diseasereportandroid.utils

import android.widget.Toast
import com.muz1i.diseasereportandroid.base.BaseApplication

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
object ToastUtils {
    private var sToast: Toast? = null

    fun showToast(tips: String?) {
        if (sToast == null) {
            sToast = Toast.makeText(BaseApplication.appContext, tips, Toast.LENGTH_SHORT)
        } else {
            sToast!!.setText(tips)
        }
        sToast!!.show()
    }
}
