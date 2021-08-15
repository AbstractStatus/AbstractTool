package com.abstractplayer.abstracttool.toolkits.tool00008

/**
 ** Created by 79260 at 2021/8/14 23:11.
 */
class Tool00008Util {
    companion object{
        /**
         * 72. 编辑距离
         *
         * 时间复杂度 ：O(mn)，其中 mm 为 word1 的长度，nn 为 word2 的长度。
         * 空间复杂度 ：O(mn)，我们需要大小为 m * n 的 dp 数组来记录状态值。
         *
         * @param word1
         * @param word2
         * @return
         */
        fun minDistance(s1: String, s2: String): ArrayList<String> {
            val m = s1.length + 1
            val n = s2.length + 1
            val list = ArrayList<String>()

//            if((m - 1) * (n - 1) == 0){
//                return n + m
//            }

            val dp = Array(m) { IntArray(n) }

            // base case
            for (i in 0 until m) dp[i][0] = i
            for (j in 0 until n) dp[0][j] = j

            // dp
            for (i in 1 until m) {
                for (j in 1 until n) {
                    if (s1[i - 1] == s2[j - 1]) {
                        // s1[i]和s2[j] 跳过 ，啥都不做
                        list.add("s1当前索引为$i, s2当前索引为$j 不作操作，s1和s2索引均加 1")
                        dp[i][j] = dp[i - 1][j - 1]
                    } else {
                        dp[i][j] = min(
                            // s1中插入一个和s2[j]一样的字符，s2[j]就被匹配了，前移j，继续跟i对比。操作数+1
                            dp[i][j - 1] + 1,
                            // 把s1[i]这个字符删掉，前移i，继续跟j对比。操作数+1
                            dp[i - 1][j] + 1,
                            // 把s1[i]替换成s2[j]，它俩就匹配了，同时前移i，j，继续对比。操作数+1
                            dp[i - 1][j - 1] + 1
                        )

                        when(dp[i][j]){
                            dp[i][j - 1] + 1 -> {
                                list.add("s1当前索引为$i, s2当前索引为$j s1第$i 个字符 后面插入 s2第$j 个字符，s2索引加1")
                            }
                            dp[i - 1][j] + 1 -> {
                                list.add("s1当前索引为$i, s2当前索引为$j 删除s1第 $i 个字符，s1索引加1")
                            }
                            dp[i - 1][j - 1] + 1 -> {
                                list.add("s1当前索引为$i, s2当前索引为$j s1第$i 个字符替换成 s2第$j 个字符s1和s2索引均加 1")
                            }
                        }
                    }
                }
            }

            list.add("共进行了 ${dp[m - 1][n - 1]} 步操作")
            // 返回 s1[0..m-1] 和 s2[0..n-1] 的最小编辑距离
            return list
        }

        private fun min(a: Int, b: Int, c: Int): Int {
            return Math.min(a, Math.min(b, c))
        }
    }
}