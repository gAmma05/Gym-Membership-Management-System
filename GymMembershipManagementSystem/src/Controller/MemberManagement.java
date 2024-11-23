package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberManagement {

    // Method to track member progress (workout history and health metrics)
    public void trackMemberProgress(int memberId) throws ClassNotFoundException {
        String query = "SELECT memberName, dateCreated, workoutHistory, healthMetrics " + 
                       "FROM MemberProgress WHERE memberID = ?";
        
        try (Connection con = ConnectToSQLServer.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setInt(1, memberId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Workout History and Health Metrics for Member ID: " + memberId);
                System.out.println("---------------------------------------------------");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.println("Member Name: " + rs.getString("memberName"));
                    System.out.println("Date Created: " + rs.getDate("dateCreated"));
                    System.out.println("Workout History: " + rs.getString("workoutHistory"));
                    System.out.println("Health Metrics: " + rs.getString("healthMetrics"));
                    System.out.println("---------------------------------------------------");
                }
                
                if (!hasData) {
                    System.out.println("No progress records found for this member.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error tracking member progress: " + e.getMessage());
        }
    }
}
