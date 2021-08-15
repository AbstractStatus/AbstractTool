package com.abstractplayer.abstracttool.toolkits.tool00003.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abstractplayer.abstracttool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by 79260 at 2021/8/8 20:12.
 */
public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder> {
    private List<ScheduleEntity> scheduleList = new ArrayList<>();
    private Context context;

    public List<ScheduleEntity> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleEntity> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tool00003_item_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleListViewHolder holder, int position) {
        holder.textView.setText(scheduleList.get(position).getScheduleName());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleListViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ScheduleListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tool00003_item_schedule_text);
        }
    }
}
