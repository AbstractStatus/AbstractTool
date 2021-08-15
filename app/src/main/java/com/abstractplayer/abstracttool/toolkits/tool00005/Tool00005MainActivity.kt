package com.abstractplayer.abstracttool.toolkits.tool00005

import android.os.Bundle
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityTool00005MainBinding

class Tool00005MainActivity : BaseActivity() {
    companion object{
        const val TAG = "Tool00005MainActivity"
    }

    private lateinit var binding: ActivityTool00005MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00005MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener(){
        binding.tool00005MainBallGenerate.container.setOnClickListener {
            val balls: List<Int> = Tool00005Util.getRandomBallList()
            var strRedBalls = ""
            strRedBalls += (if (balls[0] < 10) "0${balls[0]}" else balls[0].toString()) + "  "
            strRedBalls += (if (balls[1] < 10) "0${balls[1]}" else balls[1].toString()) + "  "
            strRedBalls += (if (balls[2] < 10) "0${balls[2]}" else balls[2].toString()) + "\n\n"
            strRedBalls += (if (balls[3] < 10) "0${balls[3]}" else balls[3].toString()) + "  "
            strRedBalls += (if (balls[4] < 10) "0${balls[4]}" else balls[4].toString()) + "  "
            strRedBalls += (if (balls[5] < 10) "0${balls[5]}" else balls[5].toString())
            binding.tool00005MainBallRed.text = strRedBalls
            binding.tool00005MainBallBlue.text = (if (balls[6] < 10) "0${balls[6]}" else balls[6].toString())
        }
    }
}