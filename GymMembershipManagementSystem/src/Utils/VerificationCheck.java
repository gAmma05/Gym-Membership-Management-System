/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Viewer.MemberMenu;
import Viewer.TrainerMenu;

/**
 *
 * @author gAmma
 */
public class VerificationCheck {
    MemberMenu mm = new MemberMenu();
    
    public void roleCheck(String role){
        if(role.equals("member")){
            mm.mmenu();
        }
    }
}
