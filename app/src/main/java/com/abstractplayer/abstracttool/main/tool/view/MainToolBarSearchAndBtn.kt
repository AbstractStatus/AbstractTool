package com.abstractplayer.abstracttool.main.tool.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.abstractplayer.abstracttool.R

class MainToolBarSearchAndBtn @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var imageView: ImageView
    var editText: EditText

    init {
        LayoutInflater.from(context).inflate(R.layout.main_tool_bar_search_and_btn, this)
        imageView = findViewById(R.id.btn_more)
        editText = findViewById(R.id.edit_search)
    }
}