package com.abstractplayer.abstracttool

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import cn.leancloud.LCLogger
import cn.leancloud.LeanCloud
import com.abstractplayer.abstracttool.common.handler.CrashHandler
import com.abstractplayer.abstracttool.main.person.setting.SettingKey
import com.tencent.mmkv.MMKV

/**
 ** Created by AbstractStatus at 2021/8/29 18:24.
 */

const val appId = "xLBhaFHRsL64m5M0QcRACuJe-gzGzoHsz"
const val appKey = "2UAXOAaMils3ikJKFbHUl41t"
const val serverHost = "https://abc.playerchan.cn"

class MyApplication : Application() {
    //黑白模式的状态，默认false，对应白天
    private val dayNightMode: Boolean by lazy { MMKV.defaultMMKV().decodeBool(SettingKey.KEY_BOOLEAN_DAY_NIGHT_MODE) }

    override fun onCreate() {
        super.onCreate()

        // 在 LeanCloud.initialize() 之前调用
        //LeanCloud.setLogLevel(LCLogger.Level.DEBUG);

        //崩溃日志全局读取
        CrashHandler.init(this)

        //MMKV
        MMKV.initialize(this)

        //黑白模式初始化
        AppCompatDelegate.setDefaultNightMode(if(dayNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        //leanCloud初始化
        LeanCloud.initialize(this, appId, appKey, serverHost)


    }
}