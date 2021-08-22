package com.abstractplayer.abstracttool.main.tool.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.abstractplayer.abstracttool.R

class MainToolBarSearchAndBtn @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var imageView: ImageView
    private var editText: EditText

    private lateinit var onSearchTextChange: (s: String) -> Unit
    private lateinit var onRightButtonClick: () -> Unit

    fun setOnSearchTextChange(onSearchTextChange: (s: String) -> Unit){
        this.onSearchTextChange = onSearchTextChange
    }

    fun setOnRightButtonClick(onRightButtonClick: () -> Unit){
        this.onRightButtonClick = onRightButtonClick
    }



    init {
        LayoutInflater.from(context).inflate(R.layout.main_tool_bar_search_and_btn, this)
        imageView = findViewById(R.id.btn_more)
        editText = findViewById(R.id.edit_search)

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onSearchTextChange(s.toString())
            }

        })

        imageView.setOnClickListener {

        }

    }
}