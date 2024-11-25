/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.TrainerManagement;
import Utils.CheckCondition;
import Utils.GetByIDList;
import Utils.GetByIDSQL;
import Utils.Show;
import Utils.Validation;
import java.time.LocalDate;
import java.util.Scanner;
import model.MemberProgress;

/**
 *
 * @author gAmma
 */
public class TrainerManagementMenu {

    CheckCondition cc = new CheckCondition();
    Show show = new Show();
    Scanner sc = new Scanner(System.in);
    GetByIDList gbil = new GetByIDList();
    GetByIDSQL gbis = new GetByIDSQL();
    TrainerManagement tm = new TrainerManagement();

    public void Schedule(String currentUser, int trainerID) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Show assigned member");
            System.out.println("2. Schedule");
            System.out.println("3. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    showAssignedMembers(trainerID);
                    break;

                case 2:
                    scheduleAssignedMem(trainerID);
                    break;

                case 3:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    public void memberProgressManagement(String currentUser, int trainerID) {
        int option;

        do {
            if (!cc.usernameCheck(currentUser)) {
                return;
            }
            System.out.println(" ------------//------------");
            System.out.println("Your current user: " + currentUser);
            System.out.println("We only accept credit card!");
            System.out.println("1. Create member progress");
            System.out.println("2. Update member progress");
            System.out.println("3. Delete member progress");
            System.out.println("4. View member progress");
            System.out.println("5. Exit");

            option = Validation.checkInt("Your option: ");

            switch (option) {
                case 1:
                    addMemberProgress(trainerID);
                    break;

                case 2:
                    updMemberProgress(trainerID);
                    break;

                case 3:
                    deleteMemberProgress(trainerID);
                    break;
                    
                case 4:
                    showMemberProgress(trainerID);
                    break;

                case 5:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;

            }

        } while (option != 0);
    }

    private void addMemberProgress(int trainerID) {
        int memberID = gbis.getAssignedMemIfTra(trainerID);
        if (memberID == 0) {
            System.out.println("You can't create member progress since you don't have any assigned member");
        } else {

            String memberName = gbis.getMemberNameFromTS(trainerID);
            System.out.println("Your assigned member: " + memberName);

            int progressID = Validation.checkInt("Insert progress ID: ");

            System.out.println(memberName + "'s " + "health metrics: ");
            int weight = Validation.checkInt("Weight: ");
            float height = Validation.checkFloat("Height: ");
            double BMI = weight / (height * height);
            System.out.printf("BMI = %.2f%n", BMI);
            String healthMetric = String.format("Weight = %d  Height = %.2f, BMI = %.2f", weight, height, BMI);

            System.out.println(memberName + "'s " + "workout history: ");
            int pushupCount = Validation.checkInt("Push-up count: ");
            int treadmillTime = Validation.checkInt("Treadmill time (by seconds): ");
            int weightLiftingCount = Validation.checkInt("Weightlifting count: ");
            String workoutHistory = String.format("Push-up count = %d  Treadmill Time (count by secs) = %d, Weightlifting count = %d", pushupCount, treadmillTime, weightLiftingCount);

            LocalDate dateCreated = LocalDate.now();

            MemberProgress mpr = new MemberProgress(progressID, memberID, memberName, dateCreated, healthMetric, workoutHistory);
            tm.addMemberProgress(mpr);
        }
    }

    private void updMemberProgress(int trainerID) {
        int memberID = gbis.getAssignedMemIfTra(trainerID);
        int progressID = gbis.getProgressID(memberID);
        if (progressID == 0) {
            System.out.println("No progress found");
        } else {
            String memberName = gbis.getMemberNameFromTS(trainerID);
            MemberProgress mp = gbil.getMPByID(progressID);

            System.out.println(memberName + "'s " + "health metrics: ");
            int weight = Validation.checkInt("Weight: ");
            float height = Validation.checkFloat("Height: ");
            double BMI = weight / (height * height);
            System.out.printf("BMI = %.2f", BMI);
            String healthMetric = String.format("Weight = %d  Height = %.2f, BMI = %.2f", weight, height, BMI);

            System.out.println(memberName + "'s " + "workout history: ");
            int pushupCount = Validation.checkInt("Push-up count: ");
            int treadmillTime = Validation.checkInt("Treadmill time (by seconds): ");
            int weightLiftingCount = Validation.checkInt("Weightlifting count: ");
            String workoutHistory = String.format("Push-up count = %d  Treadmill Time (count by secs) = %d, Weightlifting count = %d", pushupCount, treadmillTime, weightLiftingCount);

            LocalDate dateCreated = LocalDate.now();

            if (mp != null) {
                mp.setDate(dateCreated);
                mp.setHealthMetrics(healthMetric);
                mp.setWorkoutHistory(workoutHistory);
            }
            tm.updateMemberProgress(progressID, dateCreated, workoutHistory, healthMetric);
        }
    }
    
    private void deleteMemberProgress(int trainerID){
        int memberID = gbis.getAssignedMemIfTra(trainerID);
        int progressID = gbis.getProgressID(memberID);
        if (progressID == 0) {
            System.out.println("No progress found");
        } else{
            tm.deleteMemberProgress(progressID);
        }
    }

    private void showAssignedMembers(int trainerID) {
        if (tm.viewAssignedMember(trainerID)) {
            //print
        } else {
            System.out.println("You don't have any assigned member");
        }
    }

    private void scheduleAssignedMem(int trainerID) {
        if (tm.viewSchedule(trainerID)) {
            //print
        } else {
            System.out.println("You don't have any schedule");
        }
    }

    private void showMemberProgress(int trainerID) {
        int memberID = gbis.getAssignedMemIfTra(trainerID);
        int progressID = gbis.getProgressID(memberID);
        if (progressID > 0) {
            tm.viewMemberProgress(progressID);
        } else {
            System.out.println("No member progress found");
        }
    }
}
