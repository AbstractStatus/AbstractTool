package com.abstractplayer.abstracttool.common.handler

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import com.abstractplayer.abstracttool.common.utils.FileUtil.getCrashDir
import java.io.*
import java.lang.reflect.Field
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


/**
 ** Created by AbstractStatus at 2021/8/29 18:38.
 */
object CrashHandler: Thread.UncaughtExceptionHandler {
        private const val TAG = "CrashHandler"

        /*系统默认的UncaughtException处理类*/
        private var defaultHandler: Thread.UncaughtExceptionHandler? = null
        private lateinit var context: Context

        /*用来存储设备信息和异常信息*/
        private val info: MutableMap<String, String> = HashMap()
        fun init(context: Context) {
            this.context = context
            //获取系统默认的UncaughtException处理器
            defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
            //设置该CrashHandler为程序的默认处理器
            Thread.setDefaultUncaughtExceptionHandler(this)
        }

        /**
         * 【说明】：当UncaughtException发生时会转入该方法来处理
         *
         */
        override fun uncaughtException(thread: Thread, ex: Throwable) {
            if (!handleException(ex) && defaultHandler != null) {
                //如果用户没有处理则让系统默认的异常处理器处理
                defaultHandler!!.uncaughtException(thread, ex)
            }
        }

        /**
         * 【说明】：自定义错误处理（包括收集错误信息，生成错误日志文件）
         */
        private fun handleException(ex: Throwable?): Boolean {
            if (ex == null) {
                return false
            }
            collectDeviceInfo(context)
            saveCrashInfo2File(ex)
            return true
        }

        /**
         * 【说明】：收集应用参数信息
         */
        private fun collectDeviceInfo(ctx: Context) {
            try {
                val pm: PackageManager = ctx.packageManager //获取应用包管理者对象
                val pi: PackageInfo = pm.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
                if (pi != null) {
                    val versionName = if (pi.versionName == null) "null" else pi.versionName
                    val versionCode = pi.versionCode.toString() + ""
                    val packageName = pi.packageName
                    info["versionName"] = versionName
                    info["versionCode"] = versionCode
                    info["packageName"] = packageName
                    if (ctx != null) {
                        info["appName"] = ctx.getString(com.abstractplayer.abstracttool.R.string.app_name)
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                Log.e(TAG, "an error occurred when collect package info...", e)
            }
            val fields: Array<Field> = Build::class.java.declaredFields
            for (field in fields) {
                try {
                    field.isAccessible = true
                    info[field.name] = field.get(null).toString()
                } catch (e: IllegalAccessException) {
                    Log.e(TAG, "an error occurred when collect crash info...", e)
                }
            }
        }

        /**
         * 【说明】：保存错误信息到指定文件中
         */
        private fun saveCrashInfo2File(ex: Throwable): String? {
            val sbf = StringBuffer()
            for ((key, value) in info) {
                sbf.append("$key=$value\n")
            }
            val writer: Writer = StringWriter()
            val printWriter = PrintWriter(writer)
            ex.printStackTrace(printWriter)
            var cause = ex.cause
            while (cause != null) {
                cause.printStackTrace(printWriter)
                cause = cause.cause
            }
            printWriter.close()
            val result: String = writer.toString()
            sbf.append(result)
            try {
                //格式化日期，作为文件名的一部分
                val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val time: String = dateFormat.format(Date())
                val timestamp = System.currentTimeMillis()
                val fileName = "crash-$time-$timestamp.log"
                if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                    val dir: File? = getCrashDir(context)
                    val filePath: String = dir?.absoluteFile.toString() + File.separator.toString() + fileName
                    val fos = FileOutputStream(filePath)
                    Log.e(TAG, "log file path:$filePath")
                    fos.write(sbf.toString().toByteArray())
                    fos.close()
                }
                return fileName
            } catch (e: FileNotFoundException) {
                Log.e(TAG, "an error occurred while find file...", e)
            } catch (e: IOException) {
                Log.e(TAG, "an error occurred while writing file...", e)
            }
            return null
        }

}