package com.abstractplayer.abstracttool.toolkits.tool00008

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityTool00008MainBinding

class Tool00008MainActivity : BaseActivity() {
    companion object{
        const val TAG = "Tool00008MainActivity"
    }

    private lateinit var binding: ActivityTool00008MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00008MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        val list = Tool00008Util.minDistance("我爱你！", "我爱曹操操你祖宗")
        Log.d(TAG, "onCreate: ${list.size}")
        for(l in list){
            Log.d(TAG, "onCreate: $l")
        }
    }
}