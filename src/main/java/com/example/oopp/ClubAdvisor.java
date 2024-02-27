package com.example.oopp;

import java.util.ArrayList;
import java.util.List;
public class ClubAdvisor {

    private String teacherId;
    private String teacherName;
    private String teacherPassword;

    //store the clubs that the club advisor manages
    private List<Club> managedClubs;

    public ClubAdvisor(String teacherId, String teacherName, String teacherPassword) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
        this.managedClubs = new ArrayList<>();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public List<Club> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(List<Club> managedClubs) {
        this.managedClubs = managedClubs;
    }

    //when club advisor creates a club.It'll add the istance to this
    public void createClub(String clubName){
        Club newClub = new Club(clubName);
        managedClubs.add(newClub);
        System.out.println(teacherName + " created the club : " + clubName);  //change it to alert
    }

}
