package com.abstractplayer.abstracttool.main.tool.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.utils.DimenUtil


/**
 ** Created by 79260 at 2021/8/4 11:34.
 */
class MainToolBarInfo @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    companion object{
        const val TAG = "MainToolBarInfo"
    }
    
    private var textView: TextView
    private var ivBackup: ImageView
    var ivInfoAll: ImageView
    private lateinit var titleName: String

    init {
        LayoutInflater.from(context).inflate(R.layout.main_tool_bar_info, this)

        DimenUtil.setStatusBarPadding(context, this)

        textView = findViewById(R.id.bar_info_name)
        textView.text = "工具详情"

        ivBackup = findViewById(R.id.bar_info_backup)
        ivBackup.setOnClickListener {
            context as Activity
            context.finish()
        }

        ivInfoAll = findViewById(R.id.bar_info_all)

    }

    fun setText(s: String){
        textView.text = s
    }



}