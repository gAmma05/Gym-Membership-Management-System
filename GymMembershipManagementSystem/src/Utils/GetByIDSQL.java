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
public class GetByIDSQL {
    public int getAssignedMember(int sessionID) {
        String query = "SELECT memberID FROM TrainingSession WHERE sessionID = ?";
        try(Connection con = ConnectToSQLServer.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int memberID = rs.getInt("memberID");
                return memberID;
            }
            
        }catch(SQLException sql){
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        }catch(ClassNotFoundException classE){
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }
    
    public int getAssignedTrainer(int sessionID) {
        String query = "SELECT trainerID FROM TrainingSession WHERE sessionID = ?";
        try(Connection con = ConnectToSQLServer.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int trainerID = rs.getInt("trainerID");
                return trainerID;
            }
            
        }catch(SQLException sql){
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        }catch(ClassNotFoundException classE){
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }
}
