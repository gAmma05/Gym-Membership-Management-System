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
import model.Trainer;
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
            System.out.println("3. Your assigned trainer");
            System.out.println("4. Your information");
            System.out.println("5. Exit");
            //System.out.print("Your option: "); //reluctant

            option = Validation.checkInt("Your option: "); // Ensure you have a Validation class with checkInt()

            switch (option) {
                case 1:
                    trackMemberProgress(memberID);
                    break;

                case 2:
                    msMenu(currentUser);
                    break;

                case 3:
                    yourAssignedTrainer(memberID);
                    break;

                case 4:
                    yourInformation(memberID);
                    break;

                case 5:
                    System.out.println("Exiting Membership Management. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (option != 5);
    }

    private void trackMemberProgress(int memberID) {
        im.trackMemberProgress(memberID);
    }

    private void msMenu(String currentUser) {
        mm.membershipMenu(currentUser);
    }

    private void yourAssignedTrainer(int memberID) {
        if (cc.checkMemberAssigned(memberID)) {
            int trainerID = gbis.getAssignedTrainerByMember(memberID);
            if (trainerID != 0) {
                Trainer tra = gbis.getTrainerByID(trainerID);
                if (tra != null) {
                    System.out.printf("%-10s    %-10s     %-10s%n", "TrainerName",  "Join Date", "Experience Year");
                    System.out.printf("%-10s    %-10s     %-10d%n", tra.getName(), tra.getJoinDate(), tra.getExpYear());
                } else {
                    System.out.println("No assigned trainer");
                }
            }else{
                System.out.println("No trainer found");
            }
        } else {
            System.out.println("You haven't been assigned to any trainer");
        }

    }

    private void yourInformation(int memberID) {
        User use = gbis.getUserByID(memberID);
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
