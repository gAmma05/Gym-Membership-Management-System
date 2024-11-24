package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerManagement implements ITrainerManagement {

    @Override
    public void addMemberProgress(int memberID, String memberName, String dateCreated, String workoutHistory, String healthMetrics) {
        String query = "INSERT INTO MemberProgress (memberID, memberName, dateCreated, workoutHistory, healthMetrics) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);
            pstmt.setString(2, memberName);
            pstmt.setString(3, dateCreated);
            pstmt.setString(4, workoutHistory);
            pstmt.setString(5, healthMetrics);

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
    public void updateMemberProgress(int progressID, String workoutHistory, String healthMetrics) {
        String query = "UPDATE MemberProgress SET workoutHistory = ?, healthMetrics = ? WHERE progressID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, workoutHistory);
            pstmt.setString(2, healthMetrics);
            pstmt.setInt(3, progressID);

            pstmt.executeUpdate();
            System.out.println("Member progress updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    // Method to view member progress
    @Override
    public void viewMemberProgress(int memberId) {
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
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }
}
