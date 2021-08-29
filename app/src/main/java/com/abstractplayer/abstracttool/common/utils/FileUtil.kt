package com.abstractplayer.abstracttool.common.utils

import android.content.Context
import android.os.Environment
import com.abstractplayer.abstracttool.common.constant.FileConstant
import java.io.File


/**
 ** Created by AbstractStatus at 2021/8/29 18:28.
 */
    /**
     * 【说明】：文件及文件夹工具类
     */
object FileUtil {
    private const val TAG = "FileUtil"

    /**
     * 【说明】：获取手机存储空间文件夹
     *
     * @param context 上下文
     * @return File 文件夹（/storage/emulated/0或/data/user/0/(packageName)）
     */
    private fun getAppDir(context: Context): File {
        var appDir: String? = null
        appDir = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            //获取外部存储路径（SD卡，在/storage/emulated/0目录下）
            Environment.getExternalStorageDirectory().absolutePath
        } else {
            //获取内部存储路径（默认在/date/user/0/(packageName)目录下）
            context.cacheDir.absolutePath
        }
        val appFile = File(appDir)
        if (!appFile.exists()) {
            appFile.mkdirs()
        }
        return appFile
    }

    /**
     * 【说明】：获取应用基础文件夹（应用相关文件都会存储在该目录下）
     *
     * @param context 上下文
     * @return File 文件夹（.../AppName/）
     */
    fun getBaseDir(context: Context): File {
        val baseFile = File(getAppDir(context), FileConstant.APP_BASE_DIR_NAME)
        if (!baseFile.exists()) {
            baseFile.mkdirs()
        }
        return baseFile
    }

    /**
     * 【说明】：获取应用日志文件夹
     *
     * @param context 上下文
     * @return File 文件夹（.../AppName/Log/）
     */
    fun getLogDir(context: Context): File {
        val logFile = File(getBaseDir(context), FileConstant.APP_LOG_DIR_NAME)
        if (!logFile.exists()) {
            logFile.mkdirs()
        }
        return logFile
    }

    /**
     * 【说明】：获取应用崩溃日志文件夹
     *
     * @param context 上下文
     * @return File 文件夹（.../AppName/Log/Crash/）
     */
    fun getCrashDir(context: Context): File {
        val crashFile = File(getLogDir(context), FileConstant.APP_LOG_CRASH_DIR_NAME)
        if (!crashFile.exists()) {
            crashFile.mkdirs()
        }
        return crashFile
    }
}
