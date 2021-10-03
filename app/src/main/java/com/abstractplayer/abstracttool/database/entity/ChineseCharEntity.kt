package com.abstractplayer.abstracttool.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 ** Created by AbstractStatus at 2021/9/27 21:14.
 */

@Entity(tableName = "table_chinese_char")
data class ChineseCharEntity(
    @PrimaryKey(autoGenerate = true) val cid: Long,
    @ColumnInfo(name = "char_name") var charName: String?,
    @ColumnInfo(name = "char_date") var charDate: String?,
    @ColumnInfo(name = "char_bind") var charBind: String?,
    @ColumnInfo(name = "char_extra") var charExtra: String?
)