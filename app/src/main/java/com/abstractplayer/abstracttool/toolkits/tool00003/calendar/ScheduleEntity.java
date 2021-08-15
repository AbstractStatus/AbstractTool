package com.abstractplayer.abstracttool.toolkits.tool00003.calendar;

/**
 * * Created by 79260 at 2021/8/8 20:11.
 */
public class ScheduleEntity {
    String scheduleId;
    String scheduleName;
    String scheduleTime;

    public ScheduleEntity() {
    }

    public ScheduleEntity(String scheduleId, String scheduleName, String scheduleTime) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
