package com.abstractplayer.abstracttool.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abstractplayer.abstracttool.R

class BaseToolMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_tool_main)
    }
}