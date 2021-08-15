package com.abstractplayer.abstracttool.main.tool.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.constant.StringConstant
import com.abstractplayer.abstracttool.common.key.BundleKey

class MainToolBarOnlyBackup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var textView: TextView
    private var imageView: ImageView
    private var titleName: String
    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CommonBarOnlyBackup, 0, 0)
            .apply {
                try {
                    titleName = getString(R.styleable.CommonBarOnlyBackup_title_name).toString()
                }finally {
                    recycle()
                }
            }

        LayoutInflater.from(context).inflate(R.layout.main_tool_bar_only_backup, this)
        textView = findViewById(R.id.textView)
        textView.text = titleName

        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            context as Activity
            context.finish()
        }

        context as Activity
        val intent = context.intent
        val toolName = intent.getStringExtra(BundleKey.TOOL_NAME)
        if(toolName?.let { StringConstant.isNotNullAndEmpty(it) }!!){
            textView.text = toolName
        }
    }

    fun setText(s: String){
        textView.text = s
    }

}