package com.abstractplayer.abstracttool.common.view.widget

import android.content.Context
import android.util.AttributeSet

import com.abstractplayer.abstracttool.common.utils.SysServiceTool

/**
 ** Created by 79260 at 2021/8/11 20:22.
 */
class TextViewLongClickCopy @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {
    init {

    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        super.setOnLongClickListener(l)
        SysServiceTool.copyContentToClipboard(text.toString(), context)
    }
}