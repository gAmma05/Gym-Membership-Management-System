/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDate;
import model.MemberProgress;

/**
 *
 * @author gAmma
 */
public interface ITrainerManagement {

    void addMemberProgress(MemberProgress mpr);

    void updateMemberProgress(int progressID, LocalDate dateCreated, String workoutHistory, String healthMetrics);
    
    void deleteMemberProgress(int progressID);

    boolean viewMemberProgress(int memberId);
    
    boolean viewAssignedMember(int trainerID);
    
    boolean viewSchedule(int trainerID);
}
