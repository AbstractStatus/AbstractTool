package com.abstractplayer.abstracttool.main.person.setting

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.view.widget.ButtonCommonBig

/**
 ** Created by AbstractStatus at 2021/9/12 12:22.
 */
class SettingAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var settingList: List<SettingEntity>? = null

    companion object {
        const val TAG = "SettingAdapter"
    }

    inner class SettingViewBooleanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnBoolean: ButtonCommonBig = itemView.findViewById(R.id.main_setting_item_bool_btn)
    }

    inner class SettingViewVectorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnVector: ButtonCommonBig = itemView.findViewById(R.id.main_setting_item_vector_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            SettingEntity.TYPE_BOOLEAN -> {
                SettingViewBooleanHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.main_setting_item_boolean, parent, false))
            }
            SettingEntity.TYPE_VECTOR -> {
                SettingViewVectorHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.main_setting_item_vector, parent, false))
            }
            else -> {
                SettingViewBooleanHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.main_setting_item_boolean, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return settingList?.size ?: 0
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        SettingVector.initVector(context)
        when(holder){
            is SettingViewBooleanHolder -> {
                holder.btnBoolean.buttonTitle = settingList?.get(position)?.settingName ?: "未知设置"
                holder.btnBoolean.container.setOnClickListener {
                    SettingVector.actionVector[settingList?.get(position)?.settingId]?.let { it1 -> it1() }
                }
            }
            is SettingViewVectorHolder -> {
                holder.btnVector.buttonTitle = settingList?.get(position)?.settingName ?: "未知设置"
                holder.btnVector.container.setOnClickListener {
                    SettingVector.actionVector[settingList?.get(position)?.settingId]?.let { it1 -> it1() }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return settingList?.get(position)?.settingType ?: 0
    }
}