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
public interface IMemberManagement {

    void create(String membershipPlan, int durationMonths, int price);

    void update(String updMembershipPlan, int updDurationMonths, int updPrice);

    void delete(int membershipID);
}
