/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class TrainingSession {
    private int sessionID;
    private int trainerID;
    private int memberID;
    private Date sessionTime;
    private String location;
    private int durationByMinutes;

    // Constructor
    public TrainingSession(int trainerID, int memberID, Date sessionTime, String location, int durationByMinutes) {
        this.trainerID = trainerID;
        this.memberID = memberID;
        this.sessionTime = sessionTime;
        this.location = location;
        this.durationByMinutes = durationByMinutes;
    }

    // Getters and Setters
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Date getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Date sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDurationByMinutes() {
        return durationByMinutes;
    }

    public void setDurationByMinutes(int durationByMinutes) {
        this.durationByMinutes = durationByMinutes;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "sessionID=" + sessionID +
                ", trainerID=" + trainerID +
                ", memberID=" + memberID +
                ", sessionTime=" + sessionTime +
                ", location='" + location + '\'' +
                ", durationByMinutes=" + durationByMinutes +
                '}';
    }
}

