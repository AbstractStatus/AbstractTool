package com.asplayer.nameless.util

import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

/**
 * you must code in manifest permission
 * <uses-permission android:name="android.permission.VIBRATE"/>
 * 如何创建audioAttributes实例？
 * AudioAttributes.Builder().setXX....build()
 */

@RequiresApi(api = Build.VERSION_CODES.M)
object VibrateUtil {
    /**
     * @param millisecond 震动持续毫秒
     * @param amplitude 震动强度 1~255 默认-1
     */
    fun vibrateOneShot(
        context: Context,
        millisecond: Long,
        amplitude: Int
    ){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator.vibrate(
                VibrationEffect.createOneShot(millisecond, if (amplitude in 1..255) amplitude else VibrationEffect.DEFAULT_AMPLITUDE)
            )
        }else {
            vibrator.vibrate(millisecond)
        }
    }

    /**
     * @param millisecond 震动持续毫秒
     * @param amplitude 震动强度 1~255 默认-1
     * @param audioAttributes 震动的音乐属性
     */
    fun vibrateOneShotByAudioAttributes(
        context: Context,
        millisecond: Long,
        amplitude: Int,
        audioAttributes: AudioAttributes
    ){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator.vibrate(
                VibrationEffect.createOneShot(millisecond, if (amplitude in 1..255) amplitude else VibrationEffect.DEFAULT_AMPLITUDE),
                audioAttributes
            )
        }else {
            vibrator.vibrate(millisecond, audioAttributes)
        }
    }

    /**
     * @param timings 震动持续毫秒 - 停止时间 - 震动时间 -...
     * @param amplitudes 震动强度数组 1~255 默认-1
     * @param repeat
     */
    fun vibrateAlternate(
        context: Context,
        timings: LongArray,
        amplitudes: IntArray,
        repeat: Int
    ){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator.vibrate(
                VibrationEffect.createWaveform(timings, amplitudes, repeat)
            )
        }else {
            vibrator.vibrate(timings, repeat)
        }
    }

    /**
     * @param timings 震动持续毫秒 - 停止时间 - 震动时间 -...
     * @param amplitudes 震动强度数组 1~255 默认-1
     * @param repeat
     * @param audioAttributes 震动的音乐属性
     */
    fun vibrateAlternateByAudioAttributes(
        context: Context,
        timings: LongArray,
        amplitudes: IntArray,
        repeat: Int,
        audioAttributes: AudioAttributes
    ){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator.vibrate(
                VibrationEffect.createWaveform(timings, amplitudes, repeat),
                audioAttributes
            )
        }else {
            vibrator.vibrate(timings, repeat, audioAttributes)
        }
    }



}