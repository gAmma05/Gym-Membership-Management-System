/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.CheckCondition;
import Utils.Validation;

/**
 *
 * @author gAmma
 */
public class TrainerMenu {

    CheckCondition cc = new CheckCondition();
    TrainerManagementMenu tmm = new TrainerManagementMenu();

    public void trMenu(String currentUser) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Welcome trainer," + " " + currentUser);//user welcome

            System.out.println("We only accept credit card!");
            System.out.println("1. Assign trainers & Schedule the trainings");
            System.out.println("2. Manage the member progress");
            System.out.println("3. Logout");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Logout successfully");
                    return;

                default:
            }
        } while (option != 0);
    }

}
