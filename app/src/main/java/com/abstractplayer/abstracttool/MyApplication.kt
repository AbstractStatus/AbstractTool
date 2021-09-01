package com.abstractplayer.abstracttool

import android.app.Application
import cn.leancloud.LCLogger
import cn.leancloud.LeanCloud
import com.abstractplayer.abstracttool.common.handler.CrashHandler

/**
 ** Created by AbstractStatus at 2021/8/29 18:24.
 */

const val appId = "xLBhaFHRsL64m5M0QcRACuJe-gzGzoHsz"
const val appKey = "2UAXOAaMils3ikJKFbHUl41t"
const val serverHost = "https://www.playerchan.cn"

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // 在 LeanCloud.initialize() 之前调用
        //LeanCloud.setLogLevel(LCLogger.Level.DEBUG);

        //崩溃日志全局读取
        CrashHandler.init(this)

        //leanCloud初始化
        LeanCloud.initialize(this, appId, appKey, serverHost)
    }
}