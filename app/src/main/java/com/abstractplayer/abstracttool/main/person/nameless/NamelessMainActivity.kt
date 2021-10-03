package com.abstractplayer.abstracttool.main.person.nameless

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityNamelessMainBinding
import com.abstractplayer.abstracttool.main.person.nameless.list.adapter.BaseNamelessAdapter
import com.abstractplayer.abstracttool.main.person.nameless.list.entity.BaseNamelessEntity

class NamelessMainActivity : BaseActivity() {
    companion object{
        const val TAG = "NamelessMainActivity"
    }

    private val listAdapter by lazy { BaseNamelessAdapter(this) }
    private val listData by lazy { ArrayList<BaseNamelessEntity>() }

    private val binding by lazy { ActivityNamelessMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = listAdapter
        repeat((2..6).random()){
            listData.add(BaseNamelessEntity(""))
        }
        listAdapter.dataList = listData
        listAdapter.notifyDataSetChanged()
    }
}