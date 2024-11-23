
package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerManagement {

    // Method to schedule training sessions
    public void scheduleTrainingSession(int trainerId, int memberId, String location, String sessionTime, int durationByMinutes) throws ClassNotFoundException {
        String query = "INSERT INTO TrainingSession (trainerID, memberID, sessionTime, location, durationByMinutes) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, trainerId);
            pstmt.setInt(2, memberId);
            pstmt.setString(3, sessionTime);
            pstmt.setString(4, location);
            pstmt.setInt(5, durationByMinutes);

            pstmt.executeUpdate();
            System.out.println("Training session scheduled successfully.");
        } catch (SQLException e) {
            System.out.println("Error scheduling training session: " + e.getMessage());
        }
    }

    // Method to track member progress: Add progress record
    public void addMemberProgress(int memberId, String memberName, String dateCreated, String workoutHistory, String healthMetrics) throws ClassNotFoundException {
        String query = "INSERT INTO MemberProgress (memberID, memberName, dateCreated, workoutHistory, healthMetrics) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberId);
            pstmt.setString(2, memberName);
            pstmt.setString(3, dateCreated);
            pstmt.setString(4, workoutHistory);
            pstmt.setString(5, healthMetrics);

            pstmt.executeUpdate();
            System.out.println("Member progress added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding member progress: " + e.getMessage());
        }
    }

    // Method to update member progress
    public void updateMemberProgress(int progressId, String workoutHistory, String healthMetrics) throws ClassNotFoundException {
        String query = "UPDATE MemberProgress SET workoutHistory = ?, healthMetrics = ? WHERE progressID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, workoutHistory);
            pstmt.setString(2, healthMetrics);
            pstmt.setInt(3, progressId);

            pstmt.executeUpdate();
            System.out.println("Member progress updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating member progress: " + e.getMessage());
        }
    }

    // Method to view member progress
    public void viewMemberProgress(int memberId) throws ClassNotFoundException {
        String query = "SELECT * FROM MemberProgress WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Progress ID: " + rs.getInt("progressID"));
                System.out.println("Member Name: " + rs.getString("memberName"));
                System.out.println("Date Created: " + rs.getString("dateCreated"));
                System.out.println("Workout History: " + rs.getString("workoutHistory"));
                System.out.println("Health Metrics: " + rs.getString("healthMetrics"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing member progress: " + e.getMessage());
        }
    }
}
