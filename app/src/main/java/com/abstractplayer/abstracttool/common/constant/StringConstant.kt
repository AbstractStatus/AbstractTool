package com.abstractplayer.abstracttool.common.constant

/**
 ** Created by 79260 at 2021/8/4 11:24.
 */
class StringConstant {
    companion object{
        const val EMPTY_STRING = ""
        const val NULL_CAPITAL_STRING = "NULL"
        const val NULL_LOWERCASE_STRING = "null"

        fun isNotNullAndEmpty(s: String): Boolean{
            return !(s == EMPTY_STRING || s == NULL_CAPITAL_STRING || s == NULL_LOWERCASE_STRING)
        }
    }
}