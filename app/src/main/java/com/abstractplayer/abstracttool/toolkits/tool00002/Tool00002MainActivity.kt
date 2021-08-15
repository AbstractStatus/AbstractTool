package com.abstractplayer.abstracttool.toolkits.tool00002

import android.os.Bundle
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityTool00002MainBinding

class Tool00002MainActivity : BaseActivity() {
    companion object{
        const val tag = "Tool00002MainActivity"
    }

    private lateinit var binding: ActivityTool00002MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00002MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener(){
        binding.tool00002Calculate.container.setOnClickListener {
            binding.tool00002MathView.solveMathView()
        }
    }
}