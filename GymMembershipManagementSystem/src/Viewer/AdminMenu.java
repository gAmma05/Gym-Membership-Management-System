/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.IAdminManagement;
import Controller.AdminManagement;
import Utils.CheckCondition;
import Utils.GetByIDSQL;
import Utils.Validation;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import model.User;

/**
 *
 * @author gAmma
 */
public class AdminMenu {

    public Scanner sc = new Scanner(System.in);
    IAdminManagement am = new AdminManagement();
    AdminManagementMenu adm = new AdminManagementMenu();
    CheckCondition cc = new CheckCondition();
    GetByIDSQL gbis = new GetByIDSQL();

    public void adMenu(String currentUser) {
        int option;

        int adminID = gbis.getIDByUsername(currentUser);
        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Welcome admin," + " " + currentUser);//user welcome

            System.out.println("We only accept credit card!");
            System.out.println("1. Manage gym's membership plans");
            System.out.println("2. Manage trainers and training session");
            System.out.println("3. Your information");
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
                    yourInformation(adminID);
                    break;

                case 4:
                    System.out.println("Logout successfully");
                    return;

                default:
            }
        } while (option != 0);
    }

    private void yourInformation(int adminID) {
        User use = gbis.getUserByID(adminID);
        if (use != null) {
            System.out.println("Username: " + use.getUsername());
            System.out.println("Password: " + "*****");
            System.out.println("Email: " + use.getEmail());
            System.out.println("Phone number: " + use.getPhoneNumber());
            System.out.println("Gender: " + use.getGender());
        } else {
            System.out.println("No information found, please login again");
        }
    }
}
