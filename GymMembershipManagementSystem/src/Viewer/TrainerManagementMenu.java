/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.CheckCondition;
import Utils.GetByIDList;
import Utils.Show;
import Utils.Validation;
import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class TrainerManagementMenu {

    CheckCondition cc = new CheckCondition();
    Show show = new Show();
    Scanner sc = new Scanner(System.in);
    GetByIDList gbil = new GetByIDList();

    public void assignAndSchedule(String currentUser) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Show assigned members");
            System.out.println("0. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    showAssignedMembers();
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    private void showAssignedMembers() {
        
    }
}
