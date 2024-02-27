package com.example.oopp;

public class Activity extends ScheduleActivity{
    private String activityType;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Activity(int eventId, String title, String date, String time, String location, String descrition,int clubId,String advisorId, String activityType) {
        super(eventId, title, date, time, location, descrition,clubId,advisorId);
        this.activityType = activityType;
    }
}
