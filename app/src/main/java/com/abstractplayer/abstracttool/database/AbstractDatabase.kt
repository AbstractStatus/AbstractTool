package com.abstractplayer.abstracttool.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abstractplayer.abstracttool.database.dao.ChineseCharDao
import com.abstractplayer.abstracttool.database.dao.SettingDao
import com.abstractplayer.abstracttool.database.entity.ChineseCharEntity
import com.abstractplayer.abstracttool.database.entity.SettingEntity

/**
 ** Created by AbstractStatus at 2021/9/27 20:29.
 */
@Database(entities = [
    SettingEntity::class,
    ChineseCharEntity::class],
    exportSchema = false, version = 1)
abstract class AbstractDatabase : RoomDatabase(){
    companion object {
        private const val TAG = "AbstractDatabase"
        private var INSTANCE: AbstractDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AbstractDatabase{
            INSTANCE?.let { return it }
            return Room.databaseBuilder(
                    context.applicationContext,
                    AbstractDatabase::class.java,
                    "abstract_database")
                    .build()
        }
    }

    abstract fun getSettingDao(): SettingDao
    abstract fun getChineseCharDao(): ChineseCharDao
}