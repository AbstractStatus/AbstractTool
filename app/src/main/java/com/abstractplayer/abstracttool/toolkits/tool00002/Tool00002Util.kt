package com.abstractplayer.abstracttool.toolkits.tool00002

/**
 ** Created by 79260 at 2021/8/15 16:46.
 */
class Tool00002Util {
    companion object{
        fun solveMathView(board: Array<Array<Int>>) : Array<Array<Int>>{
            dfs(board, 0, 0)
            return board
        }

        private fun dfs(board: Array<Array<Int>>, row: Int, col: Int): Boolean {
            // base case
            if (row == 9) {
                return true
            }
            if (col == 9) {
                return dfs(board, row + 1, 0)
            }

            // 已有数字，忽略
            if (board[row][col] != 0) {
                return dfs(board, row, col + 1)
            }
            for (i in 1..9) {
                if (!isValid(board, row, col, i)) {
                    continue
                }
                // check valid
                board[row][col] = i
                if (dfs(board, row, col + 1)) {
                    return true
                }
                board[row][col] = 0
            }
            return false
        }

        fun isValid(board: Array<Array<Int>>, row: Int, col: Int, value: Int): Boolean {
            // 横行不能有重复值
            for (i in 0..8) {
                if (board[row][i] == value) {
                    return false
                }
            }
            // 竖行不能有重复值
            for (i in 0..8) {
                if (board[i][col] == value) {
                    return false
                }
            }
            // 所在九宫格
            for (i in 0..2) {
                val k = (row / 3) * 3 + (row + i) % 3
                for (j in 0..2) {
                    val l = (col / 3) * 3 + (col + j) % 3
                    if (board[k][l] == value) {
                        return false
                    }
                }
            }

            return true
        }

    }
}