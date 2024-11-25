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
import java.time.LocalDate;
import model.Payment;
import model.User;

/**
 *
 * @author gAmma
 */
public class GetByIDSQL {

    public User getUserByID(int id) {
        String query = "SELECT * FROM Users WHERE id = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");
                String gender = rs.getString("gender");
                User us = new User(userID, username, password, name, email, role, phoneNumber, gender);
                return us;
            }

        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return null;
    }

    public int getAssignedMember(int sessionID) {
        String query = "SELECT memberID FROM TrainingSession WHERE sessionID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int memberID = rs.getInt("memberID");
                return memberID;
            }

        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public int getAssignedTrainer(int sessionID) {
        String query = "SELECT trainerID FROM TrainingSession WHERE sessionID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int trainerID = rs.getInt("trainerID");
                return trainerID;
            }

        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        } catch (ClassNotFoundException classE) {
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

    public int getAssignedMemIfTra(int trainerID) {
        String query = "SELECT ts.sessionID, ts.sessionTime, ts.location, ts.durationByMinutes, "
                + "ts.memberID, t.trainerName, m.memberName "
                + "FROM TrainingSession ts "
                + "JOIN Trainer t ON ts.trainerID = t.trainerID "
                + "JOIN Member m ON ts.memberID = m.memberID "
                + "WHERE ts.trainerID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, trainerID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int memberID = rs.getInt("memberID");
                return memberID;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public String getMemberNameFromTS(int trainerID) {
        String query = "SELECT ts.sessionID, ts.sessionTime, ts.location, ts.durationByMinutes, "
                + "ts.memberID, t.trainerName, m.memberName "
                + "FROM TrainingSession ts "
                + "JOIN Trainer t ON ts.trainerID = t.trainerID "
                + "JOIN Member m ON ts.memberID = m.memberID "
                + "WHERE ts.trainerID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, trainerID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String memberName = rs.getString("memberName");
                return memberName;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return null;
    }

    public int getProgressID(int memberID) {
        String query = "SELECT progressID FROM MemberProgress WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, memberID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int progressID = rs.getInt("progressID");
                return progressID;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public int getIDByUsername(String username) {
        String query = "SELECT id FROM Users WHERE username = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public int getMembershipName(int membershipID) {
        String query = "SELECT membershipName FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, membershipID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int progressID = rs.getInt("progressID");
                return progressID;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public int getMPPrice(int membershipID) {
        String query = "SELECT price FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, membershipID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int price = rs.getInt("price");
                return price;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return 0;
    }

    public Payment getPaymentByID(int id) {
        String query = "SELECT * FROM Payment WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

           
            if (rs.next()) {
                int paymentID = rs.getInt("paymentID");
                int memberID = rs.getInt("memberID");
                int moneyPaid = rs.getInt("moneyPaid");
                Date sqlPD = rs.getDate("paymentDate");
                LocalDate paymentDate = sqlPD.toLocalDate();
                
                Date sqlRD = rs.getDate("renewalDate");
                LocalDate renewalDate = sqlRD.toLocalDate();
                
                String status = rs.getString("status");
                Payment pm = new Payment(paymentID, memberID, moneyPaid, paymentDate, renewalDate, status);
                return pm;
            }

        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql.getLocalizedMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
        return null;
    }
}
