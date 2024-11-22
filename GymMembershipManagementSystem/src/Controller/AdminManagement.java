package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import Utils.CheckCondition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminManagement implements IAdminManagement {

    // Method to create a new membership plan
    CheckCondition cc = new CheckCondition();

    @Override
    public void createMembership(String membershipName, int duration, int price, String benefit) throws ClassNotFoundException {
        String query = "INSERT INTO MembershipPlan (membershipName, durationMonths, price, benefit) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
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
    @Override
    public void updateMembership(int membershipId, String newMembershipName, int newPrice, int newDurationMonths, String newBenefit) throws ClassNotFoundException {
        String query = "UPDATE MembershipPlan SET membershipName = ?, price = ?, durationMonths = ?, benefit = ? WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, newMembershipName);
            pstmt.setInt(2, newPrice);
            pstmt.setInt(3, newDurationMonths);
            pstmt.setString(4, newBenefit);
            pstmt.setInt(5, membershipId);

            pstmt.executeUpdate();
            System.out.println("Membership updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating membership: " + e.getMessage());
        }
    }

    // Method to delete a membership
    @Override
    public void deleteMembership(int membershipId) throws ClassNotFoundException {
        String query = "DELETE FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, membershipId);

            pstmt.executeUpdate();
            System.out.println("Membership deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting membership: " + e.getMessage());
        }
    }

    @Override
    public void deleteUsers(int userID) throws ClassNotFoundException {
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, userID);

            pstmt.executeUpdate();
            //System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public void deleteAdmins(int userID) throws ClassNotFoundException {
        String deleteAdminQuery = "DELETE FROM Admin WHERE adminID = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE id = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement deleteAdminStmt = con.prepareStatement(deleteAdminQuery);
                PreparedStatement deleteUserStmt = con.prepareStatement(deleteUserQuery)) {

            // Step 1: Delete the member from the Member table
            deleteAdminStmt.setInt(1, userID);
            int rowsAffected = deleteAdminStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin deleted successfully from the Admin table.");

                // Step 2: After deleting the member, delete the corresponding user from Users table
                deleteUserStmt.setInt(1, userID);
                deleteUserStmt.executeUpdate();
                System.out.println("Corresponding User deleted successfully from the Users table.");
            } else {
                System.out.println("No admin found with adminID: " + userID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting admin: " + e.getMessage());
        }
    }

    @Override
    public void deleteMembers(int userID) throws ClassNotFoundException {
        String deleteMemberQuery = "DELETE FROM Member WHERE memberID = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE id = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement deleteMemberStmt = con.prepareStatement(deleteMemberQuery);
                PreparedStatement deleteUserStmt = con.prepareStatement(deleteUserQuery)) {

            // Step 1: Delete the member from the Member table
            deleteMemberStmt.setInt(1, userID);
            int rowsAffected = deleteMemberStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Member deleted successfully from the Member table.");

                // Step 2: After deleting the member, delete the corresponding user from Users table
                deleteUserStmt.setInt(1, userID);
                deleteUserStmt.executeUpdate();
                System.out.println("Corresponding User deleted successfully from the Users table.");
            } else {
                System.out.println("No member found with memberID: " + userID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }

    @Override
    public void deleteTrainers(int userID) throws ClassNotFoundException {
        String deleteTrainerQuery = "DELETE FROM Trainer WHERE trainerID = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE id = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement deleteTrainerStmt = con.prepareStatement(deleteTrainerQuery);
                PreparedStatement deleteUserStmt = con.prepareStatement(deleteUserQuery)) {

            // Step 1: Delete the member from the Member table
            deleteTrainerStmt.setInt(1, userID);
            int rowsAffected = deleteTrainerStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Trainer deleted successfully from the Trainer table.");

                // Step 2: After deleting the member, delete the corresponding user from Users table
                deleteUserStmt.setInt(1, userID);
                deleteUserStmt.executeUpdate();
                System.out.println("Corresponding User deleted successfully from the Users table.");
            } else {
                System.out.println("No trainer found with trainerID: " + userID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting trainer: " + e.getMessage());
        }
    }

    // Method to assign a trainer to a member
    public void assignTrainerToMember(int trainerId, int memberId) throws ClassNotFoundException {
        String query = "INSERT INTO TrainingSession (TrainerID, MemberID) VALUES (?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
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
}
