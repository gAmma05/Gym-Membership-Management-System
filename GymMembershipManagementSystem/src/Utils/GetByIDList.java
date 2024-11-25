/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Member;
import model.MemberProgress;
import model.MembershipPlan;
import model.Trainer;
import model.TrainingSession;
import model.User;

/**
 *
 * @author gAmma
 */
public class GetByIDList {
    public MembershipPlan getMembershipPlanByID(int ID){
        List<MembershipPlan> mpl = new ArrayList<>();
        for(MembershipPlan mp : mpl){
            if(ID == mp.getMembershipID()){
                return mp;
            }
        }
        return null;
    }
    
    public User getUserByID(int ID){
        List<User> use = new ArrayList<>();
        for(User u : use){
            if(ID == u.getId()){
                return u;
            }
        }
        return null;
    }
    
    public Member getMemberByID(int ID){
        List<Member> mem = new ArrayList<>();
        for(Member m : mem){
            if(ID == m.getId()){
                return m;
            }
        }
        return null;
    }
    
    public Trainer getTrainerByID(int ID){
        List<Trainer> tra = new ArrayList<>();
        for(Trainer t : tra){
            if(ID == t.getId()){
                return t;
            }
        }
        return null;
    }
    
    public Admin getAdminByID(int ID){
        List<Admin> adm = new ArrayList<>();
        for(Admin a : adm){
            if(ID == a.getId()){
                return a;
            }
        }
        return null;
    }
    
    public TrainingSession getTSByID(int ID){
        List<TrainingSession> tsl = new ArrayList<>();
        for(TrainingSession ts : tsl){
            if(ID == ts.getSessionID()){
                return ts;
            }
        }
        return null;
    }
    
    public MemberProgress getMPByID(int ID){
        List<MemberProgress> mpl = new ArrayList<>();
        for(MemberProgress mp : mpl){
            if(ID == mp.getProgressID()){
                return mp;
            }
        }
        return null;
    }
}
