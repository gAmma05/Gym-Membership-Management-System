/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Access.UserLogin;
import Access.UserRegistration;
import Utils.PasswordCheck;
import Utils.Validation;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class LoginRegisterMenu {

    public UserLogin ul = new UserLogin();
    public UserRegistration ur = new UserRegistration();

    public void Menu() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        int option = 0;
        do {
            System.out.println("WELCOME TO KIVOTOS GYM");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Your option: ");
            option = Validation.checkInt("");
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
            System.out.println("Your option (1 | 2): ");
            roleChoice = Validation.checkInt("");
            switch (roleChoice) {
                case 1:
                    role = "Member";
                    break;

                case 2:
                    role = "Trainer";
                    break;

                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }
        } while (roleChoice != 1 && roleChoice != 2);

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
                    role = "Male";
                    break;

                case 2:
                    role = "Female";
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
    }
}
