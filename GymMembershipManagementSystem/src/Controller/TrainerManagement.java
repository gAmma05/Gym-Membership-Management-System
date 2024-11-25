package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.MemberProgress;

public class TrainerManagement implements ITrainerManagement {

    @Override
    public void addMemberProgress(MemberProgress mpr) {
        String query = "INSERT INTO MemberProgress (progressID, memberID, memberName, dateCreated, workoutHistory, healthMetrics) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            LocalDate dateCreated = mpr.getDateCreated(); // Get the current date
            Date sqlDateCreated = Date.valueOf(dateCreated); // Convert to SQL Date

            pstmt.setInt(1, mpr.getProgressID());
            pstmt.setInt(2, mpr.getMemberID());
            pstmt.setString(3, mpr.getMemberName());
            pstmt.setDate(4, sqlDateCreated);
            pstmt.setString(5, mpr.getWorkoutHistory());
            pstmt.setString(6, mpr.getHealthMetrics());

            pstmt.executeUpdate();
            System.out.println("Member progress added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    // Method to update member progress
    @Override
    public void updateMemberProgress(int progressID, LocalDate dateCreated, String workoutHistory, String healthMetrics) {
        String query = "UPDATE MemberProgress SET dateCreated = ?, workoutHistory = ?, healthMetrics = ? WHERE progressID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            Date sqlDateCreated = Date.valueOf(dateCreated); // Convert to SQL Date
            
            pstmt.setDate(1, sqlDateCreated);
            pstmt.setString(2, workoutHistory);
            pstmt.setString(3, healthMetrics);
            pstmt.setInt(4, progressID);

            pstmt.executeUpdate();
            System.out.println("Member progress updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }
    
    @Override
    public void deleteMemberProgress(int progressID){
        String query = "DELETE FROM MemberProgress WHERE progressID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, progressID);

            pstmt.executeUpdate();
            System.out.println("Member progess deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    // Method to view member progress
    @Override
    public boolean viewMemberProgress(int progressID) {
        String query = "SELECT * FROM MemberProgress WHERE progressID = ?";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, progressID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hasResults = true;
                System.out.println("Progress ID: " + rs.getInt("progressID"));
                System.out.println("Member Name: " + rs.getString("memberName"));
                System.out.println("Date Created: " + rs.getString("dateCreated"));
                System.out.println("Workout History: " + rs.getString("workoutHistory"));
                System.out.println("Health Metrics: " + rs.getString("healthMetrics"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    @Override
    public boolean viewAssignedMember(int trainerID) {
        String query = "SELECT ts.sessionID, m.memberName "
                + "FROM TrainingSession ts "
                + "JOIN Trainer t ON ts.trainerID = t.trainerID "
                + "JOIN Member m ON ts.memberID = m.memberID "
                + "WHERE ts.trainerID = ?";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, trainerID);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("%-10s %-20s%n", "Session ID ", "Member Name");

            while (rs.next()) {
                hasResults = true;
                int sessionID = rs.getInt("sessionID");
                String memberName = rs.getString("memberName");
                System.out.printf("%-10d %-20s%n", sessionID, memberName);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    @Override
    public boolean viewSchedule(int trainerID) {
        String query = "SELECT ts.sessionID, ts.sessionTime, ts.location, ts.durationByMinutes, "
                + "t.trainerName, m.memberName "
                + "FROM TrainingSession ts "
                + "JOIN Trainer t ON ts.trainerID = t.trainerID "
                + "JOIN Member m ON ts.memberID = m.memberID "
                + "WHERE ts.trainerID = ?";
        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, trainerID);
            ResultSet rs = pstmt.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.printf("%-10s %-20s %-15s %-15s %-5s%n", "Session ID ", "Member Name", "Session Time", "Location", "Duration (Minutes)");
            while (rs.next()) {

                hasResults = true;
                int sessionID = rs.getInt("sessionID");
                String sessionTimeRaw = rs.getString("sessionTime");
                //LocalDateTime sessionTime = LocalDateTime.parse(sessionTimeRaw.replace(' ', 'T'));
                String formattedSessionTime = (sessionTimeRaw != null)
                        ? LocalDateTime.parse(sessionTimeRaw.replace(' ', 'T')).format(formatter)
                        : "N/A";

                String location = rs.getString("location");
                location = (location != null) ? location : "N/A";

                int durationByMinutes = rs.getInt("durationByMinutes");

                //String trainerName = rs.getString("trainerName");
                String memberName = rs.getString("memberName");

                System.out.printf("%-10s %-20s %-15s %-15s %-5s%n", sessionID, memberName, formattedSessionTime, location, durationByMinutes);
            }

        } catch (SQLException e) {
            System.out.println("Error scheduling training session: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }
}
