/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class CheckCondition {

    public boolean usernameCheck(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean membershipIDCheck(int membershipID) {
        String query = "SELECT COUNT(*) FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, membershipID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean userIDCheck(int userID) {
        String query = "SELECT COUNT(*) FROM Users WHERE id = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean checkMemberAssigned(int userID) {
        String query = "SELECT COUNT(*) FROM TrainingSession WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean checkTrainerAssigned(int userID) {
        String query = "SELECT COUNT(*) FROM TrainingSession WHERE trainerID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean checkTrainingSession(int sessionID) {
        String query = "SELECT COUNT(*) FROM TrainingSession WHERE sessionID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sessionID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean checkMembershipPLan(int membershipID) {
        String query = "SELECT COUNT(*) FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, membershipID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

    public boolean isMembershipActive(int memberID) {
        String query = "SELECT msID FROM Member WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("msID") != 0; // Membership is active if msID is not 0 or NULL
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return false;
    }
    
    public boolean isPaymentActive(int memberID) {
        String query = "SELECT paymentID FROM Payment WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("paymentID") != 0; 
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return false;
    }
    
    public boolean isPaymentCompleted(int memberID) {
        String query = "SELECT status FROM Payment WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status").equalsIgnoreCase("Uncompleted"); 
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return false;
    }
}
