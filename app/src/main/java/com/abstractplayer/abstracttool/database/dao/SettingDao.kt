package com.abstractplayer.abstracttool.database.dao

import androidx.room.*
import com.abstractplayer.abstracttool.database.entity.SettingEntity

/**
 ** Created by AbstractStatus at 2021/9/27 21:08.
 */
@Dao
interface SettingDao {
    @Query("SELECT * FROM table_setting")
    suspend fun getAllSetting(): List<SettingEntity>

    @Insert
    suspend fun insertSetting(settingEntity: SettingEntity)

    @Update
    suspend fun updateSetting(settingEntity: SettingEntity)

    @Delete
    suspend fun deleteSetting(settingEntity: SettingEntity)
}