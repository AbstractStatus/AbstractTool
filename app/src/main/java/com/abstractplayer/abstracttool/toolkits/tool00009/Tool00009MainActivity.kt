package com.abstractplayer.abstracttool.toolkits.tool00009

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityTool00009MainBinding

class Tool00009MainActivity : BaseActivity() {
    private var colorInt: UInt = 0u
    private var aInt: Int = 0
    private var rInt: Int = 0
    private var gInt: Int = 0
    private var bInt: Int = 0
    private var adverseColorInt: UInt = 0u


    companion object{
        const val TAG = "Tool00009MainActivity"
    }

    private lateinit var binding: ActivityTool00009MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTool00009MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener(){
        binding.tool00009SeekBarA.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                aInt = progress
                setColorInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


        binding.tool00009SeekBarR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rInt = progress
                setColorInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


        binding.tool00009SeekBarG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gInt = progress
                setColorInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


        binding.tool00009SeekBarB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bInt = progress
                setColorInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }


    private fun setColorInt(){
        colorInt = (aInt * 256 * 256 * 256 + rInt * 256 * 256 + gInt * 256 + bInt).toUInt()
        Log.d(TAG, "onProgressChanged: $colorInt")
        binding.tool00009ColorView.setBackgroundColor(colorInt.toInt())
        adverseColorInt = (255 * 256 * 256 * 256 + (255 - rInt) * 256 * 256).toUInt()
        + (255 - gInt) * 256 + 255 - bInt
        binding.tool00009ColorText.setTextColor(adverseColorInt.toInt())
        binding.tool00009ColorText.text = colorInt.toString(16)
    }
}