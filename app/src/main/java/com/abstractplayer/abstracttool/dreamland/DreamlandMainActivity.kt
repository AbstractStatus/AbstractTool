package com.abstractplayer.abstracttool.dreamland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abstractplayer.abstracttool.databinding.ActivityDreamlandMainBinding

class DreamlandMainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "DreamlandMainActivity"
    }

    private val binding by lazy { ActivityDreamlandMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}