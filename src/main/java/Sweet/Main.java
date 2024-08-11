package Sweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sweetSys.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sweet app = sweet.getInstance();
        System.out.println("\n\n****   Welcome to the Sweet Management System   ****\n");
        successfull user = new successfull();
        String firstName;
        String lastName;
        String email;
        String password;
        String confirmPassword;
        String type = "";
        boolean is_add;
        int choice;
        String idsweet;
        String namesweet;
        String typesweet;
        String pricesweet;
        boolean isValid;
        boolean isexist;
        int userType;
        int menu = 0;

        do {
            System.out.println("1. Sign UP ");
            System.out.println("2. Log In ");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    boolean isAlreadyRegistered = false;
                    System.out.println("Enter your first name:");
                    firstName = scanner.nextLine();
                    System.out.println("Enter your last name:");
                    lastName = scanner.nextLine();

                    // Check if email is valid and not already registered
                    do {
                        System.out.println("Enter your email:");
                        email = scanner.nextLine();

                        if (!user.isValidEmail(email)) {
                            System.out.println("Invalid email syntax.");
                        } else if (person.emailexi(email)) {
                            System.out.println("Email is already registered.");
                            isAlreadyRegistered = true;
                        } else {
                            isAlreadyRegistered = false;
                        }
                    } while (!user.isValidEmail(email) || isAlreadyRegistered);

                    // Check if password is valid and matches
                    do {
                        System.out.println("Enter your password:");
                        password = scanner.nextLine();
                    } while (!user.isValidPassword(password));

                    do {
                        System.out.println("Confirm password:");
                        confirmPassword = scanner.nextLine();
                        isValid = password.equals(confirmPassword);
                    } while (!isValid);

                    if (isValid) {
                        System.out.println("You sign up as:");
                        System.out.println("1- USER");
                        System.out.println("2- ADMIN");
                        System.out.println("3- Supplier");
                        System.out.println("4- Owner");
                        userType = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (userType) {
                            case 1:
                                type = "USER";
                                break;
                            case 2:
                                type = "ADMIN";
                                break;
                            case 3:
                                type = "Supplier";
                                break;
                            case 4:
                                type = "Owner";
                                break;
                            default:
                                System.out.println("Invalid user type.");
                                continue; // Re-show the options if the user type is invalid
                        }

                        // Add user to the list
                        person newUser = new person(email, password, type, firstName, lastName);
                        app.getList_of_people().add(newUser);
                        System.out.println("Sign up successful!");

                    } else {
                        System.out.println("Passwords do not match. Please try again.");
                    }
                    break;

                case 2:
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    if (!person.emailexi(email)) {
                        System.out.println("You are not exist in the system; you need to sign up.");
                    } else {
                        do {
                            System.out.println("Enter your password:");
                            password = scanner.nextLine();
                            person p = new person(email, password);
                            if (person.rightlogin(p)) {
                                isexist = true;
                            } else {
                                System.out.println("Incorrect password. Please try again.");
                                isexist = false;
                            }
                        } while (!isexist);

                        do {
                            System.out.println("You sign up as:");
                            System.out.println("1- USER");
                            System.out.println("2- ADMIN");
                            System.out.println("3- Supplier");
                            System.out.println("4- Owner");
                            userType = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (userType) {
                                case 1:
                                    type = "USER";
                                    break;
                                case 2:
                                    type = "ADMIN";
                                    break;
                                case 3:
                                    type = "Supplier";
                                    break;
                                case 4:
                                    type = "Owner";
                                    break;
                                default:
                                    System.out.println("Invalid user type.");
                                    continue; // Re-show the options if the user type is invalid
                            }
                        } while (!person.isRightType(email, password, type));

                        System.out.println("Login successful!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // Main Menu
            while (menu != 7) {
                System.out.println("Main Menu:");
                System.out.println("1. Add a new sweet");
                System.out.println("2. Delete a sweet");
                System.out.println("3. Update a sweet");
                System.out.println("4. Search a sweet");
                System.out.println("5. Order");
                System.out.println("6. Notification");
                System.out.println("7. Exit");
                System.out.print("Please enter your choice: ");

                menu = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (menu) {
                    case 1:
                        System.out.println("Adding a new sweet:");
                        if (type.equals("Supplier") || type.equals("Owner")) {
                            boolean isAdded;
                            System.out.println("Reading sweets from file :");

                            try (BufferedReader br = new BufferedReader(new FileReader("sweets.txt"))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(",");
                                    if (data.length == 4) {
                                        idsweet = data[0];
                                        namesweet = data[1];
                                        typesweet = data[2];
                                        pricesweet = data[3];

                                        // Check if sweet already exists
                                        if (newSweet.isAdd(idsweet)) {
                                            System.out.println("Sweet with ID " + idsweet + " already exists.");
                                        } else {
                                            newSweet newSweetItem = new newSweet(idsweet, namesweet, typesweet, pricesweet);
                                            sweet.addsweet(newSweetItem);
                                            System.out.println("Added sweet: " + idsweet + ", " + namesweet + ", " + typesweet + ", " + pricesweet);
                                        }
                                    } else {
                                        System.out.println("Invalid data format in file.");
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Error reading file: " + e.getMessage());
                            }
                        } else {
                            System.out.println("You can't add sweet.");
                        }
                        /*for (newSweet s: sweet.getListOfSweet()){
                            System.out.println(newSweet.printsweet(s));
                        }*/
                        break;

                    case 2:
                        System.out.println("Deleting a sweet:");
                        // Code for deleting a sweet
                        break;

                    case 3:
                        System.out.println("Updating a sweet:");
                        // Code for updating a sweet
                        break;

                    case 4:
                        System.out.println("Searching for a sweet:");
                        // Code for searching a sweet
                        break;

                    case 5:
                        System.out.println("Ordering:");
                        // Code for ordering
                        break;

                    case 6:
                        System.out.println("Notification:");
                        // Code for notification
                        break;

                    case 7:
                        System.out.println("Exiting the program...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

        } while (menu != 7);
        scanner.close();
    }
}
