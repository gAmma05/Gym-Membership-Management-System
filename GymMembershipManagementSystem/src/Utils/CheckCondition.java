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
    
    public boolean usernameCheck(String username) throws ClassNotFoundException{
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try(Connection con = ConnectToSQLServer.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean membershipIDCheck(int membershipID) throws ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, membershipID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean userIDCheck(int userID) throws ClassNotFoundException{
        String query = "SELECT COUNT(*) FROM Users WHERE id = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
