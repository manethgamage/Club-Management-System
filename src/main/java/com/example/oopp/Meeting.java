package com.example.oopp;

public class Meeting extends ScheduleActivity{
    private String agenda;

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Meeting(int eventId, String title, String date, String time, String location, String description,int clubId,String advisorId, String agenda) {
        super(eventId, title, date, time, location, description,clubId,advisorId);
        this.agenda = agenda;
    }

}

