package com.example.oopp;

public class Club {
    private int clubId;
    private String clubName;
    private String advisorId;

    public Club(int clubId, String clubName, String clubDescription) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    public Club(String clubName, String clubDescription,  String advisorId) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.advisorId = advisorId;
    }

    public Club(String clubName, String clubDescription) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    public String getClubDescription() {
        return clubDescription;
    }



    private String clubDescription;

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public Club() {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }


    public Club(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setClubId(String clubId) {
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public void setTeacherId(String teacherId) {
    }

}
