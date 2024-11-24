package ConnectToSQLServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToSQLServer {

    private static final String SERVER = "localhost";
    private static final String USER = "sa";

    private static final String PASSWORD = "12345";
    private static final String DATABASE = "GMMS";

    // Static method to get a connection to the database
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://" + SERVER + ";databaseName=" + DATABASE + ";user=" + USER + ";password=" + PASSWORD + ";encrypt=true;trustServerCertificate=true";

        Connection con = DriverManager.getConnection(connectionUrl);
        if (con != null) {
            //System.out.println("Connected to database successfully");
        } else {
            //System.out.println("Failed to connect to database");
        }
        return con;
    }

    // Method to initialize the database
    public static void initializeDatabase() throws ClassNotFoundException {
        String createDatabaseSQL = "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'GMMS') " +
                                    "BEGIN " +
                                    "CREATE DATABASE GMMS; " +
                                    "END;";

        // This connection will be to the master database for creating the GMMS database
        try (Connection con = DriverManager.getConnection("jdbc:sqlserver://" + SERVER + ";user=" + USER + ";password=" + PASSWORD);
             Statement stmt = con.createStatement()) {
            stmt.execute(createDatabaseSQL);
            System.out.println("Database created successfully or already exists.");
        } catch (SQLException e) {
            System.out.println("Database creation error: " + e.getMessage());
        }

        // After the database is created, connect to it and create tables
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            String createTablesSQL = 
                "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT IDENTITY(1,1) PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL," +
                "salt NVARCHAR(255) NOT NULL," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL UNIQUE," +
                "role VARCHAR(20) NOT NULL," +
                "phoneNumber NVARCHAR(9)," +
                "gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')));" +
                "CREATE TABLE IF NOT EXISTS Membership_Plan (" +
                "membership_ID INT IDENTITY(1,1) PRIMARY KEY," +
                "membershipName NVARCHAR(10) CHECK (membershipName IN ('Bronze', 'Silver', 'Gold', 'Platinum'))," +
                "durationMonths INT CHECK (durationMonths > 0)," +
                "price INT CHECK (price > 0)," +
                "benefit VARCHAR(512));" +
                "CREATE TABLE IF NOT EXISTS Admin (" +
                "admin_id INT PRIMARY KEY," +
                "FOREIGN KEY(admin_id) REFERENCES Users(id));" +
                "CREATE TABLE IF NOT EXISTS Member (" +
                "member_id INT PRIMARY KEY," +
                "FOREIGN KEY(member_id) REFERENCES Users(id)," +
                "msID INT FOREIGN KEY REFERENCES Membership_Plan(membership_ID)," +
                "joinDate DATE," +
                "expiredDate DATE);" +
                "CREATE TABLE IF NOT EXISTS Trainer (" +
                "trainer_id INT PRIMARY KEY," +
                "FOREIGN KEY(trainer_id) REFERENCES Users(id)," +
                "expYear INT);" +
                "CREATE TABLE IF NOT EXISTS TrainingSession (" +
                "sessionID INT PRIMARY KEY IDENTITY(1,1)," +
                "TrainerID INT FOREIGN KEY REFERENCES Trainer(trainer_ID)," +
                "MemberID INT FOREIGN KEY REFERENCES Member(member_ID)," +
                "session_time DATETIME," +
                "location VARCHAR(50)," +
                "durationByMinutes INT);" +
                "CREATE TABLE IF NOT EXISTS MemberProgress (" +
                "ProgressID INT PRIMARY KEY IDENTITY(1,1)," +
                "MemberID INT FOREIGN KEY REFERENCES Member(member_ID)," +
                "Date DATE," +
                "WorkoutHistory TEXT," +
                "HealthMetrics VARCHAR(255));" +
                "CREATE TABLE IF NOT EXISTS Payment (" +
                "payment_ID INT," +
                "Member_ID INT," +
                "FOREIGN KEY(Member_ID) REFERENCES Member(member_id) ON DELETE CASCADE," +
                "payment_Date DATE DEFAULT GETDATE()," +
                "renewalDate DATE);";

            stmt.execute(createTablesSQL);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}