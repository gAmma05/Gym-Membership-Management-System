/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import ConnectToSQLServer.ConnectToSQLServer;
import Utils.PasswordUtils;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author gAmma
 */
public class UserRegistration {

    public int Register(String username, String password, String name, String email, String role, String phoneNumber, String gender) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        String salt = PasswordUtils.generateSalt();
        String hashedPass;
        try {
            hashedPass = PasswordUtils.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Fail to hash password");
            return -1;
        }

        try (Connection con = ConnectToSQLServer.getConnection()) {
            String query = "INSERT INTO Users (username, password, salt, name, email, role, phoneNumber, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, hashedPass);
            ps.setString(3, salt);
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
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }

    public void memberRegister(int userID, String memberName, String gender) throws ClassNotFoundException, SQLException {
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
        }
    }
    
    public void trainerRegister(int userID, String trainerName, String gender, int expYear) throws ClassNotFoundException, SQLException{
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
        }
    }

    public void adminRegister(int userID, String adminName) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO Admin (adminID, adminName) VALUES (?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, adminName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
