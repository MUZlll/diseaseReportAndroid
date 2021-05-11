package com.muz1i.diseasereportandroid.base

import android.app.Application
import android.content.Context
import com.muz1i.diseasereportandroid.utils.Constants

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class BaseApplication : Application() {

    companion object {
        lateinit var appContext: Context
        var permission = Constants.PERMISSION_USER
        lateinit var id: String
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
    }
}
