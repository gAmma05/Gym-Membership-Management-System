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
import java.time.LocalDate;
import java.util.Scanner;
import model.Payment;

/**
 *
 * @author Admin
 */
public class MemberManagementMenu {

    private final Scanner sc = new Scanner(System.in);
    Show show = new Show();
    GetByIDSQL gbis = new GetByIDSQL();
    CheckCondition cc = new CheckCondition();

    IMemberManagement im = new MemberManagement();

    public void membershipMenu(String currentUser) {
        int option;

        int memberID = gbis.getIDByUsername(currentUser);

        do {
            System.out.println("\n=== Membership Management ===");
            System.out.println("Current User: " + currentUser);
            System.out.println("1. Register for a Membership");
            System.out.println("2. Cancel Membership");
            System.out.println("3. Renew membership");
            System.out.println("4. Show your membership plan");
            System.out.println("5. Manage payment");
            System.out.println("6. Exit");

            option = Validation.checkInt("Your option: "); // Ensure you have a Validation class with checkInt()

            switch (option) {
                case 1:
                    registerMembership(memberID);
                    break;

                case 2:
                    cancelMembership(memberID);
                    break;

                case 3:
                    renewMembership(memberID);
                    break;

                case 4:
                    showMembershipPlan(memberID);
                    break;

                case 5:
                    paymentManagement(memberID);
                    break;

                case 6:
                    System.out.println("Exiting Membership Management. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (option != 6);
    }

    private void registerMembership(int memberID) {
        System.out.println("\n=== Register Membership ===");

        if (cc.isMembershipActive(memberID)) {
            System.out.println("You are currently using an active membership plan");
        } else if (show.showMembershipPlanList()) {
            int membershipID = Validation.checkInt("Enter membership ID: ");
            if (!cc.membershipIDCheck(membershipID)) {
                System.out.println("No ID found");
            } else {
                im.registerMembership(membershipID, memberID);
            }
        } else {
            System.out.println("There is currently unavailable membership plan for you. Sorry for the inconvenience");
        }

    }

    private void cancelMembership(int memberID) {
        System.out.println("\n=== Cancel Membership ===");
        if (!cc.isMembershipActive(memberID)) {
            System.out.println("You haven't registered any membership plan");
        } else {
            im.cancelMembership(memberID);
        }
    }

    private void renewMembership(int memberID) {
        System.out.println("\n=== Renew Membership ===");
        if (!cc.isMembershipActive(memberID)) {
            System.out.println("You haven't registered any membership plan");
        } else {
            if (show.showMembershipPlanList()) {
                int membershipID = Validation.checkInt("Enter membership ID: ");
                if (!cc.membershipIDCheck(membershipID)) {
                    System.out.println("No ID found");
                } else {
                    im.renewMembership(membershipID, memberID);
                }
            } else {
                System.out.println("There is currently unavailable membership plan for you. Sorry for the inconvenience");
            }
        }
    }

    private void showMembershipPlan(int memberID) {
        System.out.println("\n=== Show Membership ===");
        int msID = gbis.getMembershipIDByMember(memberID);
        if (msID != 0) {
            im.showMembershipPlan(msID);
        } else {
            System.out.println("You haven't registered any plan");
        }
    }

    private void paymentManagement(int memberID) {

        int choice;
        do {
            System.out.println("\n=== Select Payment Method ===");
            System.out.println("1. Create payment");
            System.out.println("2. Renew payment");
            System.out.println("3. Delete payment");
            System.out.println("4. Show your payment list(Completed/Uncompleted)");
            System.out.println("5. Exit");
            choice = Validation.checkInt("Choose your payment method: ");

            switch (choice) {
                case 1:
                    int paymentID = Validation.checkInt("Insert payment ID: ");
                    if (cc.isPaymentDuplicated(paymentID)) {
                        System.out.println("This payment ID is being used");
                    } else {
                        int moneyPaid = Validation.checkInt("Insert money to pay: ");
                        LocalDate paymentDate = LocalDate.now();
                        String confirm1 = Validation.checkYesNo("Do you want to pay it right now?(Y/N): ");
                        if (confirm1.equalsIgnoreCase("y")) {
                            String status = "Completed";
                            Payment pm = new Payment(paymentID, memberID, moneyPaid, paymentDate, null, status);
                            im.paymentCreate(pm);
                        } else if (confirm1.equalsIgnoreCase("n")) {
                            String status = "Uncompleted";
                            Payment pm = new Payment(paymentID, memberID, moneyPaid, paymentDate, null, status);
                            im.paymentCreate(pm);
                        }
                    }
                    break;

                case 2:
                    int newPaymentID = Validation.checkInt("Insert payment ID: ");
                    if (cc.isPaymentDuplicated(newPaymentID)) {
                        System.out.println("ok");
                        Payment newPM = gbis.getPaymentByID(memberID);

                        int newMoneyPaid = Validation.checkInt("Insert money to pay: ");
                        LocalDate renewalDate = LocalDate.now();

                        if (newPM != null) {
                            newPM.setMoneyPaid(newMoneyPaid);
                            newPM.setRenewalDate(renewalDate);
                        }

                        String confirm2 = Validation.checkYesNo("Do you want to pay it now? (Y/N): ");
                        String newStatus = "";
                        if (confirm2.equalsIgnoreCase("y")) {
                            newStatus = "Completed";
                            if (newPM != null) {
                                newPM.setStatus(newStatus);
                            }

                        } else if (confirm2.equalsIgnoreCase("n")) {
                            newStatus = "Uncompleted";
                            if (newPM != null) {
                                newPM.setStatus(newStatus);
                            }
                        }
                        im.paymentUpdate(memberID, newMoneyPaid, renewalDate, newStatus);
                    }else{
                        System.out.println("No ID found");
                    }
                    break;
                case 3:
                    int delPaymentID = Validation.checkInt("Insert payment ID: ");
                    if (cc.isPaymentDuplicated(delPaymentID)) {
                        im.paymentDelete(delPaymentID);
                    }else{
                        System.out.println("No ID found");
                    }
                    break;

                case 4:
                    im.showPaymentList(memberID);
                    break;
                case 5:
                    System.out.println("Exiting payment selection.");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (choice != 5);
    }

}
