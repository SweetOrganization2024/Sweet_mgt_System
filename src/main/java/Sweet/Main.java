package Sweet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sweetSys.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sweet app = sweet.getInstance();
        System.out.println("\n\n**   Welcome to the Sweet Management System   **\n");
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean isAlreadyRegistered = false;
                    System.out.println("Enter your first name:");
                    firstName = scanner.nextLine();
                    System.out.println("Enter your last name:");
                    lastName = scanner.nextLine();

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
                        scanner.nextLine();

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
                                continue;
                        }

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
                            scanner.nextLine();

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
                                    continue;
                            }
                        } while (!person.isRightType(email, password, type));

                        System.out.println("Login successful!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

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
                scanner.nextLine();

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
                        for (newSweet s: sweet.getListOfSweet()){
                            System.out.println(newSweet.printsweet(s));
                        }
                        break;

                    case 2:
                        System.out.println("Deleting a sweet:");
                        if (type.equals("Supplier") || type.equals("Owner")) {
                            sweet.getListOfSweet().clear();

                            try (BufferedReader br = new BufferedReader(new FileReader("sweets.txt"))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(",");
                                    if (data.length == 4) {
                                        String fileSweetId = data[0];
                                         namesweet = data[1];
                                         typesweet = data[2];
                                         pricesweet = data[3];
                                        newSweet linee = new newSweet(fileSweetId, namesweet, typesweet, pricesweet);
                                        sweet.listOfSweet.add(linee);
                                    } else {
                                        System.out.println("Invalid data format in file.");
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Error reading file: " + e.getMessage());
                            }
                            if (!sweet.getListOfSweet().isEmpty()) {
                                do {
                                    System.out.println("Enter the ID of the sweet you want to delete:");
                                    idsweet = scanner.nextLine();
                                    is_add = newSweet.isAdd(idsweet);
                                    if (is_add) {
                                        sweet.deletesweet1(idsweet);
                                        System.out.println("Successful delete.");
                                        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sweets.txt"))) {
                                            for (newSweet sweetItem : sweet.getListOfSweet()) {
                                                if (!sweetItem.getId_of_sweet().equals(idsweet)) {
                                                    bw.write(sweetItem.getId_of_sweet() + "," + sweetItem.getName_of_sweet() + "," + sweetItem.getType_of_sweet() + "," + sweetItem.getPrice());
                                                    bw.newLine();
                                                }
                                            }
                                        } catch (IOException e) {
                                            System.out.println("Error writing to file: " + e.getMessage());
                                        }
                                    } else {
                                        System.out.println("Invalid ID.");
                                    }

                                } while (!is_add);
                            } else {
                                System.out.println("There are no sweets available to delete.");
                            }
                        } else {
                            System.out.println("You can't delete.");
                        }
                        for (newSweet s: sweet.getListOfSweet()){
                            System.out.println(newSweet.printsweet(s));
                        }
                        break;

                    case 3:
                        System.out.println("Updating a sweet:");
                        if (type.equals("Supplier") || type.equals("Owner")) {
                            sweet.getListOfSweet().clear();

                            try (BufferedReader br = new BufferedReader(new FileReader("sweets.txt"))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(",");
                                    if (data.length == 4) {
                                        String fileSweetId = data[0];
                                         namesweet = data[1];
                                         typesweet = data[2];
                                         pricesweet = data[3];

                                        sweet.listOfSweet.add(new newSweet(fileSweetId, namesweet, typesweet, pricesweet));
                                    } else {
                                        System.out.println("Invalid data format in file.");
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Error reading file: " + e.getMessage());
                            }

                            boolean isExist;

                            do {
                                System.out.println("Enter the ID of the sweet you want to update:");
                                idsweet = scanner.nextLine();
                                isExist = newSweet.isAdd(idsweet);
                                if (!isExist) {
                                    System.out.println("Invalid ID. Please try again.");
                                }
                            } while (!isExist);

                            System.out.println("Please enter your choice:");
                            System.out.println("1. Update name and type of sweet");
                            System.out.println("2. Update name of sweet");
                            System.out.println("3. Update type of sweet");
                            System.out.println("4. Update price of sweet");
                            int updateChoice = scanner.nextInt();
                            scanner.nextLine();

                            String newName = null;
                            String newType = null;
                            String newPrice = null;

                            switch (updateChoice) {
                                case 1:
                                    System.out.println("Enter new name of sweet:");
                                    newName = scanner.nextLine();
                                    System.out.println("Enter new type of sweet:");
                                    newType = scanner.nextLine();
                                    break;
                                case 2:
                                    System.out.println("Enter new name of sweet:");
                                    newName = scanner.nextLine();
                                    break;
                                case 3:
                                    System.out.println("Enter new type of sweet:");
                                    newType = scanner.nextLine();
                                    break;
                                case 4:
                                    System.out.println("Enter new price of sweet:");
                                    newPrice = scanner.nextLine();
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                                    return;
                            }
                            for (newSweet s : sweet.getListOfSweet()) {
                                if (s.getId_of_sweet().equals(idsweet)) {
                                    if (newName != null) s.setName_of_sweet(newName);
                                    if (newType != null) s.setType_of_sweet(newType);
                                    if (newPrice != null) s.setPrice(newPrice);
                                    break;
                                }
                            }
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("sweets.txt"))) {
                                for (newSweet sweetItem : sweet.getListOfSweet()) {
                                    bw.write(sweetItem.getId_of_sweet() + "," + sweetItem.getName_of_sweet() + "," + sweetItem.getType_of_sweet() + "," + sweetItem.getPrice());
                                    bw.newLine();
                                }
                            } catch (IOException e) {
                                System.out.println("Error writing to file: " + e.getMessage());
                            }

                            System.out.println("Update successful!");

                        } else {
                            System.out.println("You don't have permission to update.");
                        }
                        for (newSweet s: sweet.getListOfSweet()){
                            System.out.println(newSweet.printsweet(s));
                        }
                        break;


                    case 4:
                        System.out.println("Searching for a sweet:");
                        break;

                    case 5:
                        System.out.println("Ordering:");
                        break;

                    case 6:
                        System.out.println("Notification:");
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
