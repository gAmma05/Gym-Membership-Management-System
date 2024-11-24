/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.IAdminManagement;
import Controller.AdminManagement;
import Utils.CheckCondition;
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
    CheckCondition cc = new CheckCondition();

    public void adMenu(String currentUser) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Welcome admin," + " " + currentUser);//user welcome

            System.out.println("We only accept credit card!");
            System.out.println("1. Manage gym's membership plans");
            System.out.println("2. Manage trainers and training session");
            System.out.println("3. Settings");
            System.out.println("4. Logout");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    adm.membershipManagement(currentUser);
                    break;

                case 2:
                    adm.trainerAndSessionManagement(currentUser);
                    break;

                case 3:
                    adm.Settings(currentUser);
                    break;

                case 4:
                    System.out.println("Logout successfully");
                    return;

                default:
            }
        } while (option != 0);
    }

}
