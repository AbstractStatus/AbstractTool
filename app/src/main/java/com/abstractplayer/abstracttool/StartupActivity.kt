package com.abstractplayer.abstracttool

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityStartupBinding
import com.abstractplayer.abstracttool.main.MainActivity

class StartupActivity : BaseActivity() {
    private lateinit var binding: ActivityStartupBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartup()
    }

    private fun setStartup(){
        binding.tvStartupTitle.alpha = 0f
        binding.ivStartupLogo.alpha = 0f
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener {
            val percent = it.animatedValue
            percent as Float
            binding.tvStartupTitle.alpha = percent
            binding.ivStartupLogo.alpha = percent
        }

        valueAnimator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
               initAppData()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        valueAnimator.start()
    }


    private fun initAppData(){

        startToMainActivity()
    }


    private fun startToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}