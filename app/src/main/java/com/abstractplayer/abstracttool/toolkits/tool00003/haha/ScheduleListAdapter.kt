package com.abstractplayer.abstracttool.toolkits.tool00003.haha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abstractplayer.abstracttool.R

/**
 ** Created by 79260 at 2021/8/7 14:19.
 */
class ScheduleListAdapter() : RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>(){
    var scheduleList: List<ScheduleEntity> = ArrayList()

    inner class ScheduleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tool00003_item_schedule_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val view = ScheduleListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.tool00003_item_schedule, parent, false))

        return view
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        holder.textView.text = scheduleList[position].scheduleName
    }
}