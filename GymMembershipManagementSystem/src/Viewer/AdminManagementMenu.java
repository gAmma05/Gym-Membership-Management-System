/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.AdminManagement;
import Controller.IAdminManagement;
import Utils.CheckCondition;
import Utils.Validation;
import Utils.Utilization;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class AdminManagementMenu {

    IAdminManagement am = new AdminManagement();
    CheckCondition cc = new CheckCondition();
    Utilization ul = new Utilization();
    Scanner sc = new Scanner(System.in);

    public void membershipManagement(String currentUser) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Create membership");
            System.out.println("2. Update membership");
            System.out.println("3. Delete membership");
            System.out.println("0. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    createMembership();
                    break;

                case 2:
                    updateMembership();
                    break;

                case 3:
                    deleteMembership();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    public void trainerAndSessionManagement(String currentUser) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Show trainer's list");
            System.out.println("2. Show member's list");
            System.out.println("3. Assign members to trainer");
            System.out.println("4. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    showTrainerList();
                    break;

                case 2:
                    showMemberList();
                    break;

                case 3:

                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 4);
    }

    public void showMemberProgress(String currentUser) throws ClassNotFoundException {
        if (!cc.usernameCheck(currentUser)) {
            return;
        }

    }

    public void Settings(String currentUser) throws ClassNotFoundException {

        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("1. Delete admins");
            System.out.println("2. Delete members");
            System.out.println("3. Delete trainers");
            System.out.println("4. Exit");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    deleteAdmins();
                    break;

                case 2:
                    deleteMembers();
                    break;

                case 3:
                    deleteTrainers();
                    break;
                case 4:
                    return;
            }
        } while (option != 2);
    }

    private void createMembership() throws ClassNotFoundException {
        String membershipName = Validation.checkString("Enter the membership name you want to create (length < 10): ");
        int durationMonths = Validation.checkInt("Enter the duration of" + " " + membershipName + " " + "membership  (length > 0): ");
        int price = Validation.checkInt("Enter the price of" + " " + membershipName + " " + "membership plan (length > 0): ");
        String benefit = Validation.checkString("Enter benefit of" + " " + membershipName + " " + "membership plan (length < 512): ");
        am.createMembership(membershipName, durationMonths, price, benefit);
    }

    private void updateMembership() throws ClassNotFoundException {
        if (ul.showMembershipPlanList()) {
            //System.out.println(ul.showMembershipPlanList());
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                String updMembershipName = Validation.checkString("Enter the membership name you want to update: ");
                int updDurationMonths = Validation.checkInt("Enter the duration of" + " " + updMembershipName + " " + "membership plan (length < 10): ");
                int updPrice = Validation.checkInt("Enter the price of" + " " + updMembershipName + " " + "membership plan (length > 0): ");
                String updBenefit = Validation.checkString("Enter benefit of" + " " + updMembershipName + " " + "membership plan (length < 512): ");
                am.updateMembership(membershipID, updMembershipName, updDurationMonths, updPrice, updBenefit);
            }
        } else {
            System.out.println("The list is empty!");
        }
    }

    private void deleteMembership() throws ClassNotFoundException {
        if (ul.showMembershipPlanList()) {
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                am.deleteMembership(membershipID);
            }
        }
    }

    private void showTrainerList() throws ClassNotFoundException {
        if (ul.showTrainerList()) {
            System.out.println("");
        } else {
            System.out.println("The list is empty");
        }
    }

    private void showMemberList() throws ClassNotFoundException {
        if (ul.showMemberList()) {
            System.out.println("");
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteAdmins() throws ClassNotFoundException {
        if (ul.showAdminList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                am.deleteAdmins(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteMembers() throws ClassNotFoundException {
        if (ul.showMemberList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                am.deleteMembers(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteTrainers() throws ClassNotFoundException {
        if (ul.showTrainerList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                am.deleteTrainers(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }
}
