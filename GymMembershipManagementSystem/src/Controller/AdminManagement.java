package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import Utils.CheckCondition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.MembershipPlan;
import model.TrainingSession;

public class AdminManagement implements IAdminManagement {

    // Method to create a new membership plan
    CheckCondition cc = new CheckCondition();

    @Override
    public void createMembership(MembershipPlan mp) {
        String query = "INSERT INTO MembershipPlan (membershipID, membershipName, durationMonths, price, benefit) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, mp.getMembershipID());
            pstmt.setString(2, mp.getMembershipName());
            pstmt.setInt(3, mp.getDurationMonths()); // Define how long the membership lasts
            pstmt.setInt(4, mp.getPrice()); // Set the price based on the membership
            pstmt.setString(5, mp.getBenefit()); // Add any benefits

            pstmt.executeUpdate();
            System.out.println("Membership created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating membership: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    // Method to update a membership name and price
    @Override
    public void updateMembership(int membershipID, String newMembershipName, int newPrice, int newDurationMonths, String newBenefit) {
        String query = "UPDATE MembershipPlan SET membershipName = ?, price = ?, durationMonths = ?, benefit = ? WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, newMembershipName);
            pstmt.setInt(2, newPrice);
            pstmt.setInt(3, newDurationMonths);
            pstmt.setString(4, newBenefit);
            pstmt.setInt(5, membershipID);

            pstmt.executeUpdate();
            System.out.println("Membership updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating membership: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    // Method to delete a membership
    @Override
    public void deleteMembership(int membershipID) {
        String query = "DELETE FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, membershipID);

            pstmt.executeUpdate();
            System.out.println("Membership deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting membership: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void deleteUsers(int userID) {
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, userID);

            pstmt.executeUpdate();
            //System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void deleteAdmins(int userID) {
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
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void deleteMembers(int userID) {
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
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void deleteTrainers(int userID) {
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
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void assignTrainerToMember(TrainingSession ts) {
        String query = "INSERT INTO TrainingSession (sessionID, TrainerID, MemberID) VALUES (?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, ts.getSessionID());
            pstmt.setInt(2, ts.getTrainerID());
            pstmt.setInt(3, ts.getMemberID());
            pstmt.executeUpdate();
            System.out.println("Trainer assigned to member successfully.");
        } catch (SQLException e) {
            System.out.println("Error assigning trainer to member: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void scheduleTrainingSession(int sessionID, int trainerID, int memberID, LocalDateTime sessionTime, String location, int durationByMinutes) {
        String query = "UPDATE TrainingSession SET sessionTime = ?, location = ?, durationByMinutes = ? WHERE sessionID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            Timestamp sTime = Timestamp.valueOf(sessionTime);

            pstmt.setTimestamp(1, sTime);
            pstmt.setString(2, location);
            pstmt.setInt(3, durationByMinutes);
            pstmt.setInt(4, sessionID);
            pstmt.executeUpdate();
            System.out.println("Training session scheduled successfully.");
        } catch (SQLException e) {
            System.out.println("Error scheduling training session: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void deleteTrainingSession(int sessionID) {
        String query = "DELETE FROM TrainingSession WHERE sessionID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, sessionID);

            pstmt.executeUpdate();
            System.out.println("Session deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting session: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }
}
