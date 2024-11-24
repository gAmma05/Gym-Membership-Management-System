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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author gAmma
 */
public class Show {

    public boolean showMembershipPlanList() {

        String query = "SELECT membershipID, membershipName, durationMonths, price, benefit FROM MembershipPlan ";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            System.out.printf("%-4s - %-7s - %-4s - %-4s - %-30s%n",
                        "Membership ID", "Membership Name", "Duration Months", "Price", "Benefit");

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    public boolean showAdminList() {
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    public boolean showMemberList() {
        String query = "SELECT memberID, memberName, joinDate FROM Member";

        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.printf("%-5s - %-15s - %-7s%n",
                    "Member ID", "MemberName", "Join Date");
            while (rs.next()) {
                hasResults = true;
                int memberID = rs.getInt("memberID");
                String memberName = rs.getString("memberName");
                Date joinDate = rs.getDate("joinDate");

                System.out.printf("%-5d - %-15s - %-7s%n",
                        memberID, memberName, joinDate);
            }
            if (!hasResults) {
                //System.out.println("The list is empty");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    public boolean showTrainerList() {
        String query = "SELECT trainerID, trainerName, expYear, joinDate FROM Trainer";

        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.printf("%-5s - %-15s - %-5s - %-7s%n", "Trainer ID", "Trainer Name", "Exp Year", "Join Date");

            while (rs.next()) {
                hasResults = true;
                int trainerID = rs.getInt("trainerID");
                String trainerName = rs.getString("trainerName");
                int expYear = rs.getInt("expYear");
                Date joinDate = rs.getDate("joinDate");
                System.out.printf("%-5d - %-15s - %-5d - %-7s%n", trainerID, trainerName, expYear, joinDate);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
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
    public boolean showMemberProgress() {
        String query = "SELECT mpr.progressID, m.memberName [Member Name], mpr.dateCreated, mpr.workoutHistory, mpr.healthMetrics "
                + "FROM MemberProgress mpr "
                + "INNER JOIN Member m ON mpr.memberID = m.memberID "
                + "INNER JOIN Member m ON mpr.memberName = m.memberName"
                + "ORDER BY mpr.progressID";
        boolean hasResults = false;
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.printf("%-10s - %-20s - %-7s - %-30s - %-15s%n",
                    "Progress ID", "Member Name", "Date Created", "Workout History", "Health Metrics");
            while (rs.next()) {
                hasResults = true;
                int progressID = rs.getInt("progressID");
                String memberName = rs.getString("Member Name");
                String dateCreated = rs.getString("dateCreated");
                String workoutHistory = rs.getString("workoutHistory");
                String healthMetrics = rs.getString("healthMetrics");

                System.out.printf("%-10d - %-20s - %-7s - %-30s - %15s%n",
                        progressID, memberName, dateCreated, workoutHistory, healthMetrics);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

    public boolean showTrainingSession() {
        String query = "SELECT ts.sessionID, ts.sessionTime, ts.location, ts.durationByMinutes, "
                + "t.trainerName, m.memberName "
                + "FROM TrainingSession ts "
                + "JOIN Trainer t ON ts.trainerID = t.trainerID "
                + "JOIN Member m ON ts.memberID = m.memberID";
        boolean hasResults = false;

        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.printf("%-10s %-20s %-20s %-15s %-15s %-5s%n", "Session ID ", "Trainer Name", "Member Name", "Session Time", "Location", "Duration (Minutes)");
            while (rs.next()) {

                hasResults = true;
                int sessionID = rs.getInt("sessionID");
                String sessionTimeRaw = rs.getString("sessionTime");
                //LocalDateTime sessionTime = LocalDateTime.parse(sessionTimeRaw.replace(' ', 'T'));
                String formattedSessionTime = (sessionTimeRaw != null)
                        ? LocalDateTime.parse(sessionTimeRaw.replace(' ', 'T')).format(formatter)
                        : "N/A";

                String location = rs.getString("location");
                location = (location != null) ? location : "N/A";

                int durationByMinutes = rs.getInt("durationByMinutes");

                String trainerName = rs.getString("trainerName");
                String memberName = rs.getString("memberName");

                System.out.printf("%-10s %-20s %-20s %-15s %-15s %-5s%n", sessionID, trainerName, memberName, formattedSessionTime, location, durationByMinutes);
            }

        } catch (SQLException e) {
            System.out.println("Error scheduling training session: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return hasResults;
    }

}
