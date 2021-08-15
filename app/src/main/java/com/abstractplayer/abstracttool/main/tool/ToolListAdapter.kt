package com.abstractplayer.abstracttool.main.tool

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.key.BundleKey
import com.abstractplayer.abstracttool.common.view.widget.ButtonCommonBig


class ToolListAdapter(private val activity: Activity) : RecyclerView.Adapter<ToolListAdapter.ToolListViewHolder>() {
    private val toolList = ToolList.toolList

    inner class ToolListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toolButtonCommonBig: ButtonCommonBig = itemView.findViewById(R.id.main_item_tool_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolListViewHolder {
        return ToolListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.main_tool_item, parent, false))
    }

    override fun getItemCount(): Int {
        return toolList.size
    }

    override fun onBindViewHolder(holder: ToolListViewHolder, position: Int) {
        holder.toolButtonCommonBig.setText(toolList[position].info.getName())

        holder.toolButtonCommonBig.container.setOnClickListener {
            val intent = Intent(activity, toolList[position].activity)
            intent.putExtra(BundleKey.TOOL_NAME, toolList[position].info.getName())
            activity.startActivity(intent)
        }

        holder.toolButtonCommonBig.container.setOnLongClickListener {
            val intent = Intent(activity, ToolInfoActivity::class.java)
            activity.startActivity(intent)
            true
        }
    }
}