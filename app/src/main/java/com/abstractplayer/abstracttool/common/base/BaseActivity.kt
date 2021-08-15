package com.abstractplayer.abstracttool.common.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abstractplayer.abstracttool.common.utils.LogUtil
import com.abstractplayer.abstracttool.common.utils.WindowUtil

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTwoBar()

        LogUtil.d("")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d("")
    }

    override fun onResume() {
        super.onResume()

        setTwoBar()

        LogUtil.d("")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d("")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d("")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.d("")
    }

    private fun setTwoBar(){
        WindowUtil.setImmerseStatusFullScreen(window)
    }

}