package com.abstractplayer.abstracttool

import android.app.Application
import com.abstractplayer.abstracttool.common.handler.CrashHandler

/**
 ** Created by AbstractStatus at 2021/8/29 18:24.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CrashHandler.init(this)
    }
}