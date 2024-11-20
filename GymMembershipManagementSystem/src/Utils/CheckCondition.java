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
}
