/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.PasswordUtils;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author gAmma
 */
public class UserLogin {


    public boolean Login(String username, String password) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        try (Connection con = ConnectToSQLServer.getConnection()) {
            String query = "SELECT password, salt, role FROM Users WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String storedHashedPassword = result.getString("password");
                String salt = result.getString("salt");
                String hashedInputPassword = PasswordUtils.hashPassword(password, salt);
                if (storedHashedPassword.equals(hashedInputPassword)) {
                    System.out.println("Login successfully!");
                    //System.out.println("Your role is: "+ role);
                    //String rs = veri.checkRole(username);
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
            } else {
                //System.out.println("User not found");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
