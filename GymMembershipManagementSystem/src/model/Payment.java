/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

public class Payment {

    private int paymentID;
    private int memberID;
    private int moneyPaid;
    private LocalDate paymentDate;
    private LocalDate renewalDate;
    private String status;

    // Constructor
    public Payment(int paymentID, int memberID, int moneyPaid, LocalDate paymentDate, LocalDate renewalDate, String status) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.moneyPaid = moneyPaid;
        this.paymentDate = paymentDate;
        this.renewalDate = renewalDate;
        this.status = status;
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

    public int getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(int moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{"
                + "paymentID=" + paymentID
                + ", memberID=" + memberID
                + ", moneyPaid=" + moneyPaid
                + ", paymentDate=" + paymentDate
                + ", renewalDate=" + renewalDate
                + ", status= " + status
                + '}';
    }
}
