/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Utils.PasswordUtils;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class UserRegistration {

    public boolean Register(String username, String password, String name, String email, String role, String phoneNumber, String gender) throws NoSuchAlgorithmException, SQLException {
        String salt = PasswordUtils.generateSalt();
        String hashedPass;
        try {
            hashedPass = PasswordUtils.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Fail to hash password");
            return false;
        }

        try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GMMS;encrypt=true;trustServerCertificate=true", "sa", "12345")) {
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
            return result > 0;
        } catch (SQLException e) {
            
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
