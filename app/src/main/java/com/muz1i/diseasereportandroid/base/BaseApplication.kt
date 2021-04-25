package com.muz1i.diseasereportandroid.base

import android.app.Application
import android.content.Context

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class BaseApplication : Application() {

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
    }
}
