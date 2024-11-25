/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.IMemberManagement;
import Controller.MemberManagement;
import Utils.CheckCondition;
import Utils.GetByIDSQL;
import Utils.Show;
import Utils.Validation;
import java.util.Scanner;
import model.Member;
import model.User;

/**
 *
 * @author Admin
 */
public class MemberMenu {

    private final Scanner sc = new Scanner(System.in);
    Show show = new Show();
    GetByIDSQL gbis = new GetByIDSQL();
    CheckCondition cc = new CheckCondition();
    
    MemberManagementMenu mm = new MemberManagementMenu();
    IMemberManagement im = new MemberManagement();

    public void membershipMenu(String currentUser) {

        int option;

        int memberID = gbis.getIDByUsername(currentUser);

        do {
            System.out.println("\n=== Member Management ===");
            System.out.println("Welcome member," + " " + currentUser);//user welcome
            System.out.println("1. Track member progress");
            System.out.println("2. Membership menu");
            System.out.println("3. Your information");
            System.out.println("4. Exit");
            System.out.print("Your option: ");

            option = Validation.checkInt("Your option: "); // Ensure you have a Validation class with checkInt()

            switch (option) {
                case 1:
                    trackMemberProgress(memberID);
                    break;

                case 2:
                    mm.membershipMenu(currentUser);
                    break;

                case 3:
                    yourInformation(memberID);
                    break;
                case 4:
                    System.out.println("Exiting Membership Management. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (option != 4);
    }

    private void trackMemberProgress(int memberID){
        im.trackMemberProgress(memberID);
    }
    
    private void yourInformation(int memberID){
        User use = gbis.getUserByID(memberID);
        if(use != null){
            System.out.println("Username: " + use.getUsername());
            System.out.println("Password: " + "*****");
            System.out.println("Email: " + use.getEmail());
            System.out.println("Phone number: " + use.getPhoneNumber());
            System.out.println("Gender: " + use.getGender());
        }else{
            System.out.println("No information found, please login again");
        }
    }
}
