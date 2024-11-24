/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Admin;
import model.Member;
import model.Trainer;
import model.User;

/**
 *
 * @author gAmma
 */
public class UserRegistration {

    public int Register(User us) {

        try (Connection con = ConnectToSQLServer.getConnection()) {
            String query = "INSERT INTO Users (id, username, password, name, email, role, phoneNumber, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, us.getId());
            ps.setString(2, us.getUsername());
            ps.setString(3, us.getPassword());
            ps.setString(4, us.getName());
            ps.setString(5, us.getEmail());
            ps.setString(6, us.getRole());
            ps.setString(7, us.getPhoneNumber());
            ps.setString(8, us.getGender());

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Registered successfully");
                query = "SELECT id FROM Users WHERE username = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, us.getUsername());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int generatedUserID = rs.getInt("id");
                    return generatedUserID;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return -1;
    }

    public void memberRegister(Member mem) {
        String query = "INSERT INTO Member (memberID, memberName, gender, joinDate) VALUES (?, ?, ?, ?)";
        LocalDate joinDate = mem.getJoinDate(); // Get the current date
        Date sqlJoinDate = Date.valueOf(joinDate); // Convert to SQL Date
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, mem.getId());
            ps.setString(2, mem.getName());
            ps.setString(3, mem.getGender());
            ps.setDate(4, sqlJoinDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    public void trainerRegister(Trainer tra){
        String query = "INSERT INTO Trainer (trainerID, trainerName, gender, expYear, joinDate) VALUES (?, ?, ?, ?, ?)";
        LocalDate joinDate = tra.getJoinDate(); // Get the current date
        Date sqlJoinDate = Date.valueOf(joinDate); // Convert to SQL Date
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, tra.getId());
            ps.setString(2, tra.getName());
            ps.setString(3, tra.getGender());
            ps.setInt(4, tra.getExpYear());
            ps.setDate(5, sqlJoinDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    public void adminRegister(Admin adm) {
        String query = "INSERT INTO Admin (adminID, adminName) VALUES (?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, adm.getId());
            ps.setString(2, adm.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }
}
