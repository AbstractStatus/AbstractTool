package com.abstractplayer.abstracttool.common.utils

import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager

class WindowUtil {
    companion object{
        //1、状态栏沉浸 + 底部导航栏沉浸
        fun setImmerseStatusAndNavBar(window: Window){
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }

        //2、状态栏隐藏 + 底部导航栏隐藏（游戏全屏）
        fun setHideStatusFullScreen(window: Window){
            window.decorView.systemUiVisibility = (
                    //下滑显示状态栏和导航栏，数秒后隐藏，粘性沉浸式
                    //若没有STICKY，轻触屏幕也会弹出两栏，否则不会
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            //某些设备这里设置了状态栏无法隐藏
//                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    //若不隐藏导航栏，则页面和导航栏嵌在一起，也就是说页面感知不到导航栏，觉得导航栏隐藏了
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            //全屏显示，状态栏不消失，若状态栏不隐藏，则嵌在一起，页面感知不到状态栏的存在
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            //隐藏导航栏
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    //全屏显示且隐藏状态栏
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

        //3、仅状态栏沉浸
        fun setImmerseOnlyStatusBar(window: Window){
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

        //4、状态栏沉浸 + 底部导航栏隐藏（伪全屏）
        fun setImmerseStatusFullScreen(window: Window){
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}