/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Payment {
    private int paymentID;
    private int memberID;
    private Date paymentDate;
    private Date renewalDate;

    // Constructor
    public Payment(int memberID, Date paymentDate, Date renewalDate) {
        this.memberID = memberID;
        this.paymentDate = paymentDate;
        this.renewalDate = renewalDate;
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", memberID=" + memberID +
                ", paymentDate=" + paymentDate +
                ", renewalDate=" + renewalDate +
                '}';
    }
}

