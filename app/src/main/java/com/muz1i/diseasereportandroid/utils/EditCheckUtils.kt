package com.muz1i.diseasereportandroid.utils

import android.view.View
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.google.android.material.textfield.TextInputLayout

/**
 * @author: Muz1i
 * @date: 2021/5/5
 */
object EditCheckUtils {
    fun checkNotNull(rootView: View): Boolean {
        val tempRootView = rootView as ConstraintLayout
        val scrollView = tempRootView[0] as ScrollView
        val constraintLayout = scrollView[0] as ConstraintLayout
        for (i in 0 until constraintLayout.childCount) {
            if (constraintLayout[i] is TextInputLayout) {
                val textInputLayout = constraintLayout[i] as TextInputLayout
                if (textInputLayout.editText!!.text.isNullOrEmpty()) {
                    return false
                }
            }
        }
        return true
    }
}
