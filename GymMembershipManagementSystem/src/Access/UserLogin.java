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

/**
 *
 * @author gAmma
 */
public class UserLogin {


    public boolean Login(String username, String password){
        try (Connection con = ConnectToSQLServer.getConnection()) {
            String query = "SELECT password FROM Users WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String storedPassword = result.getString("password");
                if (storedPassword.equals(password)) {
                    System.out.println("Login successfully!");
                    return true;
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                //System.out.println("User not found");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return false;
    }

}
