package model;

public class MembershipPlan {
    private int membershipID;
    private String membershipName;
    private int durationMonths;
    private int price;
    private String benefit;

    // Constructor
    public MembershipPlan(int membershipID, String membershipName, int durationMonths, int price, String benefit) {
        this.membershipID = membershipID;
        this.membershipName = membershipName;
        this.durationMonths = durationMonths;
        this.price = price;
        this.benefit = benefit;
    }

    // Getter and Setter methods

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}
