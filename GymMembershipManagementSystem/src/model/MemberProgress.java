/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class MemberProgress {
    private int progressID;
    private int memberID;
    private Date date;
    private String workoutHistory;
    private String healthMetrics;

    // Constructor
    public MemberProgress(int memberID, Date date, String workoutHistory, String healthMetrics) {
        this.memberID = memberID;
        this.date = date;
        this.workoutHistory = workoutHistory;
        this.healthMetrics = healthMetrics;
    }

    // Getters and Setters
    public int getProgressID() {
        return progressID;
    }

    public void setProgressID(int progressID) {
        this.progressID = progressID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWorkoutHistory() {
        return workoutHistory;
    }

    public void setWorkoutHistory(String workoutHistory) {
        this.workoutHistory = workoutHistory;
    }

    public String getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(String healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    @Override
    public String toString() {
        return "MemberProgress{" +
                "progressID=" + progressID +
                ", memberID=" + memberID +
                ", date=" + date +
                ", workoutHistory='" + workoutHistory + '\'' +
                ", healthMetrics='" + healthMetrics + '\'' +
                '}';
    }
}