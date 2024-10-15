/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import ConnectToSQLServer.ConnectToSQLServer;
import java.sql.SQLException;



/**
 *
 * @author gAmma
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        ConnectToSQLServer ctss = new ConnectToSQLServer();
        ctss.getConnection();
    }
}
