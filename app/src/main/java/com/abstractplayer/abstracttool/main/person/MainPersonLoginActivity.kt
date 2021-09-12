package com.abstractplayer.abstracttool.main.person

import android.os.Bundle
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityMainPersonLoginBinding

class MainPersonLoginActivity : BaseActivity() {
    private val binding by lazy{ ActivityMainPersonLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}