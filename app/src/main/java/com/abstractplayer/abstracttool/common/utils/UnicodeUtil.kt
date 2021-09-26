package com.abstractplayer.abstracttool.common.utils

/**
 ** Created by AbstractStatus at 2021/9/22 22:20.
 */
object UnicodeUtil {
    fun getOneChineseCharacter(): String{
        return (0x4E00..0x9FA5).random().toChar().toString()
    }
}