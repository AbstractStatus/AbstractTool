package com.abstractplayer.abstracttool.common.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

/**
 ** Created by 79260 at 2021/8/14 21:38.
 */
class KeyBoardUtil {
    companion object{
        fun hintKeyBoard(context: Context) {
            //拿到InputMethodManager
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            //如果window上view获取焦点 && view不为空
            context as Activity
            if (imm.isActive && context.currentFocus != null) {
                //拿到view的token 不为空
                if (context.currentFocus!!.windowToken != null) {
                    //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                    imm.hideSoftInputFromWindow(
                        context.currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    );
                }
            }
        }
    }
}