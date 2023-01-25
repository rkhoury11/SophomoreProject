package com.example.login;

import java.util.*;
import java.util.regex.*;

public class Main {
    public static Map<String, String> userList = new HashMap<String, String>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome!");
        System.out.println("What would you like to do?");

        showInitialOptions();


//        System.out.print("Username: ");
//
//        String username = scanner.next();
//
//        System.out.print("Password: ");
//
//        String password = scanner.next();
//
//        userList.add(new User(username, password));
//
//        System.out.println(userList.get(0).getUsername());
    }

    private static void showInitialOptions() {
        System.out.println("Please select one: " +
                "\n\t1. Log In" +
                "\n\t2. Create Account" +
                "\n\t3. Quit");

        int input = scanner.nextInt();
        switch (input) {
            case 1:
                logIn();
                break;
            case 2:
                createAccount();
                break;
            default:
                break;
        }
    }

    private static void createAccount() {

        System.out.println("Creating account..." +
                "\nPlease enter a username: ");
        String username = scanner.next();
        // if user does not type anything, invalidate and ask again
        if (username.equals("")) {
            System.out.println("Invalid username! Try again");
            createAccount();

            // or if username is already taken, invalidate and ask again
        } else if (userList.containsKey(username)) {
            System.out.println("Username already exists. Try again");
            createAccount();

            // if everything is ok, ask for a password
        } else {
            System.out.println("Password must be between 8 and 16 characters" +
                    "and satisfy 3 out of the 4 following requirements:" +
                    "\n\t1. At least one digit"
                    + "\n\t2. One lower case letter \n\t3. One upper case letter\n\t4. One symbol.");
            System.out.println("Please enter a password:");
            String password = scanner.next();

            //if user enters nothing, invalidate password and try again
            if (validatePassword(password) == false) {
                createAccount();

                //if password is ok, let them know, add account to the database and show initial options
            } else {
                System.out.println("Account successfully created!");
                userList.put(username, password);
                showInitialOptions();
            }
        }
    }

    private static void logIn() {
        System.out.println("Logging in..." +
                "\nUsername:");
        String username = scanner.next();

        // if username is in stored list of usernames, ask for password
        if (userList.containsKey(username)) {
            System.out.println("Password:");
            String password = scanner.next();

            // if password matches database, welcome the user
            if (userList.containsValue(password)) {
                System.out.println("Welcome " + username + "!");

                // else, tell them the password does not exist and provide a pop-up menu
            } else {
                System.out.println("This username does not exist!" +
                        "\n\t1. Try again" +
                        "\n\t2. Forgot my password" +
                        "\n\t3. Quit");
                int optionP = scanner.nextInt();

                // selecting 1 takes you back to the log in menu
                // selecting 2 takes you back to the log in menu + a fuck you comment
                // quit exits the whole app
                switch (optionP) {
                    case 1:
                        logIn();
                        break;
                    case 2:
                        System.out.println("That's tough");
                        logIn();
                        break;
                    default:
                        break;
                }
            }

            // if username does not exist in database, provide them with a pop-up menu with three options
        } else {
            System.out.println("This username does not exist!" +
                    "\n\t1. Try again" +
                    "\n\t2. Create an account" +
                    "\n\t3. Quit");
            int option = scanner.nextInt();

            // selecting 2 will provide user the create account function
            switch (option) {
                case 1:
                    logIn();
                    break;
                case 2:
                    createAccount();
                    break;
                default:
                    break;
            }
        }
    }

    private static boolean validatePassword(String str) {
        int count = 0;
        str = str.replace(" ", "");

        if (isGoodLength(str) == false) {
            System.out.println("Invalid Password");
            System.out.println("Password length must be between 8 and 16 characters");
            return false;
        } else {
            if (isDigit(str) == true) {
                count++;
            }
            if (isLowerCase(str) == true) {
                count++;
            }
            if (isUpperCase(str) == true) {
                count++;
            }
            if (isSymbol(str) == true) {
                count++;
            }

            if (count >= 3) {
                System.out.println("Valid password!");
                return true;
            } else {
                System.out.println("Invalid Password. Password must satisfy 3 out of the 4 following requirements:" +
                                "\n\t1. At least one digit"
                                + "\n\t2. One lower case letter \n\t3. One upper case letter\n\t4. One symbol.");
                return false;
            }
        }
    }

    private static boolean isGoodLength(String password) {
        if (password.length()< 8 || password.length() > 16){
            return false;
        }
        return true;
    }

    private static boolean isDigit(String password) {
        String regex = ".*[0-9].*";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

    private static boolean isLowerCase(String password) {
        String regex = ".*[a-z].*";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

    private static boolean isSymbol(String password) {
        String regex = ".*[~!@#$%^&*()=+_-].*";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

    private static boolean isUpperCase(String password) {
        String regex = ".*[A-Z].*";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }
}