/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.Validation;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class MemberMenu {
    private final Scanner sc = new Scanner(System.in);

    public void membershipMenu(String currentUser) {
        int option;

        do {
            System.out.println("\n=== Membership Management ===");
            System.out.println("Current User: " + currentUser);
            System.out.println("1. Register for a Membership");
            System.out.println("2. Renew Membership");
            System.out.println("3. Select Payment Method");
            System.out.println("4. Exit");
            System.out.print("Your option: ");

            option = Validation.checkInt("Your option: "); // Ensure you have a Validation class with checkInt()

            switch (option) {
                case 1:
                    registerMembership();
                    break;

                case 2:
                    renewMembership();
                    break;

                case 3:
                    selectPaymentMethod();
                    break;

                case 4:
                    System.out.println("Exiting Membership Management. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (option != 4);
    }

    private void registerMembership() {
        System.out.println("\n=== Register Membership ===");
        String membershipType = Validation.checkString("Enter membership type (e.g., Basic, Premium): ");
        int duration = Validation.checkInt("Enter duration in months: ");
        int price = Validation.checkInt("Enter price: ");
        System.out.println("Membership Registered: " + membershipType + " for " + duration + " months at $" + price);
    }

    private void renewMembership() {
        System.out.println("\n=== Renew Membership ===");
        int membershipID = Validation.checkInt("Enter your Membership ID: ");
        int additionalMonths = Validation.checkInt("Enter the number of additional months: ");
        System.out.println("Membership ID " + membershipID + " renewed for " + additionalMonths + " months.");
    }

    private void selectPaymentMethod() {
        System.out.println("\n=== Select Payment Method ===");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. PayPal");
        System.out.println("4. Exit");
        int choice = Validation.checkInt("Choose your payment method: ");

        switch (choice) {
            case 1:
                System.out.println("Payment Method: Credit Card selected.");
                break;
            case 2:
                System.out.println("Payment Method: Debit Card selected.");
                break;
            case 3:
                System.out.println("Payment Method: PayPal selected.");
                break;
            case 4:
                System.out.println("Exiting payment selection.");
                break;
            default:
                System.out.println("Invalid input. Please try again.");
        }
    }    
}
