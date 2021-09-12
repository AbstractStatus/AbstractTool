package com.abstractplayer.abstracttool.main.person


import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager

import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityMainPersonSettingBinding
import com.abstractplayer.abstracttool.main.person.setting.SettingAdapter
import com.abstractplayer.abstracttool.main.person.setting.SettingVector

class MainPersonSettingActivity : BaseActivity() {
    companion object{
        const val TAG = "MainPersonSettingActivity"
        const val KEY_INTENT_PAGE_CODE = "KEY_INTENT_PAGE_CODE"
        const val KEY_INTENT_PAGE_TITLE = "KEY_INTENT_PAGE_TITLE"
    }

    private val binding by lazy { ActivityMainPersonSettingBinding.inflate(layoutInflater) }
    private val settingAdapter by lazy { SettingAdapter(this) }

    private val curPageCode by lazy { intent.getIntExtra(KEY_INTENT_PAGE_CODE, 0) }
    private val curPageTitle by lazy { intent.getStringExtra(KEY_INTENT_PAGE_TITLE)}

    private val settingEntityList by lazy { SettingVector.entityVector[curPageCode] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainSettingToolBar.titleName = curPageTitle ?: "设置"

        binding.mainSettingRecycleView.layoutManager = LinearLayoutManager(this)
        binding.mainSettingRecycleView.adapter = settingAdapter

        settingAdapter.settingList = settingEntityList
        settingAdapter.notifyDataSetChanged()
    }
}