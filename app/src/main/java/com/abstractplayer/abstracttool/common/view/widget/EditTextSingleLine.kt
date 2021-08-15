package com.abstractplayer.abstracttool.common.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import com.abstractplayer.abstracttool.R

/**
 ** Created by 79260 at 2021/8/13 22:01.
 */
class EditTextSingleLine @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : EditText(context, attrs, defStyleAttr) {

    init {
        setTextColor(resources.getColor(R.color.common_text))
        setHintTextColor(resources.getColor(R.color.common_text_hint))
        setBackgroundResource(R.drawable.common_shape_edit)
        gravity = Gravity.TOP or Gravity.START
    }
}