/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class Utilization {

    public boolean showMembershipPlanList() throws ClassNotFoundException {

        String query = "SELECT membershipID, membershipName, durationMonths, price, benefit FROM MembershipPlan ";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hasResults = true;
                int membershipID = rs.getInt("membershipID");
                String membershipName = rs.getString("membershipName");
                int durationMonths = rs.getInt("durationMonths");
                int price = rs.getInt("price");
                String benefit = rs.getString("benefit");

                System.out.printf("%-4d - %-7s - %-4d - %-4d - %-30s%n",
                        membershipID, membershipName, durationMonths, price, benefit);
            }

            if (!hasResults) {
                //System.out.println("The list is empty");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hasResults;
    }

    public boolean showAdminList() throws ClassNotFoundException {
        String query = "SELECT adminID, adminName FROM Admin";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hasResults = true;
                int adminID = rs.getInt("adminID");
                String adminName = rs.getString("adminName");
                System.out.printf("%-5d - %-15s%n", adminID, adminName);
            }

            if (!hasResults) {
                //System.out.println("The list is empty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return hasResults;
    }

    public boolean showMemberList() throws ClassNotFoundException {
        String query = "SELECT memberID, memberName, joinDate FROM Member";

        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hasResults = true;
                int memberID = rs.getInt("memberID");
                String memberName = rs.getString("memberName");
                Date joinDate = rs.getDate("joinDate");

                System.out.printf("%-10d - %-20s - %-7s%n",
                        memberID, memberName, joinDate);
            }
            if (!hasResults) {
                //System.out.println("The list is empty");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hasResults;
    }

    public boolean showTrainerList() throws ClassNotFoundException {
        String query = "SELECT trainerID, trainerName, expYear, joinDate FROM Trainer";

        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hasResults = true;
                int trainerID = rs.getInt("trainerID");
                String trainerName = rs.getString("trainerName");
                int expYear = rs.getInt("expYear");
                Date joinDate = rs.getDate("joinDate");
                System.out.printf("%-5d - %-20s - %-5d - %-7s%n", trainerID, trainerName, expYear, joinDate);
            }

            if (!hasResults) {
                //System.out.println("The list is empty");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return hasResults;
    }

    /*
    public boolean showUser() throws ClassNotFoundException {
        String query = "SELECT id, username FROM Users";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;
                int userID = rs.getInt("id");
                String username = rs.getString("username");
                System.out.printf("%-5d - %-10s", userID, username);
            }
            
            if(!hasResults){
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

     */
    public void showMemberProgress() throws ClassNotFoundException {
        String query = "SELECT mpr.progressID, m.memberName [Member Name], mpr.dateCreated, mpr.workoutHistory, mpr.healthMetrics "
                + "FROM MemberProgress mpr "
                + "INNER JOIN Member m ON mpr.memberID = m.memberID "
                + "INNER JOIN Member m ON mpr.memberName = m.memberName"
                + "ORDER BY mpr.progressID";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int progressID = rs.getInt("progressID");
                String memberName = rs.getString("Member Name");
                String dateCreated = rs.getString("dateCreated");
                String workoutHistory = rs.getString("workoutHistory");
                String healthMetrics = rs.getString("healthMetrics");

                System.out.printf("%-10d - %-20s - %-7s - %-30s - %15%n",
                        progressID, memberName, dateCreated, workoutHistory, healthMetrics);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
