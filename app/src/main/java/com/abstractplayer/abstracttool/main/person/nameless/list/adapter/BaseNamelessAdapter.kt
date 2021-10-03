package com.abstractplayer.abstracttool.main.person.nameless.list.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.view.widget.ButtonCommonBig
import com.abstractplayer.abstracttool.main.person.nameless.NamelessMainActivity
import com.abstractplayer.abstracttool.main.person.nameless.list.entity.BaseNamelessEntity

/**
 ** Created by AbstractStatus at 2021/9/30 15:01.
 */
class BaseNamelessAdapter(val activity: Activity) : RecyclerView.Adapter<BaseNamelessAdapter.BaseNamelessViewHolder>(){
    companion object {
        private const val TAG = "BaseNamelessAdapter"
    }

    var dataList: List<BaseNamelessEntity> = ArrayList()

    inner class BaseNamelessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: ButtonCommonBig = itemView.findViewById(R.id.btn_nameless_item_base)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNamelessViewHolder {
        return BaseNamelessViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.main_nameless_item_base,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BaseNamelessViewHolder, position: Int) {
        holder.button.container.setOnClickListener {
            val intent = Intent(activity, NamelessMainActivity::class.java)
            activity.startActivityForResult(intent, 1)
        }
    }
}