package com.example.oopp;

public class MembershipRequest {
    private String clubName;
    private String studentId;

    public MembershipRequest(String clubName, String studentId) {
        this.clubName = clubName;
        this.studentId = studentId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "MembershipRequest{" +
                "clubName='" + clubName + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
