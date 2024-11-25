/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDate;
import model.Payment;

/**
 *
 * @author gAmma
 */
public interface IMemberManagement {

    void registerMembership(int membershipID, int memberID);

    void cancelMembership(int memberID);

    void renewMembership(int membershipID, int memberID);

    void trackMemberProgress(int memberID);

    void paymentCreate(Payment pm);

    void paymentUpdate(int newMoneyPaid, LocalDate renewalDate, String newStatus);
}
