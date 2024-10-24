/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.Validation;
import java.util.Scanner;
import model.Member;

/**
 *
 * @author gAmma
 */
public class MemberMenu {

    public Scanner sc = new Scanner(System.in);

    public void run() {
        int option = 0;
        System.out.println(" ------------//------------");
        System.out.println("Welcome," + "");//user welcome
        do {
            System.out.println("1. Create your plan");
            System.out.println("2. Update your plan");
            System.out.println("3. Delete your plan");
            System.out.println("4. Make the renewal");
            System.out.println("5. Settings");
            System.out.println("6. Logout");
            System.out.println("Your option: ");
            option = sc.nextInt();
            
            switch(option){
                case 1:
                    break;
                    
                case 2:
                    break;
                    
                case 3:
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
        System.out.println("1.Bronze");
        System.out.println("2.Silver");
        System.out.println("3.Gold");
        System.out.println("4.Diamond");

        System.out.print("Your option: ");
        int mo = Validation.checkInt("");
        
        String cr = "";

        switch (mo) {
            case 1:
                cr = "Bronze";
                break;
            case 2:
                cr = "Silver";
                break;
            case 3:
                cr = "Gold";
                break;
            case 4:
                cr = "Diamond";
                break;
            default:
                break;
        }

        System.out.print("We only accept credit card!");

    }

    private void updateMembership() {
        System.out.print("Choose the membership you desire (Use 1|2|3|4): ");
        System.out.println("1.Bronze - 5$/month");
        System.out.println("2.Silver - 10$/month");
        System.out.println("3.Gold - 20$/month");
        System.out.println("4.Diamond - 40$/ month");
        int mo = Validation.checkInt("");
        String up = "";

        switch (mo) {
            case 1:
                up = "Bronze";
                break;
            case 2:
                up = "Silver";
                break;
            case 3:
                up = "Gold";
                break;
            case 4:
                up = "Diamond";
                break;
            default:
                break;
        }

        System.out.print("We only accept credit card!");
        
    }

    private void deleteMembership() {

    }

}
