package com.abstractplayer.abstracttool.common.utils.security

import android.util.Log
import com.abstractplayer.abstracttool.common.utils.RandomUtil
import com.abstractplayer.abstracttool.common.utils.TimeUtil

import kotlin.math.pow

class QRPasswordSysUtil {
    companion object{
        private const val TAG = "PasswordSys"
        //替换加密映射字典
        private val encryption_form = mapOf('0' to 's', '1' to 'h', '2' to 'G', '3' to 'a',
            '4' to 'n', '5' to 'g', '6' to 'c', '7' to 'U', '8' to '1',
            '9' to '@', 'A' to 'o', 'B' to 'D', 'C' to 'j', 'D' to 'l',
            'E' to 'w', 'F' to 'd', 'G' to '9', 'H' to 'y',
            'I' to '6', 'J' to 'Q', 'K' to 'q', 'L' to '4', 'M' to 'f',
            'N' to 'N', 'O' to 'J', 'P' to 'k', 'Q' to '5',
            'R' to 'z', 'S' to 'P', 'T' to 'S', 'U' to 'T', 'V' to 'u',
            'W' to '3', 'X' to 'i', 'Y' to 'H', 'Z' to '?',
            'a' to 'X', 'b' to 'b', 'c' to '0', 'd' to 'e', 'e' to 'p',
            'f' to 'V', 'g' to 'Z', 'h' to '2', 'i' to 'O',
            'j' to 'W', 'k' to 'I', 'l' to 'L', 'm' to '8', 'n' to 'v',
            'o' to 'x', 'p' to 'K', 'q' to 'E', 'r' to 'R',
            's' to 'M', 't' to '7', 'u' to 'A', 'v' to 'F', 'w' to 'C',
            'x' to 'Y', 'y' to 'r', 'z' to 'B', '?' to 't', '@' to 'm')

        //替换解密映射字典
        private val decryption_form = mapOf('s' to '0', 'h' to '1', 'G' to '2', 'a' to '3',
            'n' to '4', 'g' to '5', 'c' to '6', 'U' to '7', '1' to '8',
            '@' to '9', 'o' to 'A', 'D' to 'B', 'j' to 'C', 'l' to 'D', 'w' to 'E',
            'd' to 'F', '9' to 'G', 'y' to 'H',
            '6' to 'I', 'Q' to 'J', 'q' to 'K', '4' to 'L',
            'f' to 'M', 'N' to 'N', 'J' to 'O', 'k' to 'P', '5' to 'Q',
            'z' to 'R', 'P' to 'S', 'S' to 'T', 'T' to 'U', 'u' to 'V',
            '3' to 'W', 'i' to 'X', 'H' to 'Y', '?' to 'Z',
            'X' to 'a', 'b' to 'b', '0' to 'c', 'e' to 'd', 'p' to 'e',
            'V' to 'f', 'Z' to 'g', '2' to 'h', 'O' to 'i',
            'W' to 'j', 'I' to 'k', 'L' to 'l', '8' to 'm', 'v' to 'n',
            'x' to 'o', 'K' to 'p', 'E' to 'q', 'R' to 'r',
            'M' to 's', '7' to 't', 'A' to 'u', 'F' to 'v', 'C' to 'w',
            'Y' to 'x', 'r' to 'y', 'B' to 'z', 't' to '?', 'm' to '@')

        private val base_element = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
                'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z', '?', '@')

        private val base_element_form = mapOf('0' to 0, '1' to 1, '2' to 2, '3' to 3, '4' to 4,
            '5' to 5, '6' to 6, '7' to 7, '8' to 8, '9' to 9, 'A' to 10,
            'B' to 11, 'C' to 12, 'D' to 13, 'E' to 14, 'F' to 15, 'G' to 16,
            'H' to 17, 'I' to 18, 'J' to 19, 'K' to 20,
            'L' to 21, 'M' to 22, 'N' to 23, 'O' to 24, 'P' to 25, 'Q' to 26,
            'R' to 27, 'S' to 28, 'T' to 29, 'U' to 30,
            'V' to 31, 'W' to 32, 'X' to 33, 'Y' to 34, 'Z' to 35, 'a' to 36,
            'b' to 37, 'c' to 38, 'd' to 39, 'e' to 40,
            'f' to 41, 'g' to 42, 'h' to 43, 'i' to 44, 'j' to 45, 'k' to 46,
            'l' to 47, 'm' to 48, 'n' to 49, 'o' to 50,
            'p' to 51, 'q' to 52, 'r' to 53, 's' to 54, 't' to 55, 'u' to 56,
            'v' to 57, 'w' to 58, 'x' to 59, 'y' to 60,
            'z' to 61, '?' to 62, '@' to 63)

        private val keyList = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

