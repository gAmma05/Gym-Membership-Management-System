/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.Member;

/**
 *
 * @author gAmma
 */
public interface IAdminManagement {

    void createMembership(String membershipName, int durationMonths, int price, String benefit) throws ClassNotFoundException;

    void updateMembership(int membershipId, String newMembershipPlan, int newDurationMonths, int newPrice, String newBenefit) throws ClassNotFoundException;

    void deleteMembership(int membershipID) throws ClassNotFoundException;
    
    //////////////////////////////////////////////////////////////////////
    
}
