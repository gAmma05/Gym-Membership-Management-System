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
        String query = "SELECT mp.membershipID, membershipName, durationMonths, price, benefit FROM MembershipPlan mp";
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
        String query = "SELECT m.memberID, mp.membershipName, m.joinDate "
                + "FROM Member m "
                + "INNER JOIN MembershipPlan mp ON m.msID = mp.membershipID";

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int memberID = rs.getInt("memberID");
                String membershipName = rs.getString("membershipName");
                String joinDate = rs.getString("joinDate");

                System.out.printf("%-10d - %-20s - %-15s%n",
                        memberID, membershipName, joinDate);
            }

            if (!rs.next()) {
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
}
