package com.abstractplayer.abstracttool.common.utils

class TimeUtil {
    companion object{
        fun getTimeMilliSecondStr(): String{
            return System.currentTimeMillis().toString()
        }

        fun getTimeMillisecondLong(): Long{
            return System.currentTimeMillis()
        }
    }
}