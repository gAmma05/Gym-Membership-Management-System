package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Member;
import model.Trainer;

public class MemberManagement extends IMemberManagement {

    // Method to create a new membership plan
    public void createMembership(Member member, String membershipName, int duration, int price, String benefit) throws ClassNotFoundException {
        String query = "INSERT INTO Membership_Plan (membershipName, durationMonths, price, benefit) VALUES (?, ?, ?, ?)";
        try (Connection con = new ConnectToSQLServer().getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setString(1, membershipName);
            pstmt.setInt(2, duration); // Define how long the membership lasts
            pstmt.setInt(3, price); // Set the price based on the membership
            pstmt.setString(4, benefit); // Add any benefits
            
            pstmt.executeUpdate();
            System.out.println("Membership created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating membership: " + e.getMessage());
        }
    }
    
    // Method to update a membership name and price
    public void updateMembership(int membershipId, String newMembershipName, int newPrice, int newDurationMonths) throws ClassNotFoundException {
    String query = "UPDATE Membership_Plan SET membershipName = ?, price = ?, durationMonths = ? WHERE membership_ID = ?";
    try (Connection con = new ConnectToSQLServer().getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)) {

        pstmt.setString(1, newMembershipName);
        pstmt.setInt(2, newPrice);  
        pstmt.setInt(3, newDurationMonths);  
        pstmt.setInt(4, membershipId);

        pstmt.executeUpdate();
        System.out.println("Membership updated successfully.");
    } catch (SQLException e) {
        System.out.println("Error updating membership: " + e.getMessage());
    }
}


    // Method to delete a membership
    public void deleteMembership(int membershipId) throws ClassNotFoundException {
        String query = "DELETE FROM Membership_Plan WHERE membership_ID = ?";
        try (Connection con = new ConnectToSQLServer().getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setInt(1, membershipId);
            
            pstmt.executeUpdate();
            System.out.println("Membership deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting membership: " + e.getMessage());
        }
    }

    // Method to create a new trainer
    public void createTrainer(Trainer trainer) throws ClassNotFoundException {
        String query = "INSERT INTO Trainer (trainer_id, expYear, availableSessions) VALUES (?, ?, ?)";
        try (Connection con = new ConnectToSQLServer().getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setInt(1, trainer.getId());
            pstmt.setInt(2, trainer.getExpYear());
            pstmt.setString(3, trainer.getAvailableSessions()); // Added availableSessions
            pstmt.executeUpdate();
            System.out.println("Trainer created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating trainer: " + e.getMessage());
        }
    }

    // Method to update trainer information
    public void updateTrainer(int trainerId, int newExpYear, String newAvailableSessions) throws ClassNotFoundException {
        String query = "UPDATE Trainer SET expYear = ?, availableSessions = ? WHERE trainer_id = ?";
        try (Connection con = new ConnectToSQLServer().getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setInt(1, newExpYear);
            pstmt.setString(2, newAvailableSessions); // Added availableSessions
            pstmt.setInt(3, trainerId);
            pstmt.executeUpdate();
            System.out.println("Trainer updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating trainer: " + e.getMessage());
        }
    }

    // Method to assign a trainer to a member
    public void assignTrainerToMember(int trainerId, int memberId) throws ClassNotFoundException {
        String query = "INSERT INTO TrainingSession (TrainerID, MemberID) VALUES (?, ?)";
        try (Connection con = new ConnectToSQLServer().getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            pstmt.setInt(1, trainerId);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();
            System.out.println("Trainer assigned to member successfully.");
        } catch (SQLException e) {
            System.out.println("Error assigning trainer to member: " + e.getMessage());
        }
    }

    // Method to schedule a training session
    public void scheduleTrainingSession(int trainerId, int memberId, String location, String sessionTime, int durationByMinutes) throws ClassNotFoundException {
        String query = "INSERT INTO TrainingSession (TrainerID, MemberID, session_time, location, durationByMinutes) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = new ConnectToSQLServer().getConnection();
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
}
