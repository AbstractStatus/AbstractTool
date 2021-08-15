package com.abstractplayer.abstracttool.toolkits.tool00003.calendar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by 79260 at 2021/8/7 19:59.
 */
public class SystemCalendarRepository {

    public static List<SystemScheduleEntity> getScheduleData(ContentResolver contentResolver){
        List<SystemScheduleEntity> scheduleEntities = new ArrayList<>();
        Uri uri = CalendarContract.Instances.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()){
            SystemScheduleEntity scheduleEntity = new SystemScheduleEntity();
//            scheduleEntity.id = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.ORIGINAL_ID));
            scheduleEntities.add(scheduleEntity);
        }

        return scheduleEntities;
    }

    public static class SystemScheduleEntity{
        String id;
        String title;
        String location;
        String startTime;
        String endTime;
        String description;

        @NotNull
        public String toString(){
            return "id: " + id
                    + "\ntitle: " + title
                    + "\nlocation" + location
                    + "\nstartTime: " + startTime
                    + "\nendTime" + endTime
                    + "\ndescription" + description;
        }
    }
}
