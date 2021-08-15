package com.abstractplayer.abstracttool.common.utils

import android.util.Log

class LogUtil {
    companion object {
        //当前使用log的类名
        private lateinit var className: String
        //当前使用log的方法名
        private lateinit var methodName: String
        //当前使用log的行数
        private lateinit var lineNumber: String
        //log的开关,在Application中设置，上线关闭，测试打开
        private var debuggable = false

        //设置显示的log信息
        private fun createLog(log: String) : String{
            val stringBuffer = StringBuffer()
            stringBuffer.append("==========")
                .append(methodName)
                .append("(")
                .append(className)
                .append(":")
                .append(lineNumber)
                .append(")==========")
                .append(log);
            return stringBuffer.toString();
        }

        //根据获取到的堆栈跟踪信息，得到当前调用这个类的类名、方法名以及行数
        private fun getName(stackTraceElements: Array<StackTraceElement>){
            className = stackTraceElements[1].fileName;
            methodName = stackTraceElements[1].methodName;
            lineNumber = stackTraceElements[1].lineNumber.toString();
        }

        fun e(msg: String) {
            if (!debuggable)
                return;
            //获取堆栈信息
            getName(Throwable().stackTrace);
            Log.e(className, createLog(msg));
        }

        fun d(msg: String) {
            if (!debuggable)
                return;
            getName(Throwable().stackTrace);
            Log.d(className, createLog(msg));
        }

        fun i(msg: String) {
            if (!debuggable)
                return;
            getName(Throwable().stackTrace);
            Log.i(className, createLog(msg));
        }

        fun w(msg: String) {
            if (!debuggable)
                return;
            getName(Throwable().stackTrace);
            Log.w(className, createLog(msg));
        }

    }

}