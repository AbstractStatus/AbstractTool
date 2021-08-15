package com.abstractplayer.abstracttool.toolkits.tool00005

/**
 ** Created by 79260 at 2021/8/14 12:21.
 */
class Tool00005Util {

    companion object{
        private val ballMap = HashMap<Int, Boolean>().apply {
            repeat(33){
                put(it + 1, false)
            }
        }

        fun getRandomBallList(): ArrayList<Int>{
            val res= ArrayList<Int>()

            val blueBall = (1..16).random()
            ballMap[blueBall] = true

            var index = 0
            var redBall = 0
            while(index < 6){
                redBall = (1..33).random()
                if(ballMap[redBall]!!){
                    continue
                }

                res.add(redBall)
                ballMap[redBall] = true
                index += 1
            }

            res.sort()

            res.add(blueBall)

            repeat(33){
                ballMap[it + 1] = false
            }

            return res
        }
    }
}