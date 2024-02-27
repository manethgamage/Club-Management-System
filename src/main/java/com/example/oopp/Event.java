package com.example.oopp;

public class Event extends ScheduleActivity {
    private String eventType;

    public Event() {
        super();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Event(int eventId, String title, String date, String time, String location, String descrition,int clubId,String advisorId, String eventType) {
        super(eventId, title, date, time, location, descrition,clubId,advisorId);
        this.eventType = eventType;

    }

}
