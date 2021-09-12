package com.abstractplayer.abstracttool.common.utils

import android.content.Context
import android.util.Log
import android.view.View
import com.abstractplayer.abstracttool.main.tool.view.MainToolBarInfo

/**
 ** Created by AbstractStatus at 2021/9/12 10:30.
 */
object DimenUtil {
    fun setStatusBarPadding(context: Context, view: View) {
        var compatPaddingTop = 0
        // 将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        compatPaddingTop = getStatusBarHeight(context)
        view.setPadding(
            view.paddingLeft,
            view.paddingTop + compatPaddingTop,
            view.paddingRight,
            view.paddingBottom
        )
    }

    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }

        //华为畅想9刘海屏状态栏高度为28.333334dp
        return statusBarHeight
    }

    fun px2dp(context: Context, pxVal: Int): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }
}