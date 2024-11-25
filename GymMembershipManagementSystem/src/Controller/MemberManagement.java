package Controller;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Payment;

public class MemberManagement implements IMemberManagement {

    @Override
    public void registerMembership(int membershipID, int memberID) {
        String query = "UPDATE Member SET msID = ? WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            // Set the parameters
            pstmt.setInt(1, membershipID);
            pstmt.setInt(2, memberID);

            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Registered successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());

        }
    }

    @Override
    public void cancelMembership(int memberID) {
        String query = "UPDATE Member SET msID = NULL WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cancelled successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    @Override
    public void renewMembership(int membershipID, int memberID) {
        String query = "UPDATE Member SET msID = ? WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            // Set the parameters
            pstmt.setInt(1, membershipID);
            pstmt.setInt(2, memberID);

            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Registered successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());

        }
    }

    @Override
    public void showMembershipPlan(int msID) {
        String query = "SELECT membershipName, durationMonths, price, benefit FROM MembershipPlan WHERE membershipID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, msID);

            ResultSet rs = pstmt.executeQuery();

            boolean hasResults = false;

            System.out.printf("%-10s    %-10s    %-10s    %-20s%n", "Membership Name", "Duration By Months", "Price", "Benefit");

            while (rs.next()) {
                hasResults = true;
                String membershipName = rs.getString("membershipName");
                int durationMonths = rs.getInt("durationMonths");
                int price = rs.getInt("price");
                String benefit = rs.getString("benefit");

                System.out.printf("%-20s   %-20d    %-10d    %-20s%n", membershipName, durationMonths, price, benefit);
            }

            if (!hasResults) {
                System.out.println("The list is empty");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    @Override
    public void trackMemberProgress(int memberId) {
        String query = "SELECT memberName, dateCreated, workoutHistory, healthMetrics "
                + "FROM MemberProgress WHERE memberID = ?";

        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Workout History and Health Metrics for Member ID: " + memberId);
                System.out.println("---------------------------------------------------");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.println("Member Name: " + rs.getString("memberName"));
                    System.out.println("Date Created: " + rs.getDate("dateCreated"));
                    System.out.println("Workout History: " + rs.getString("workoutHistory"));
                    System.out.println("Health Metrics: " + rs.getString("healthMetrics"));
                    System.out.println("---------------------------------------------------");
                }

                if (!hasData) {
                    System.out.println("No progress records found for this member.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error tracking member progress: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void paymentCreate(Payment pm) {
        String query = "INSERT INTO Payment (paymentID, memberID, moneyPaid, paymentDate, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {
            Date sqlPaymentDate = Date.valueOf(pm.getPaymentDate());
            //Date sqlRenewalDate = Date.valueOf(pm.getRenewalDate());

            pstmt.setInt(1, pm.getPaymentID());
            pstmt.setInt(2, pm.getMemberID());
            pstmt.setInt(3, pm.getMoneyPaid());
            pstmt.setDate(4, sqlPaymentDate);
            //pstmt.setDate(4, sqlRenewalDate);
            pstmt.setString(5, pm.getStatus());

            pstmt.executeUpdate();
            System.out.println("Membership created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating membership: " + e.getMessage());
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        }
    }

    @Override
    public void paymentUpdate(int memberID, int newMoneyPaid, LocalDate renewalDate, String newStatus) {
        String query = "UPDATE Payment SET moneyPaid = ?, renewalDate = ?, status = ? WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            // Set the parameters
            Date sqlRenewalDate = Date.valueOf(renewalDate);

            pstmt.setInt(1, newMoneyPaid);
            pstmt.setDate(2, sqlRenewalDate);
            pstmt.setString(3, newStatus);

            pstmt.setInt(4, memberID);
            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Updated successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());

        }
    }

    @Override
    public void paymentDelete(int paymentID) {
        String query = "DELETE FROM Payment WHERE paymentID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, paymentID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    @Override
    public void showPaymentList(int memberID) {
        String query = "SELECT * FROM Payment WHERE memberID = ?";
        try (Connection con = ConnectToSQLServer.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, memberID);

            ResultSet rs = pstmt.executeQuery();

            boolean hasResults = false;

            System.out.printf("%-10s    %-10s    %-10s     %-10s     %-10s%n", "Payment ID", "Money Paid", "Payment Date", "Renewal Date", "Status");

            while (rs.next()) {
                hasResults = true;
                int paymentID = rs.getInt("paymentID");
                int moneyPaid = rs.getInt("moneyPaid");
                Date paymentDate = rs.getDate("paymentDate");
                Date renewalDate = rs.getDate("renewalDate");
                String status = rs.getString("status");

                System.out.printf("%-10s   %-10s    %-10s    %-10s    %-10s%n", paymentID, moneyPaid, paymentDate, renewalDate, status);
            }

            if (!hasResults) {
                System.out.println("The list is empty");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }

    }
}