        fun generate(): String{
            // 当前毫秒级时间字符串 取第二位至最后
            val strNow = TimeUtil.getTimeMilliSecondStr().run {
                substring(2, this.length - 1)
            }
            val strRandomNum = RandomUtil.getRandomIntByRangeInt(10000, 99999, left = true, right = true)
                    .toString()

            val strBaseKey = strRandomNum + strNow
            Log.d(TAG, "generate: strBaseKey: $strBaseKey")
            var pre = ""
            // 前缀5位
            repeat(5){
                pre += keyList[RandomUtil.getRandomIntByRangeInt(0, 35, left = true, right = true)]
            }
            // 后部分加密
            var curKey = ""
            var ordStrBaseKey: Int
            repeat(strBaseKey.length){
                ordStrBaseKey = if ((pre.codePointAt(it % 5) + it) % 2 == 1){
                    strBaseKey.codePointAt(it) + pre.codePointAt(it % 5) % 30
                } else{
                    strBaseKey.codePointAt(it) - pre.codePointAt(it % 5) % 9
                }
                curKey += ordStrBaseKey.toChar()
            }


            return pre + curKey

        }

        fun encryption(keyAll: String, words: String): String{
            if (keyAll.length != 20){
                return "钥匙无效"
            }
            repeat(keyAll.length){
                if ((keyAll[it].toInt() < 40) or (keyAll[it].toInt() > 90)){
                    return "钥匙无效"
                }
            }

            val pre = keyAll.substring(0, 5)
            val key = keyAll.substring(5, keyAll.length)
            var curKey = ""
            var ordStrBaseKey: Int
            repeat(key.length){
                ordStrBaseKey = if ((pre.codePointAt(it % 5) + it) % 2 == 1){
                    key.codePointAt(it) - pre.codePointAt(it % 5) % 30
                } else{
                    key.codePointAt(it) + pre.codePointAt(it % 5) % 9
                }
                Log.d(TAG, "encryption: " + ordStrBaseKey.toChar())
                curKey += ordStrBaseKey.toChar()
            }
            var nextKey = ""
            repeat(curKey.length){
                ordStrBaseKey = if((pre.codePointAt(it % 5) + it) % 2 == 0){
                    curKey.codePointAt(it) + pre.codePointAt(it % 5) % 30
                } else{
                    curKey.codePointAt(it) - pre.codePointAt(it % 5) % 9
                }
                nextKey += ordStrBaseKey.toChar()
            }

            var nextWords = ""
            var changeNum = curKey.codePointAt(curKey.length - 1)
            var wordId: Int
            var baseExponent: Int
            repeat(words.length){
                wordId = words.codePointAt(it)
                //64**3
                if (wordId > 262144){
                    return "字符超出范围"
                }
                baseExponent = 2
                while (baseExponent >= 0){
                    nextWords += encryption_form[base_element[(wordId / (64.0.pow(baseExponent.toDouble())
                        .toInt()) + changeNum % 13) % 64]]
                    wordId %= 64.0.pow(baseExponent.toDouble()).toInt()
                    baseExponent -= 1
                    changeNum = curKey.codePointAt(it % curKey.length)
                }

            }
            Log.d(TAG, "encryption: $nextKey + $nextWords")
            return nextKey + nextWords
        }

        fun decryption(key: String, words: String): String{
            val lengthKey = key.length
            val lengthWords = words.length
            if ((lengthKey != 20) or (lengthWords < 15) or (lengthWords % 3 == 1)){
                return "输入有误"
            }

            val pre = key.substring(0, 5)
            val preKeyKey = key.substring(5, key.length)
            val preWordsKey = words.substring(0, 15)
            var keyKey = ""
            var wordsKey = ""
            var ordStrBaseKey: Int
            Log.d(TAG, "decryption: preKeyKey $preKeyKey")
            repeat(preKeyKey.length){
                ordStrBaseKey = if ((pre.codePointAt(it % 5) + it) % 2 == 1){
                    preKeyKey.codePointAt(it) - pre.codePointAt(it % 5) % 30
                } else{
                    preKeyKey.codePointAt(it) + pre.codePointAt(it % 5) % 9
                }
                keyKey += ordStrBaseKey.toChar()
            }
            repeat(preKeyKey.length){
                ordStrBaseKey = if ((pre.codePointAt(it % 5) + it) % 2 == 0){
                    preWordsKey.codePointAt(it) - pre.codePointAt(it % 5) % 30
                } else{
                    preWordsKey.codePointAt(it) + pre.codePointAt(it % 5) % 9
                }
                wordsKey += ordStrBaseKey.toChar()
            }

            var res = ""
            Log.d(TAG, "decryption: wordsKey: $wordsKey")
            Log.d(TAG, "decryption: keyKey: $keyKey")
            if (wordsKey != keyKey){
                return "钥匙错误"
            }
            val wordsWords = words.substring(15, words.length)
            Log.d(TAG, "decryption: $wordsWords")
            var changeNum = keyKey.codePointAt(keyKey.length - 1)

            var baseExponent: Int
            var wordId: Int
            var baseIndex: Int
            for(i in wordsWords.indices){
                if (i % 3 != 0){
                    continue
                }
                baseExponent = 2
                wordId = 0
                baseIndex = 2
                while (baseExponent >= 0){
                    wordId += 64.0.pow(baseExponent.toDouble()).toInt() *
                            (((base_element_form[decryption_form[wordsWords[i + 2 - baseIndex]]]?.minus(changeNum % 13)
                                    ?: 0) + 64) % 64)
                    changeNum = keyKey.codePointAt((i/3) % keyKey.length)
                    baseIndex -= 1
                    baseExponent -= 1
                }
                res += wordId.toChar()
            }



            return res
        }
    }

}