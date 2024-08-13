package Sweet;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sweetSys.*;

public class Main {
    private static LocalDate localdate;

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
        String minPrice   ;
        String maxPrice  ;
        int menu = 0;
        int quan = 0;
        double totalCost = 0.0;
        String orderid = "1";

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
                        password = readPassword();
                    } while (!user.isValidPassword(password));

                    do {
                        System.out.println("Confirm password:");
                        confirmPassword = readPassword();
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
                        sweet.getListOfSweet().clear();

                        // Read sweets from file
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

                        System.out.println("Searching for a sweet:");
                        System.out.println("1. Search based on ID of sweet");
                        System.out.println("2. Search based on name of sweet");
                        System.out.println("3. Search based on ID and name of sweet");
                        System.out.println("4. Search based on name and type of sweet");
                        System.out.println("5. Search based on ID, name, and type of sweet");
                        System.out.println("6. Search based on price of sweet");
                        System.out.println("7. Show all sweets");

                        int searchChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (searchChoice) {
                            case 1:
                                System.out.println("Please enter the ID of the sweet:");
                                idsweet = scanner.nextLine();
                                if (sweet.Search_ID(idsweet)) {
                                    sweet.print_SweetId(idsweet);
                                } else {
                                    System.out.println("Sweet with the given ID not found.");
                                }
                                break;
                            case 2:
                                System.out.println("Please enter the name of the sweet:");
                                namesweet = scanner.nextLine();
                                if (sweet.Search_name(namesweet)) {
                                    sweet.print_Sweetname(namesweet);
                                } else {
                                    System.out.println("Sweet with the given name not found.");
                                }
                                break;
                            case 3:
                                System.out.println("Please enter the ID of the sweet:");
                                idsweet = scanner.nextLine();
                                System.out.println("Please enter the name of the sweet:");
                                namesweet = scanner.nextLine();
                                if (sweet.Search_name_id(namesweet, idsweet)) {
                                    sweet.Print_name_id(idsweet, namesweet);
                                } else {
                                    System.out.println("Sweet with the given ID and name not found.");
                                }
                                break;
                            case 4:
                                System.out.println("Please enter the name of the sweet:");
                                namesweet = scanner.nextLine();
                                System.out.println("Please enter the type of the sweet:");
                                typesweet = scanner.nextLine();
                                if (sweet.Search_name_Type(namesweet, typesweet)) {
                                    sweet.Print_Type_name(namesweet, typesweet);
                                } else {
                                    System.out.println("Sweet with the given name and type not found.");
                                }
                                break;
                            case 5:
                                System.out.println("Please enter the ID of the sweet:");
                                idsweet = scanner.nextLine();
                                System.out.println("Please enter the name of the sweet:");
                                namesweet = scanner.nextLine();
                                System.out.println("Please enter the type of the sweet:");
                                typesweet = scanner.nextLine();
                                if (sweet.Secrch_all(namesweet, idsweet, typesweet)) {
                                    sweet.Print_name_id_type(idsweet, namesweet, typesweet);
                                } else {
                                    System.out.println("Sweet with the given ID, name, and type not found.");
                                }
                                break;
                            case 6:
                                System.out.println("Please enter the minimum price:");
                                minPrice = scanner.nextLine();
                                System.out.println("Please enter the maximum price:");
                                maxPrice = scanner.nextLine();
                                sweet.Pricemin_max(minPrice, maxPrice);
                                break;
                            case 7:
                                for (newSweet s : sweet.getListOfSweet()) {
                                    System.out.println("Name: " + s.getName_of_sweet() + ", ID: " + s.getId_of_sweet() + ", Type: " + s.getType_of_sweet() + ", Price: " + s.getPrice());
                                }
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                        break;

                    case 5:
                        // Read sweets from file and initialize the list
                        try (BufferedReader br = new BufferedReader(new FileReader("sweets.txt"))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                String[] data = line.split(",");
                                if (data.length == 4) {
                                    String fileSweetId = data[0];
                                    String nameSweet = data[1];
                                    String typeSweet = data[2];
                                    String priceSweet = data[3];

                                    sweet.listOfSweet.add(new newSweet(fileSweetId, nameSweet, typeSweet, priceSweet));
                                } else {
                                    System.out.println("Invalid data format in file.");
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }

                        // Ordering process
                        System.out.println("Ordering:");
                        System.out.print("Enter the Name of sweet you want to order: ");
                        String nameSweet = scanner.nextLine();
                        System.out.print("Enter the type of this sweet: ");
                        String typeSweet = scanner.nextLine();
                        System.out.print("Enter the number of quantity you want to order: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Clear the newline character from the buffer

                        try {
                            // Get the price of the sweet and calculate total cost
                            String priceStr = sweet.getThePrice(nameSweet, typeSweet);
                            double price = Double.parseDouble(priceStr);
                            totalCost = quantity * price;

                            if (sweet.Search_name_Type(nameSweet, typeSweet)) {
                                int orderId = OrderManager.getInstance().getNextOrderId(); // Use singleton instance
                                OrderManager.getInstance().addOrder(String.valueOf(orderId), LocalDate.now(), totalCost);

                                System.out.println("Success order :)");
                                System.out.println("Order ID: " + orderId + " on " + LocalDate.now() + " with total cost: " + totalCost);
                            } else {
                                System.out.println("This sweet does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing the price. Please ensure the price is a valid number.");
                        } catch (NullPointerException e) {
                            System.out.println("Error: The sweet could not be found or the price is not available.");
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }

                        // Payment method
                        System.out.println("Choose the way of payment: ");
                        System.out.println("1 - Card number");
                        System.out.println("2 - Cash");
                        int paymentMethod = scanner.nextInt();
                        scanner.nextLine(); // Clear the newline character from the buffer

                        switch (paymentMethod) {
                            case 1:
                                System.out.print("Enter the card number: ");
                                int cardNumber = scanner.nextInt();
                                scanner.nextLine();
                                break;
                            case 2:
                                System.out.println("Payment when delivered order");
                                break;
                            default:
                                System.out.println("Invalid payment method selected.");
                                break;
                        }

                        // Order action
                        System.out.println("1 - Order");
                        System.out.println("2 - Cancel this order");
                        int action = scanner.nextInt();
                        scanner.nextLine(); // Clear the newline character from the buffer

                        switch (action) {
                            case 1:
                                OrderManager.getInstance().getCurrentOrder();
                                break;
                            case 2:
                                System.out.print("Enter the Order ID to cancel: ");
                                String orderIdToCancel = scanner.nextLine();
                                OrderManager.getInstance().cancelOrder(orderIdToCancel);
                                OrderManager.getPrevOrderId();
                                break;
                            default:
                                System.out.println("Invalid action selected.");
                                break;
                        }
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
    private static String readPassword() {
        Console console = System.console();
        if (console == null) {
            // Console not available, use Scanner as fallback
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } else {
            // Read password with Console
            return new String(console.readPassword("Enter your password: "));
        }
    }
}

