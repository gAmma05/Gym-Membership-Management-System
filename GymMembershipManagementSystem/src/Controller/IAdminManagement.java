/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDateTime;
import model.MembershipPlan;
import model.TrainingSession;

/**
 *
 * @author gAmma
 */
public interface IAdminManagement {

    void createMembership(MembershipPlan mp);

    void updateMembership(int membershipID, String newMembershipPlan, int newDurationMonths, int newPrice, String newBenefit);

    void deleteMembership(int membershipID);

    //////////////////////////////////////////////////////////////////////
    /*
    void deleteUsers(int userID);

    void deleteAdmins(int userID);

    void deleteMembers(int userID);

    void deleteTrainers(int userID);
     */
    //////////////////////////////////////////////////////////////////////
    void assignTrainerToMember(TrainingSession ts);

    void scheduleTrainingSession(int sessionID, int trainerID, int memberID, LocalDateTime sessionTime, String location, int durationByMinutes);

    void deleteTrainingSession(int sessionID);
}
