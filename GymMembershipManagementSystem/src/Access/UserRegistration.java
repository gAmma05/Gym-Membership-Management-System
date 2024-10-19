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

    public boolean Register(String username, String password) throws NoSuchAlgorithmException, SQLException {
        String salt = PasswordUtils.generateSalt();
        String hashedPass;
        try {
            hashedPass = PasswordUtils.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Fail to hash password");
            return false;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1433;databaseName=GMMS", "sa", "12345")) {
            String query = "INSERT INTO account_authentication (username, password, salt) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, hashedPass);
            ps.setString(3, salt);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }
}
