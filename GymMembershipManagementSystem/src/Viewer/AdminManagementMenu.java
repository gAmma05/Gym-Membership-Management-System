/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.AdminManagement;
import Controller.IAdminManagement;
import Utils.CheckCondition;
import Utils.GetByIDList;
import Utils.GetByIDSQL;
import Utils.Validation;
import Utils.Show;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Admin;
import model.Member;
import model.MembershipPlan;
import model.Trainer;
import model.TrainingSession;
import model.User;

/**
 *
 * @author gAmma
 */
public class AdminManagementMenu {

    IAdminManagement am = new AdminManagement();
    CheckCondition cc = new CheckCondition();
    Show show = new Show();
    Scanner sc = new Scanner(System.in);
    GetByIDList gbil = new GetByIDList();
    GetByIDSQL gbis = new GetByIDSQL();

    List<MembershipPlan> mpl = new ArrayList<>();
    List<TrainingSession> tsl = new ArrayList<>();
    List<Member> mem = new ArrayList<>();
    List<Trainer> tra = new ArrayList<>();
    List<Admin> adm = new ArrayList<>();
    List<User> use = new ArrayList<>();

    public void membershipManagement(String currentUser) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Create membership");
            System.out.println("2. Update membership");
            System.out.println("3. Delete membership");
            System.out.println("4. Exit");

