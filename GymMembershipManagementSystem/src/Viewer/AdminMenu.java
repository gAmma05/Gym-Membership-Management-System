/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.IAdminManagement;
import Controller.AdminManagement;
import Utils.Validation;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class AdminMenu {
    
    
    public Scanner sc = new Scanner(System.in);
    IAdminManagement am = new AdminManagement();
    AdminManagementMenu adm = new AdminManagementMenu();
    
    public void adMenu(String currentUser) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        int option;
        System.out.println(" ------------//------------");
        System.out.println("Welcome admin," + " " + currentUser);//user welcome
        do {
            System.out.println("We only accept credit card!");
            System.out.println("1. Manage gym's membership plans");
            System.out.println("2. Manage trainers and training session");
            System.out.println("3. Show member progress");
            System.out.println("4. Settings");
            System.out.println("5. Logout");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    adm.membershipManagement();
                    break;

                case 2:
                    adm.trainerAndSessionManagement();
                    break;

                case 3:
                    adm.showMemberProgress();
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Logout successfully");
                    return;

                default:
            }
        } while (option != 0);
    }
    

    

}
