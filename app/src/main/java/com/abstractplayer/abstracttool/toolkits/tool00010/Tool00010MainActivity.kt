package com.abstractplayer.abstracttool.toolkits.tool00010

import android.os.Bundle
import android.widget.Toast
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.common.utils.SysServiceTool
import com.abstractplayer.abstracttool.databinding.ActivityTool00010MainBinding

class Tool00010MainActivity : BaseActivity() {
    companion object{
        const val TAG = "Tool00010MainActivity"
    }

    private val binding by lazy { ActivityTool00010MainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tool00010MainAdd.container.setOnClickListener {
            if(!isDigitsStr(binding.tool00010MainNum1.text.toString())){
                Toast.makeText(this, "第一空不存在合法的整数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!isDigitsStr(binding.tool00010MainNum2.text.toString())){
                Toast.makeText(this, "第二空不存在合法的整数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.tool00010MainRes.text = multiply(
                binding.tool00010MainNum1.text.toString(),
                binding.tool00010MainNum2.text.toString()
            )
        }

        binding.tool00010MainCopy.container.setOnClickListener {
            SysServiceTool.copyContentToClipboard(binding.tool00010MainRes.text.toString(), this)
        }
    }

    //判断是不是正整数字符串（纯数字）
    private fun isDigitsStr(s: String): Boolean{
        if(s.isEmpty()){
            return false
        }

        if(s.isNotEmpty() && s[0] == '0'){
            return false
        }

        val charArray = s.toCharArray()
        for(c in charArray){
            if((c.toInt() < 48) or (c.toInt() > 57)){
                return false
            }
        }

        return true
    }

    private fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") {
            return "0"
        }
        val m = num1.length
        val n = num2.length
        val ansArr = IntArray(m + n)
        for (i in m - 1 downTo 0) {
            val x = num1[i] - '0'
            for (j in n - 1 downTo 0) {
                val y = num2[j] - '0'
                ansArr[i + j + 1] += x * y
            }
        }
        for (i in m + n - 1 downTo 1) {
            ansArr[i - 1] += ansArr[i] / 10
            ansArr[i] %= 10
        }
        var index = if (ansArr[0] == 0) 1 else 0
        val ans = StringBuffer()
        while (index < m + n) {
            ans.append(ansArr[index])
            index++
        }
        return ans.toString()
    }
}