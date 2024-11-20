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

/**
 *
 * @author gAmma
 */
public class AdminManagementMenu {

    IAdminManagement am = new AdminManagement();
    CheckCondition cc = new CheckCondition();
    Utilization ul = new Utilization();

    public void membershipManagement() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        int option;
        System.out.println(" ------------//------------");

        do {
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

                case 0:

                    break;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    public void trainerAndSessionManagement() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        int option = 0;
        System.out.println(" ------------//------------");

        do {
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

                case 0:

                    break;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    public void showMemberProgress() {

    }

    private void createMembership() throws ClassNotFoundException {
        String membershipName = Validation.checkString("Enter the membership name you want to create: ");
        int durationMonths = Validation.checkInt("Enter the duration of" + membershipName + " " + "membership plan: ");
        int price = Validation.checkInt("Enter the price of" + membershipName + " " + "membership plan: ");
        String benefit = Validation.checkString("Enter benefit of" + membershipName + " " + "membership plan: ");
        am.createMembership(membershipName, durationMonths, price, benefit);
    }

    private void updateMembership() throws ClassNotFoundException {
        if (ul.showMembershipPlanList() != null) {
            System.out.println(ul.showMembershipPlanList());
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                String updMembershipName = Validation.checkString("Enter the membership name you want to update: ");
                int updDurationMonths = Validation.checkInt("Enter the duration of" + updMembershipName + " " + "membership plan: ");
                int updPrice = Validation.checkInt("Enter the price of" + updMembershipName + " " + "membership plan: ");
                String updBenefit = Validation.checkString("Enter benefit of" + updMembershipName + " " + "membership plan: ");
                am.updateMembership(membershipID, updMembershipName, updDurationMonths, updPrice, updBenefit);
            }
        } else {
            System.out.println("The list is empty!");
        }
    }

    private void deleteMembership() throws ClassNotFoundException {
        if (ul.showMembershipPlanList() != null) {
            System.out.print(ul.showMembershipPlanList());
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                am.deleteMembership(membershipID);
            }
        } else {
            System.out.println("The list is empty!");
        }
    }

    private void showTrainerList() throws ClassNotFoundException {
        ul.showTrainerList();
    }

    private void showMemberList() throws ClassNotFoundException {
        ul.showMemberList();
    }
}
