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
    
    public int getDurationOfTS(int sessionID) {
        String query = "SELECT durationByMinutes FROM TrainingSession WHERE sessionID = ?";
        int result = -1;
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sessionID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getInt("durationByMinutes");
                return result;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return result;
    }
}
