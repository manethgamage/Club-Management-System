package com.example.oopp;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private String studentPassword;

    //store the clubs that the student has joined
    private List<Club> joinedClubs;

    public Student(String studentId, String studentName, String studentPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.joinedClubs = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public List<Club> getJoinedClubs() {
        return joinedClubs;
    }

    public void setJoinedClubs(List<Club> joinedClubs) {
        this.joinedClubs = joinedClubs;
    }

    //when student joins a club.It'll add the istance to this
    public void joinClub(Club club){
        joinedClubs.add(club);
        System.out.println("You joined the club: "+ club.getClubName());  //change it to alert
    }
}
