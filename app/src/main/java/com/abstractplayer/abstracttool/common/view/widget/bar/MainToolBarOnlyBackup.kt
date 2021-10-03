package com.abstractplayer.abstracttool.common.view.widget.bar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.key.BundleKey
import com.abstractplayer.abstracttool.common.utils.DimenUtil

class MainToolBarOnlyBackup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var textView: TextView?
    private var imageView: ImageView?
    var titleName: String = ""
    set(value){
        field = value
        textView?.text = value
    }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CommonBarOnlyBackup, 0, 0)
            .apply {
                try {
                    titleName = getString(R.styleable.CommonBarOnlyBackup_title_name) ?: ""
                }finally {
                    recycle()
                }
            }

        LayoutInflater.from(context).inflate(R.layout.main_tool_bar_only_backup, this)

        DimenUtil.setStatusBarPadding(context, this)

        textView = findViewById(R.id.textView)
        textView?.text = titleName

        imageView = findViewById(R.id.imageView)
        imageView?.setOnClickListener {
            context as Activity
            context.finish()
        }

        context as Activity
        val intent = context.intent
        val toolName = intent.getStringExtra(BundleKey.TOOL_NAME)
        if(toolName != null){
            textView?.text = toolName
        }
    }

}