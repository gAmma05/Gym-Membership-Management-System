/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import ConnectToSQLServer.ConnectToSQLServer;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author gAmma
 */
public class UserRegistration {

    public int Register(int id, String username, String password, String name, String email, String role, String phoneNumber, String gender) {

        try (Connection con = ConnectToSQLServer.getConnection()) {
            String query = "INSERT INTO Users (id, username, password, name, email, role, phoneNumber, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, name);
            ps.setString(5, email);
            ps.setString(6, role);
            ps.setString(7, phoneNumber);
            ps.setString(8, gender);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Registered successfully");
                query = "SELECT id FROM Users WHERE username = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, username);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int generatedUserID = rs.getInt("id");
                    return generatedUserID;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return -1;
    }

    public void memberRegister(int userID, String memberName, String gender) {
        String query = "INSERT INTO Member (memberID, memberName, gender, joinDate) VALUES (?, ?, ?, ?)";
        LocalDate joinDate = LocalDate.now(); // Get the current date
        Date sqlJoinDate = Date.valueOf(joinDate); // Convert to SQL Date
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, memberName);
            ps.setString(3, gender);
            ps.setDate(4, sqlJoinDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    public void trainerRegister(int userID, String trainerName, String gender, int expYear){
        String query = "INSERT INTO Trainer (trainerID, trainerName, gender, expYear, joinDate) VALUES (?, ?, ?, ?, ?)";
        LocalDate joinDate = LocalDate.now(); // Get the current date
        Date sqlJoinDate = Date.valueOf(joinDate); // Convert to SQL Date
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, trainerName);
            ps.setString(3, gender);
            ps.setInt(4, expYear);
            ps.setDate(5, sqlJoinDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    public void adminRegister(int userID, String adminName) {
        String query = "INSERT INTO Admin (adminID, adminName) VALUES (?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, adminName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }
}
