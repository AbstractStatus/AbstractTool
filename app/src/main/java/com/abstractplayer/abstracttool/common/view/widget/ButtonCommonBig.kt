package com.abstractplayer.abstracttool.common.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.abstractplayer.abstracttool.R

/**
 * 在使用点击事件时候，必须设置 控件名称.container.setXxxListener()
 */

class ButtonCommonBig @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var textView: TextView
    var container: ConstraintLayout
    private var buttonTitle: String

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ButtonCommonBig, 0, 0)
                .apply {
                    try {
                        buttonTitle = getString(R.styleable.ButtonCommonBig_button_title).toString()
                    }finally {
                        recycle()
                    }
                }

        LayoutInflater.from(context).inflate(R.layout.common_button, this)
        textView = findViewById(R.id.common_btn_title)
        container = findViewById(R.id.common_btn_container)
        textView.text = buttonTitle
    }

    fun setText(s: String){
        textView.text = s
    }

}