package com.abstractplayer.abstracttool.database.dao

import androidx.room.*
import com.abstractplayer.abstracttool.database.entity.ChineseCharEntity

/**
 ** Created by AbstractStatus at 2021/9/27 21:19.
 */
@Dao
interface ChineseCharDao {
    @Query("SELECT * FROM table_chinese_char")
    suspend fun getAllChineseChar(): List<ChineseCharEntity>

    @Insert
    suspend fun insertChineseChar(chineseCharEntity: ChineseCharEntity)

    @Update
    suspend fun updateChineseChar(chineseCharEntity: ChineseCharEntity)

    @Delete
    suspend fun deleteChineseChar(chineseCharEntity: ChineseCharEntity)
}