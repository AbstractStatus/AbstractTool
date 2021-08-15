package com.abstractplayer.abstracttool.common.utils

class RandomUtil {

    companion object{
        //获取从整数a到b的随机数，开闭由left和right设置
        fun getRandomIntByRangeInt(a: Int, b: Int, left: Boolean, right: Boolean): Int{
            return if (left){
                if (right){
                    (Math.random() * (b - a + 1) + a).toInt()
                } else{
                    (Math.random() * (b - a) + a).toInt()
                }
            } else{
                if(right){
                    (Math.random() * (b - a) + a + 1).toInt()
                } else{
                    (Math.random() * (b - a - 1) + a + 1).toInt()
                }
            }
        }
    }

}