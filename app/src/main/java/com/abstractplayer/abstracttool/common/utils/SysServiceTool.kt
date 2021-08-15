package com.abstractplayer.abstracttool.common.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import java.lang.Exception

/**
 ** Created by 79260 at 2021/8/11 20:26.
 */
class SysServiceTool {
    companion object{
        //复制文本到剪切板
        fun copyContentToClipboard(content: String, context : Context) {
            try {
                //获取剪贴板管理器：
                val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;
                // 创建普通字符型ClipData
                val mClipData = ClipData.newPlainText("Label", content);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
            }catch (e: Exception){
                Toast.makeText(context, "复制失败", Toast.LENGTH_SHORT).show();
            }
        }


    }
}