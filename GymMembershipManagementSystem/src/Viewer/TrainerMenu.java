/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.CheckCondition;
import Utils.GetByIDSQL;
import Utils.Validation;
import model.User;

/**
 *
 * @author gAmma
 */
public class TrainerMenu {

    CheckCondition cc = new CheckCondition();
    TrainerManagementMenu tmm = new TrainerManagementMenu();
    GetByIDSQL gbis = new GetByIDSQL();

    public void trMenu(String currentUser) {
        int option;

        int trainerID = gbis.getIDByUsername(currentUser);
        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Welcome trainer," + " " + currentUser);//user welcome

            System.out.println("We only accept credit card!");
            System.out.println("1. View schedule and assigned member");
            System.out.println("2. Manage the member progress");
            System.out.println("3. Your information");
            System.out.println("4. Logout");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    tmm.Schedule(currentUser, trainerID);
                    break;

                case 2:
                    tmm.memberProgressManagement(currentUser, trainerID);
                    break;

                case 3:
                    yourInformation(trainerID);
                    break;

                case 4:
                    System.out.println("Logout successfully");
                    return;

                default:
            }
        } while (option != 0);
    }

    private void yourInformation(int trainerID) {
        User use = gbis.getUserByID(trainerID);
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
