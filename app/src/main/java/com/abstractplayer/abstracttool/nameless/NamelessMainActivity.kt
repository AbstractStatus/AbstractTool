package com.abstractplayer.abstracttool.nameless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityNamelessMainBinding

class NamelessMainActivity : BaseActivity() {
    companion object{
        const val TAG = "NamelessMainActivity"
    }

    private val binding by lazy { ActivityNamelessMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}