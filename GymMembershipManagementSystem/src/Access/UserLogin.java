/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public boolean Login(String username, String password) throws SQLException, NoSuchAlgorithmException{
        try(Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GMMS", username, password)){
            String query = "SELECT password, salt FROM account_authenciation WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();
            
            if(result.next()){
                String storedHashedPassword = result.getString("password");
                String salt = result.getString("salt");
                
                String hashedInputPassword = PasswordUtils.hashPassword(password, salt);
                return storedHashedPassword.equals(hashedInputPassword); //return if hashed pass stored in db matches with the input
            }else{
                System.out.println("User not found");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error Error Error");
            return false;
        }
    }
}
