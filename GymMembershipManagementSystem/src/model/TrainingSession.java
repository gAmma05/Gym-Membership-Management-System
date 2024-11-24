/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

public class TrainingSession {

    private int sessionID;
    private int trainerID;
    private int memberID;
    private LocalDateTime sessionTime;
    private String location;
    private int durationByMinutes;

    // Constructor
    public TrainingSession(int sessionID, int trainerID, int memberID, LocalDateTime sessionTime, String location, int durationByMinutes) {
        this.sessionID = sessionID;
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

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalDateTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getLocation() {
        return location != null ? location : "N/A";
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
        return "TrainingSession{"
                + "sessionID=" + sessionID
                + ", trainerID=" + trainerID
                + ", memberID=" + memberID
                + ", sessionTime=" + sessionTime
                + ", location='" + location + '\''
                + ", durationByMinutes=" + durationByMinutes
                + '}';
    }
}
