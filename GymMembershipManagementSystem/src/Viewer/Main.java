/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Access.LoginRegisterMenu;
import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class Main {

    public static LoginRegisterMenu lrm = new LoginRegisterMenu();

    public static void main(String[] args) {
        try (Connection con = ConnectToSQLServer.getConnection()) {
            if (con != null) {
                System.out.println("Connected to database successfully");
                lrm.Menu();
            } else {
                System.out.println("Failed to connect to database");
            }
        } catch (ClassNotFoundException classE) {
            System.out.println("Class not found: " + classE.getMessage());
        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql.getMessage());
        }

    }
}
