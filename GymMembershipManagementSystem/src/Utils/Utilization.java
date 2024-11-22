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
public class Utilization {

    public String showMembershipPlanList() throws ClassNotFoundException {
        String query = "SELECT membershipID, membershipName, durationMonths, price, benefit FROM MembershipPlan ";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int membershipID = rs.getInt("membershipID");
                String membershipName = rs.getString("membershipName");
                int durationMonths = rs.getInt("durationMonths");
                int price = rs.getInt("price");
                String benefit = rs.getString("benefit");

                return String.format("%-4d - %-7s - %-4d - %-4d - %-30s%n",
                        membershipID, membershipName, durationMonths, price, benefit);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void showMemberList() throws ClassNotFoundException {
        String query = "SELECT m.memberID, u.name AS [Member Name], mp.membershipName AS [Membership Plan], m.joinDate "
                + "FROM Member m "
                + "INNER JOIN MembershipPlan mp ON m.msID = mp.membershipID "
                + "INNER JOIN Users u ON m.memberName = u.name "
                + "ORDER BY m.memberID;";

        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hasResults = true;
                int memberID = rs.getInt("memberID");
                String memberName = rs.getString("Member Name");
                String membershipName = rs.getString("Membership Name");
                String joinDate = rs.getString("joinDate");

                System.out.printf("%-10d - %-20s - %-20s - %-15s%n",
                        memberID, memberName, membershipName, joinDate);
            }

            if (!hasResults) {
                System.out.println("The list is empty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTrainerList() throws ClassNotFoundException {
        String query = "SELECT trainerID, expYear FROM Trainer";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int trainerID = rs.getInt("trainerID");
                int expYear = rs.getInt("expYear");
                System.out.printf("%-15d - %-15d", trainerID, expYear);
            }

            if (!rs.next()) {
                System.out.println("The list is empty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