            option = Validation.checkInt("Your option: ");

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
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    public void trainerAndSessionManagement(String currentUser) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Show trainer's list");
            System.out.println("2. Show member's list");
            System.out.println("3. Assign trainer to members");
            System.out.println("4. Schedule training session");
            System.out.println("5. Delete training sessions");
            System.out.println("6. Show training sessions");
            System.out.println("7. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    showTrainerList();
                    break;

                case 2:
                    showMemberList();
                    break;

                case 3:
                    assignTrainerToMember();
                    break;

                case 4:
                    scheduleTrainingSession();
                    break;

                case 5:
                    deleteTrainingSession();
                    break;

                case 6:
                    showScheduleTraining();
                    break;

                case 7:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 7);
    }

    public void showMemberProgress(String currentUser) {
        if (!cc.usernameCheck(currentUser)) {
            return;
        }
        showMembProgress();

    }

    public void Settings(String currentUser) {

        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("1. Delete admins");
            System.out.println("2. Delete members");
            System.out.println("3. Delete trainers");
            System.out.println("4. Exit");
            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    deleteAdmins();
                    break;

                case 2:
                    deleteMembers();
                    break;

                case 3:
                    deleteTrainers();
                    break;

                case 4:
                    System.out.println("Exiting");
                    return;
            }
        } while (option != 2);
    }

    private void createMembership() {
        int membershipID = Validation.checkInt("Enter the membership ID you want to create: ");
        if (cc.membershipIDCheck(membershipID)) {
            System.out.println("This membership ID is being used");
        } else {
            String membershipName = Validation.checkString("Enter the membership name you want to create (length < 10): ");
            int durationMonths = Validation.checkInt("Enter the duration of" + " " + membershipName + " " + "membership  (length > 0): ");
            int price = Validation.checkInt("Enter the price of" + " " + membershipName + " " + "membership plan (length > 0): ");
            String benefit = Validation.checkString("Enter benefit of" + " " + membershipName + " " + "membership plan (length < 512): ");

            MembershipPlan membershipPlan = new MembershipPlan(membershipID, membershipName, durationMonths, price, benefit);
            am.createMembership(membershipPlan);
        }
    }

    private void updateMembership() {
        if (show.showMembershipPlanList()) {
            //System.out.println(show.showMembershipPlanList());
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {

                String updMembershipName = Validation.checkString("Enter the membership name you want to update: ");

                int updDurationMonths = Validation.checkInt("Enter the duration of" + " " + updMembershipName + " " + "membership plan (length < 10): ");

                int updPrice = Validation.checkInt("Enter the price of" + " " + updMembershipName + " " + "membership plan (length > 0): ");

                String updBenefit = Validation.checkString("Enter benefit of" + " " + updMembershipName + " " + "membership plan (length < 512): ");

                MembershipPlan mp = gbil.getMembershipPlanByID(membershipID);
                if (mp != null) {
                    mp.setMembershipName(updMembershipName);
                    mp.setDurationMonths(updDurationMonths);
                    mp.setPrice(updPrice);
                    mp.setBenefit(updBenefit);
                }

                am.updateMembership(membershipID, updMembershipName, updDurationMonths, updPrice, updBenefit);
            }
        } else {
            System.out.println("The list is empty!");
        }
    }

    private void deleteMembership() {
        if (show.showMembershipPlanList()) {
            int membershipID = Validation.checkInt("Enter the membership ID you want to update: ");
            boolean checkID = cc.membershipIDCheck(membershipID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                MembershipPlan mp = gbil.getMembershipPlanByID(membershipID);
                if (mp != null) {
                    mpl.remove(mp);
                }
                am.deleteMembership(membershipID);

            }
        }
    }

    private void showTrainerList() {
        if (show.showTrainerList()) {
            System.out.println("");
        } else {
            System.out.println("The list is empty");
        }
    }

    private void showMemberList() {
        if (show.showMemberList()) {
            System.out.println("");
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteAdmins() {
        if (show.showAdminList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                User u = gbil.getUserByID(userID);
                use.remove(u);

                Admin a = gbil.getAdminByID(userID);
                adm.remove(a);

                am.deleteAdmins(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteMembers() {
        if (show.showMemberList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                User u = gbil.getUserByID(userID);
                use.remove(u);

                Member m = gbil.getMemberByID(userID);
                mem.remove(m);

                am.deleteMembers(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void deleteTrainers() {
        if (show.showTrainerList()) {
            System.out.print("Enter the user ID you want to delete: ");
            int userID = sc.nextInt();
            boolean checkID = cc.userIDCheck(userID);
            if (!checkID) {
                System.out.println("No ID found");
            } else {
                User u = gbil.getUserByID(userID);
                use.remove(u);

                Trainer t = gbil.getTrainerByID(userID);
                tra.remove(t);

                am.deleteTrainers(userID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void assignTrainerToMember() {
        int sessionID = Validation.checkInt("Insert training session ID: ");
        if (cc.checkTrainingSession(sessionID)) {
            System.out.println("This session is being used");
        } else {
            int memberID = Validation.checkInt("Insert member ID to be assigned: ");
            if (!cc.userIDCheck(memberID)) {
                System.out.println("This member ID is not found");
            } else if (cc.checkMemberAssigned(memberID)) {
                System.out.println("This member ID has been already assigned");
            } else {
                int trainerID = Validation.checkInt("Insert Trainer ID to assign " + memberID + ": ");
                if (!cc.userIDCheck(trainerID)) {
                    System.out.println("No ID found");
                } else {
                    if (cc.checkTrainerAssigned(trainerID)) {
                        System.out.println("This trainer is currently assigning other member");
                    } else {
                        TrainingSession ts = new TrainingSession(sessionID, trainerID, memberID, null, "N/A", 0);
                        am.assignTrainerToMember(ts);
                    }
                }
            }
        }
    }

    private void scheduleTrainingSession() {
        int sessionID = Validation.checkInt("Insert training session ID to be scheduled: ");
        if (!cc.checkTrainingSession(sessionID)) {
            System.out.println("No ID found");
        } else {
            int duration = gbis.getDurationOfTS(sessionID);
            if (duration == 0) {
                TrainingSession ts = gbil.getTSByID(sessionID);

                System.out.print("Insert session time (yyyy-MM-dd HH:mm:ss): ");
                String sessionTimeInput = sc.nextLine();

                // Use a DateTimeFormatter to parse the input
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime sessionTime = null;
                try {
                    sessionTime = LocalDateTime.parse(sessionTimeInput, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date-time format. Please use yyyy-MM-dd HH:mm:ss: " + e.getMessage());
                    return;
                }

                String location = Validation.checkString("Insert location where you want to train: ");

                int durationByMinutes = Validation.checkInt("Insert the duration by minutes: ");

                if (ts != null) {
                    ts.setSessionTime(sessionTime);
                    ts.setLocation(location);
                    ts.setDurationByMinutes(durationByMinutes);
                }
                int trainerID = gbis.getAssignedTrainer(sessionID);
                int memberID = gbis.getAssignedMember(sessionID);
                am.scheduleTrainingSession(sessionID, trainerID, memberID, sessionTime, location, durationByMinutes);
            } else if (duration > 0) {
                System.out.println("This session is already scheduled");
            }
        }
    }

    private void deleteTrainingSession() {
        if (show.showTrainingSession()) {
            int sessionID = Validation.checkInt("Insert training session ID to delete: ");
            if (!cc.checkTrainingSession(sessionID)) {
                System.out.println("No ID found");
            } else {
                TrainingSession ts = gbil.getTSByID(sessionID);
                if (ts != null) {
                    tsl.remove(ts);
                }
                am.deleteTrainingSession(sessionID);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    private void showScheduleTraining() {
        if (show.showTrainingSession()) {
            //print
        } else {
            System.out.println("The list is empty");
        }
    }

    private void showMembProgress() {
        show.showMemberProgress();
    }

}
