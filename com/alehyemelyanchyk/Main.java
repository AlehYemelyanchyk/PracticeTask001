package com.alehyemelyanchyk;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Application application = new Application();

    private static boolean quit = false;

    public static void main(String[] args) throws IOException {


        boolean continueLoop;
        StartApp();
        application.readFromFile();
        showMenu();

        while (!quit) {
            int choice = 0;
            do {
                try {
                    System.out.println("Please, make your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    continueLoop = false;
                } catch (InputMismatchException e) {
                    System.out.println("Use numbers only. Please, try again.");
                    continueLoop = true;
                    scanner.next();
                }
            } while (continueLoop);
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    showUsers();
                    break;
                case 0:
                    quit = true;
                    System.out.println("We're sorry you're leaving :(");
            }

        }

    }

    private static void register() {
        System.out.println("Enter new login:");
        String login = scanner.nextLine();
        System.out.println("Enter new password:");
        String password = scanner.nextLine();
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        User newUser = User.createUser(login, password, email);
        if (application.register(newUser)) {
            System.out.println("\nThe new user has been registered successfully");
            showMenu();
        } else {
            System.out.println("\nA user with such login or email already exists. Please, try again");
            showMenu();
        }
    }

    private static void login() {
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        if (application.login(login, (String.valueOf(password.hashCode())))) {
            System.out.println("Welcome, " + login);
            System.out.println("To quit, press 0\n"
                    + "To logout press 1");
            boolean logout = false;
            int choice;
            while (!logout) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        logout();
                        logout = true;
                        break;
                    case 0:
                        logout = true;
                        quit = true;
                }
            }
        } else {
            System.out.println("\nThe password or the login is not correct. Please, try again.");
            showMenu();
        }
    }

    private static void logout() {
        System.out.println("You've been logged out successfully");
        showMenu();
    }

    public static void showUsers() {
        application.showUsers();
    }

    public static void showMenu() {
        System.out.println("\n"
                + "Choose an option:\n"
                + "1. Register a new user;\n"
                + "2. Login;\n"
                + "3. Show users;\n"
                + "0. Quit.");
    }

    public static void StartApp() {
        System.out.println("\nWelcome to the PracticeTask001!");
    }
}
