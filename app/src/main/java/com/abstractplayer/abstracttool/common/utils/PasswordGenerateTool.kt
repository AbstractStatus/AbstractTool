package com.abstractplayer.abstracttool.common.utils

import java.util.*



object PasswordGenerateTool {
    //特殊字符
    private val special_characters =
            charArrayOf('`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
                        '-', '+', '_', '=', '[', ']', '{', '}', ':', ';', ',', '.',
                        '<', '>', '/', '?')

    //大写字母
    private val capital = charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    //小写字母
    private val lower_case_letters = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

    //数字
    private val number = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')

    //字符类型的数量
    private const val typeNum = 4
    private val random = Random()

    // 生成 n 位密码，最少8位
    fun generatePassword(nn: Int): String {
        var n = nn
        if (n < 8) {
            n = 8
        }
        val password = StringBuilder()
        for (i in 0 until n) {
            when (random.nextInt(typeNum)) {
                0 -> password.append(randomChar(special_characters))
                1 -> password.append(randomChar(capital))
                2 -> password.append(randomChar(lower_case_letters))
                3 -> password.append(randomChar(number))
            }
        }
        return password.toString()
    }

    private fun randomChar(char_set: CharArray): Char {
        return char_set[random.nextInt(char_set.size)]
    }
}