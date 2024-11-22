/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Access.UserLogin;
import Access.UserRegistration;
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

    private void Register() throws NoSuchAlgorithmException, SQLException {
        System.out.print("Username: ");
        String username = Validation.checkString("");

        System.out.print("Password: ");
        String password = Validation.checkString("");
        if (PasswordCheck.checkLength(password)) {
            System.out.println("Password must be longer than 6 characters");
            return;
        }

        System.out.print("Fullname: ");
        String name = Validation.checkString("");

        System.out.print("Email: ");
        String email = Validation.checkString("");

        String role = "";
        int roleChoice = 0;

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
                    }
                    break;

                case 0:

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
                System.out.println("Your option (1 | 2): ");
                genderChoice = Validation.checkInt("");
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
                System.out.print("Phone number (length > 6): ");
                phoneNumber = Validation.checkString("");
                if (phoneNumber.length() > 6) {
                    ur.Register(username, password, name, email, role, phoneNumber, gender);
                } else {
                    System.out.println("Your phone number must be longer than 6! Try again");
                }
            } while (phoneNumber.length() <= 6);
        } else {
            System.out.println("The role is empty! Failed to create account");
        }
    }
}
