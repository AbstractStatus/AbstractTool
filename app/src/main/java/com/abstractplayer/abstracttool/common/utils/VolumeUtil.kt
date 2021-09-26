package com.asplayer.nameless.util

import android.content.Context
import android.media.AudioManager


object VolumeUtil {
    val volumeTypes = arrayOf(
            "CALL",  //通话
            "SYSTEM",
            "RING",
            "MUSIC",  //媒体
            "ALARM",  //闹钟
            "NOTIFICATION" //铃声
    )
    const val TYPE_CALL = 0
    const val TYPE_SYSTEM = 1
    const val TYPE_RING = 2
    const val TYPE_MUSIC = 3
    const val TYPE_ALARM = 4
    const val TYPE_NOTIFICATION = 5

    fun getMaxVolume(type: Int, context: Context): Int {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        return audioManager.getStreamMaxVolume(type)
    }

    fun getCurVolume(type: Int, context: Context): Int {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        return audioManager.getStreamVolume(type)
    }

    fun setVolume(type: Int, volume: Int, context: Context) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(type, volume, 0)
    }

}