/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.AdminManagement;
import Controller.IAdminManagement;
import Utils.Validation;

/**
 *
 * @author gAmma
 */
public class AdminManagementMenu {
    IAdminManagement am = new AdminManagement();

    public void membershipManagement() {
        int option = 0;
        System.out.println(" ------------//------------");

        do {
            System.out.print("We only accept credit card!");
            System.out.println("1. Create membership");
            System.out.println("2. Update membership");
            System.out.println("3. Delete membership");
            System.out.println("0. Exit");

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

    public void trainerAndSessionManagement() {
        int option = 0;
        System.out.println(" ------------//------------");

        do {
            System.out.print("We only accept credit card!");
            System.out.println("1. Show trainer's list");
            System.out.println("2. Assign members to trainer");
            System.out.println("0. Exit");

            switch (option) {
                case 1:

                    break;

                case 2:

                    break;

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

    private void createMembership() {
        String membershipName = Validation.checkString("Enter the membership name you want to create: ");
        int durationMonths = Validation.checkInt("How many months you want to subscribe: ");
        int price = Validation.checkInt("Enter the price of" + membershipName + " membership plan: ");
        String benefit = Validation.checkString("Enter benefit of membership plan: ");
    }

    private void updateMembership() {
        
    }

    private void deleteMembership() {
        //mm.delete();
    }
}
