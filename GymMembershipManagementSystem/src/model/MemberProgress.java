/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

public class MemberProgress {

    private int progressID;
    private int memberID;
    private String memberName;
    private LocalDate dateCreated;
    private String workoutHistory;
    private String healthMetrics;

    // Constructor
    public MemberProgress(int progressID, int memberID, String memberName, LocalDate dateCreated, String workoutHistory, String healthMetrics) {
        this.progressID = progressID;
        this.memberID = memberID;
        this.memberName = memberName;
        this.dateCreated = dateCreated;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDate(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
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
        return "MemberProgress{"
                + "progressID=" + progressID
                + ", memberID=" + memberID
                + ", memberName=" + memberName
                + ", date=" + dateCreated
                + ", workoutHistory='" + workoutHistory + '\''
                + ", healthMetrics='" + healthMetrics + '\''
                + '}';
    }
}
