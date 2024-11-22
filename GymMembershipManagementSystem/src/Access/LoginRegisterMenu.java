/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Utils.CheckCondition;
import Utils.PasswordCheck;
import Utils.Validation;
import Utils.Verification;
import Viewer.AdminMenu;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class LoginRegisterMenu {

    UserLogin ul = new UserLogin();
    UserRegistration ur = new UserRegistration();
    Verification veri = new Verification();
    AdminMenu admin = new AdminMenu();
    CheckCondition cc = new CheckCondition();

    public String currentUser;

    public void Menu() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        int option;
        do {
            System.out.println("WELCOME TO KIVOTOS GYM");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            option = Validation.checkInt("Your option: ");
            switch (option) {
                case 1:
                    Login();
                    break;

                case 2:
                    Register();
                    break;

                case 3:
                    System.out.println("Good bye");
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (option > 0 && option < 3);
    }

    private void Login() throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        System.out.print("Username: ");
        String username = Validation.checkString("");

        System.out.print("Password: ");
        String password = Validation.checkString("");

        /*
        String yn = "";
        do {
            if (PasswordCheck.checkLength(password)) {
                System.out.println("Password must be longer than 6 characters");
                yn = Validation.checkYesNo("Do you want to continue trying again your password? (Y/N): ");
                if(yn.equalsIgnoreCase("n")){
                    return;
                }
            }
        } while (PasswordCheck.checkLength(password) && yn.equalsIgnoreCase("y"));
         */
        if (ul.Login(username, password)) {
            currentUser = username;
            if (veri.checkRole(username).equalsIgnoreCase("Admin")) {
                admin.adMenu(currentUser);
            } else if (veri.checkRole(username).equalsIgnoreCase("Member")) {
                //Access to member menu
            } else if (veri.checkRole(username).equalsIgnoreCase("Trainer")) {
                //Access to trainer menu
            }
        } else {
            System.out.println("Wrong username or password. Try again");
        }
    }

    private void Register() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        String username = Validation.checkString("Username: ");
        if (cc.usernameCheck(username)) {
            System.out.println("The username is already used");
            return;
        }

        String password = Validation.checkString("Password: ");
        if (PasswordCheck.checkLength(password)) {
            System.out.println("Password must be longer than 6 characters");
            return;
        }

        String name = Validation.checkString("Fullname: ");

        String email = Validation.checkString("Email: ");

        String role = "";
        int roleChoice = 0;
        int userID = -1;

        do {
            System.out.println("Choose role!");
            System.out.println("1. Member");
            System.out.println("2. Trainer");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Your option (1 | 2 | 3 | 4): ");
            roleChoice = Validation.checkInt("");
            switch (roleChoice) {
                case 1:
                    role = "Member";
                    break;

                case 2:
                    role = "Trainer";
                    break;

                case 3:
                    String ans = Validation.checkString("Tell me! What is this project developer's student code: ");
                    if (ans.equals("SE192605")) {
                        role = "Admin";
                    } else {
                        System.out.println("Sorry but you're wrong! Access denied");
                        return;
                    }
                    break;

                case 4:

                    break;

                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }
        } while (roleChoice != 1 && roleChoice != 2 && roleChoice != 3);

        if (!role.isEmpty()) {
            String gender = "";
            int genderChoice = 0;

            do {
                System.out.println("Choose gender!");
                System.out.println("1. Male");
                System.out.println("2. Female");
                genderChoice = Validation.checkInt("Your option (1 | 2): ");
                switch (genderChoice) {
                    case 1:
                        gender = "Male";
                        break;

                    case 2:
                        gender = "Female";
                        break;

                    default:
                        System.out.println("Invalid input! Try again!");
                        break;

                }
            } while (genderChoice != 1 && genderChoice != 2);

            String phoneNumber;
            do {
                phoneNumber = Validation.checkString("Phone number (length > 6): ");
                if (phoneNumber.length() > 6) {
                    userID = ur.Register(username, password, name, email, role, phoneNumber, gender);
                } else {
                    System.out.println("Your phone number must be longer than 6! Try again");
                }
            } while (phoneNumber.length() <= 6);

            switch (role) {
                case "Member":
                    ur.memberRegister(userID, name, gender);
                    break;

                case "Trainer":
                    int expYear = Validation.checkInt("Your experience year: ");
                    ur.trainerRegister(userID, name, gender, expYear);
                    break;

                case "Admin":
                    ur.adminRegister(userID, name);
                    break;

            }
        } else {
            System.out.println("The role is empty! Failed to create account");
        }
    }
}
