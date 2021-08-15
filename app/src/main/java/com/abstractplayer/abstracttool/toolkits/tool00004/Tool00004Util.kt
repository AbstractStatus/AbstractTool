package com.abstractplayer.abstracttool.toolkits.tool00004

/**
 ** Created by 79260 at 2021/8/14 11:10.
 */
class Tool00004Util {

    companion object{
        /**
         * 415. 字符串相加（两个大数相加）
         * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，模拟人工加法
         *
         * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
         * 空间复杂度 O(1)：指针与变量使用常数大小空间。
         *
         * @param num1
         * @param num2
         * @return
         */
        fun addStrings(num1: String, num2: String): String? {
            var i = num1.length - 1
            var j = num2.length - 1

            var carry = 0
            val res = StringBuilder("")

            while (i >= 0 || j >= 0) {
                val n1 = if (i >= 0) num1[i] else '0'
                val n2 = if (j >= 0) num2[j] else '0'

                // 分别获取两个字符对应的字面数值，然后相加，再加上进位
                val sum = n1.toInt() + n2.toInt() - 2 * '0'.toInt() + carry

                // 求值：获取进位
                carry = sum / 10

                // 添加当前位
                res.append(sum % 10)

                i--
                j--
            }

            // 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
            if (carry == 1) res.append(1)

            // 最后翻转恢复字符串，再返回
            return res.reverse().toString()
        }


        //判断是不是正整数字符串（纯数字）
        fun isDigitsStr(s: String): Boolean{
            if(s.isEmpty()){
                return false
            }

            if(s.isNotEmpty() && s[0] == '0'){
                return false
            }

            val charArray = s.toCharArray()
            for(c in charArray){
                if((c.toInt() < 48) or (c.toInt() > 57)){
                    return false
                }
            }

            return true
        }
    }

}