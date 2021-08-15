package com.abstractplayer.abstracttool.toolkits.tool00004

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.common.utils.SysServiceTool
import com.abstractplayer.abstracttool.databinding.ActivityTool00004MainBinding

class Tool00004MainActivity : BaseActivity() {
    companion object{
        const val TAG: String = "Tool00004MainActivity"
    }

    private lateinit var binding: ActivityTool00004MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00004MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setListener()
    }

    private fun initView(){
        binding.tool00004MainCopy.visibility = View.GONE
        binding.tool00004MainRes.visibility = View.GONE
    }

    private fun setListener(){
        binding.tool00004MainAdd.container.setOnClickListener {
            val addDigitsNum1 = binding.tool00004MainNum1.text.toString()
            val addDigitsNum2 = binding.tool00004MainNum2.text.toString()

            if(!Tool00004Util.isDigitsStr(addDigitsNum1)){
                Toast.makeText(this, "第一个空不存在合法的正整数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!Tool00004Util.isDigitsStr(addDigitsNum2)){
                Toast.makeText(this, "第二个空不存在合法的正整数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val addDigitsRes = Tool00004Util.addStrings(addDigitsNum1, addDigitsNum2)
            binding.tool00004MainCopy.visibility = View.VISIBLE
            binding.tool00004MainRes.visibility = View.VISIBLE

            binding.tool00004MainRes.text = addDigitsRes
        }

        binding.tool00004MainCopy.container.setOnClickListener {
            SysServiceTool.copyContentToClipboard(binding.tool00004MainRes.text.toString(), this)
        }
    }
}