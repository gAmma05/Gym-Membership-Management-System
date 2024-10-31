/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectToSQLServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class ConnectToSQLServer {
    //public static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String server = "localhost";
        String user = "sa";
        String port = "1433";
        String password = "12345";
        String dataBase = "GMMS";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl
                = "jdbc:sqlserver://"
                + server + ":"
                + port
                + ";databaseName=" + dataBase + ";integratedSecurity=true;"
                + "encrypt=true;trustServerCertificate=true";
        //jdbc:sqlserver://localhost:1433;databaseName=GMMS
        try (Connection con = DriverManager.getConnection(connectionUrl, user, password)) {
            if (con != null) {
                return con;
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to SQL Server. Reason: Error in SQL");
        }
        return null;
    }
    
}
