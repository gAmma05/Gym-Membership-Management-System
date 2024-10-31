/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.IMemberManagement;
import Controller.MemberManagement;
import Utils.Validation;
import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class MemberMenu {

    public Scanner sc = new Scanner(System.in);
    IMemberManagement mm = new MemberManagement();

    public void mmenu() {
        int option = 0;
        System.out.println(" ------------//------------");
        System.out.println("Welcome," + "");//user welcome
        do {
            System.out.print("We only accept credit card!");
            System.out.println("1. Create your plan");
            System.out.println("2. Update your plan");
            System.out.println("3. Delete your plan");
            System.out.println("4. Make the renewal");
            System.out.println("5. Settings");
            System.out.println("6. Logout");
            System.out.println("Your option: ");
            option = sc.nextInt();

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
                    break;

                case 5:
                    break;

                case 6:
                    break;

                default:
            }
        } while (option != 0);
    }

    private void createMembership() {
        System.out.print("Choose the membership you desire (Use 1|2|3|4): ");
        System.out.println("1.Bronze - 5$/month");
        System.out.println("2.Silver - 10$/month");
        System.out.println("3.Gold - 20$/month");
        System.out.println("4.Diamond - 40$/ month");

        System.out.print("Your option: ");
        int mo = Validation.checkInt("");

        String cr = "";
        System.out.print("How many months you want to subscribe: ");
        int durationMonths = sc.nextInt();
        int price = 0;

        switch (mo) {
            case 1:
                cr = "Bronze";
                price = 5 * durationMonths;
                break;
            case 2:
                cr = "Silver";
                price = 10 * durationMonths;
                break;
            case 3:
                cr = "Gold";
                price = 20 * durationMonths;
                break;
            case 4:
                cr = "Diamond";
                price = 40 * durationMonths;
                break;
            default:
                break;
        }

        mm.create(cr, durationMonths, price);

    }

    private void updateMembership() {
        System.out.print("Choose the membership you desire (Use 1|2|3|4): ");
        System.out.println("1.Bronze - 5$/month");
        System.out.println("2.Silver - 10$/month");
        System.out.println("3.Gold - 20$/month");
        System.out.println("4.Diamond - 40$/month");
        int mo = Validation.checkInt("");
        String up = "";
        System.out.print("How many months you want to subscribe: ");
        int updDurationMonths = sc.nextInt();
        int updPrice = 0;

        switch (mo) {
            case 1:
                up = "Bronze";
                updPrice = 5 * updDurationMonths;
                break;
            case 2:
                up = "Silver";
                updPrice = 10 * updDurationMonths;
                break;
            case 3:
                up = "Gold";
                updPrice = 20 * updDurationMonths;
                break;
            case 4:
                up = "Diamond";
                updPrice = 40 * updDurationMonths;
                break;
            default:
                break;
        }
        mm.update(up, updDurationMonths, updPrice);
    }

    private void deleteMembership() {
        //mm.delete();
    }

}
