/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author gAmma
 */
public interface ITrainerManagement {

    void addMemberProgress(int memberID, String memberName, String dateCreated, String workoutHistory, String healthMetrics);

    void updateMemberProgress(int progressID, String workoutHistory, String healthMetrics);

    void viewMemberProgress(int memberId);

}
