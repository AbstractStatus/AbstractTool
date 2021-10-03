package com.abstractplayer.abstracttool.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 ** Created by AbstractStatus at 2021/9/27 20:52.
 */

@Entity(tableName = "table_setting")
data class SettingEntity(
    @PrimaryKey(autoGenerate = true) val sid: Long,
    @ColumnInfo(name = "setting_type") var settingType: Int?,
    @ColumnInfo(name = "setting_name") var settingName: String?,
    @ColumnInfo(name = "setting_father_name") var settingFatherName: String?,
    @ColumnInfo(name = "setting_value") var settingValue: String?) {
}