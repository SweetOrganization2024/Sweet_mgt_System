package Sweet;
import java.io.*;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import sweetSys.*;
import javax.mail.MessagingException;

public class Main {
    public static final String FILE_NAME = "sweets.txt";
    static NotificationService notificationService = new NotificationService();


    public static void main(String[] args) throws MessagingException {
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
        int choice;
        boolean isValid;
        boolean isexist;
        int userType;


        do {
            System.out.println("1. Sign UP ");
            System.out.println("2. Log In ");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1 -> {
                    boolean isAlreadyRegistered = false;
                    System.out.println("Enter your first name:");
                    firstName = scanner.nextLine();
                    System.out.println("Enter your last name:");
                    lastName = scanner.nextLine();
                    do {
                        System.out.println("Enter your email:");
                        email = scanner.nextLine();

                        if (!successfull.isValidEmail(email)) {
                            System.out.println("Invalid email syntax.");
                        } else if (person.emailexi(email)) {
                            System.out.println("Email is already registered.");
                            isAlreadyRegistered = true;
                        } else {
                            isAlreadyRegistered = false;
                        }
                    } while (!successfull.isValidEmail(email) || isAlreadyRegistered);
                    do {
                        System.out.println("Enter your password:");
                        password = readPassword();
                    } while (!successfull.isValidPassword(password));
                    do {
                        System.out.println("Confirm password:");
                        confirmPassword = readPassword();
                        isValid = password.equals(confirmPassword);
                    } while (!isValid);
                    System.out.println("You sign up as:");
                    System.out.println("1- USER");
                    System.out.println("2- ADMIN");
                    System.out.println("3- Supplier");
                    System.out.println("4- Owner");
                    userType = scanner.nextInt();
                    scanner.nextLine();

                    switch (userType) {
                        case 1 -> type = "USER";
                        case 2 -> type = "ADMIN";
                        case 3 -> type = "Supplier";
                        case 4 -> type = "Owner";
                        default -> {
                            System.out.println("Invalid user type.");
                            continue;
                        }
                    }

                    person newUser = new person(email, password, type, firstName, lastName);
                    sweet.getList_of_people().add(newUser);
                    System.out.println("Sign up successful!");
                    saveUserToFile(newUser, "userfile.txt");
                    notificationService.notifyAdminOfNewAccount("asmarsamia2003@gmail.com", firstName + lastName, email);
                    notificationService.notifyUserOfNewAccount(email, firstName);


                    if (type.equals("ADMIN")) {
                        AdminManager();
                    }
                    menu();
                }
                case 2 -> {
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
                                case 1 -> type = "USER";
                                case 2 -> type = "ADMIN";
                                case 3 -> type = "Supplier";
                                case 4 -> type = "Owner";
                                default -> System.out.println("Invalid user type.");
                            }
                        } while (!person.isRightType(email, password, type));

                        System.out.println("Login successful!");
                    }
                    if (type.equals("ADMIN")) {
                        AdminManager();
                    }
                    menu();
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }
public static void menu() {
    Scanner scanner = new Scanner(System.in);
    sweet.getInstance();
    new successfull();
    String email = null;
    String type = "";
    boolean is_add;
    String idsweet = null;
    String namesweet = null;
    String typesweet;
    String pricesweet = null;
    String minPrice;
    String maxPrice;
    int menu = 0;
    double totalCost;

    while (menu != 6) {
        System.out.println("Main Menu:");
        System.out.println("1. Add a new sweet");
        System.out.println("2. Delete a sweet");
        System.out.println("3. Update a sweet");
        System.out.println("4. Search a sweet");
        System.out.println("5. Order");
        System.out.println("6. Exit");
        System.out.print("Please enter your choice: ");

        menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1 -> {
                System.out.println("Adding a new sweet:");
                if (type.equals("Supplier") || type.equals("Owner") || type.equals("ADMIN")) {
                    System.out.println("Reading sweets from file :");
                    loadSweetsFromFile(FILE_NAME);
                    notificationService.notifyOwnerOfNewSweet(email, namesweet, idsweet, pricesweet);


                } else {
                    System.out.println("You can't add sweet.");
                }
                for (newSweet s : sweet.getListOfSweet()) {
                    System.out.println(newSweet.printsweet(s));
                }
            }
            case 2 -> {
                System.out.println("Deleting a sweet:");
                if (type.equals("Supplier") || type.equals("Owner") || type.equals("ADMIN")) {
                    sweet.getListOfSweet().clear();

                    loadSweetsFromFile(FILE_NAME);

                    if (!sweet.getListOfSweet().isEmpty()) {
                        do {
                            System.out.println("Enter the ID of the sweet you want to delete:");
                            idsweet = scanner.nextLine();
                            is_add = newSweet.isAdd(idsweet);
                            if (is_add) {
                                sweet.deletesweet1(idsweet);
                                System.out.println("Successful delete.");
                                saveSweetsToFile(sweet.getListOfSweet(), FILE_NAME);
                                notificationService.notifyOwnerOfDeletedSweet(email, idsweet);

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
                for (newSweet s : sweet.getListOfSweet()) {
                    System.out.println(newSweet.printsweet(s));
                }
            }
            case 3 -> {
                System.out.println("Updating a sweet:");
                if (type.equals("Supplier") || type.equals("Owner") || type.equals("ADMIN")) {
                    sweet.getListOfSweet().clear();

                    loadSweetsFromFile(FILE_NAME);
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
                    saveSweetsToFile(sweet.getListOfSweet(), FILE_NAME);

                    System.out.println("Update successful!");
                    notificationService.notifyOwnerOfUpdatedSweet(email, idsweet, newName);

                } else {
                    System.out.println("You don't have permission to update.");
                }
                for (newSweet s : sweet.getListOfSweet()) {
                    System.out.println(newSweet.printsweet(s));
                }
            }
            case 4 -> {
                sweet.getListOfSweet().clear();
                loadSweetsFromFile(FILE_NAME);
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
            }
            case 5 -> {
                loadSweetsFromFile(FILE_NAME);
                System.out.println("Ordering:");
                for (newSweet s : sweet.listOfSweet) {
                    System.out.println(newSweet.printsweet(s));
                }
                System.out.print("Enter the ID of sweet you want to order: ");
                String idd = scanner.nextLine();
                System.out.print("Enter the Name of sweet you want to order: ");
                String nameSweet = scanner.nextLine();
                System.out.print("Enter the type of this sweet: ");
                String typeSweet = scanner.nextLine();
                System.out.print("Enter the number of quantity you want to order: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                try {
                    String priceStr = sweet.getThePrice(nameSweet, typeSweet);
                    if (priceStr == null || priceStr.trim().isEmpty()) {
                        System.out.println("Price not available for the selected sweet.");
                        return;
                    }

                    double price = 0.0;
                    try {
                        price = Double.parseDouble(priceStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format: " + priceStr);
                        return;
                    }

                    totalCost = quantity * price;

                    for (newSweet sweetItem : sweet.getListOfSweet()) {
                        if (sweetItem.getId_of_sweet().equals(idd)) {
                            sweetItem.setSale(quantity);
                            System.out.println(sweetItem.getName_of_sweet() + " " + sweetItem.getSale());
                            break;
                        }
                    }

                    if (sweet.Search_name_Type(nameSweet, typeSweet)) {
                        int orderId = OrderManager.getInstance().getNextOrderId();
                        OrderManager.getInstance().addOrder(String.valueOf(orderId), LocalDate.now(), totalCost);

                        System.out.println("Order successful :)");
                        String total = Double.toString(totalCost);

                        notificationService.notifyUserOfOrder(email, idsweet, total);

                        System.out.println("Order ID: " + orderId + " on " + LocalDate.now() + " with total cost: " + totalCost);
                    } else {
                        System.out.println("This sweet does not exist.");
                    }
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
                System.out.println("Choose the way of payment: ");
                System.out.println("1 - Card number");
                System.out.println("2 - Cash");
                int paymentMethod = scanner.nextInt();
                scanner.nextLine();
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
                System.out.println("1 - Order");
                System.out.println("2 - Cancel this order");
                int action = scanner.nextInt();
                scanner.nextLine();
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
            }
            case 6 -> System.out.println("Exiting the program...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
    while (menu != 6);
}


    private static String readPassword() {
        Console console = System.console();
        if (console == null) {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } else {
            return new String(console.readPassword("Enter your password: "));
        }
    }

    public static void loadSweetsFromFile(String FILE_NAME) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String idsweet = data[0];
                    String namesweet = data[1];
                    String typesweet = data[2];
                    String pricesweet = data[3];

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
    }
    public static void saveSweetsToFile(List<newSweet> sweetsList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (newSweet sweetItem : sweetsList) {
                bw.write(sweetItem.getId_of_sweet() + "," + sweetItem.getName_of_sweet() + "," + sweetItem.getType_of_sweet() + "," + sweetItem.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveUserToFile(person user, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + "," + user.getPass() + "," + user.getType());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



    public static void AdminManager() {

        Scanner scanner = new Scanner(System.in);
        int admin;
        do {
            System.out.println("Admin Management Menu:");
            System.out.println("1. Manage user accounts");
            System.out.println("2. Generate financial reports");
            System.out.println("3. View best-selling products");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");

            admin = scanner.nextInt();
            scanner.nextLine();

            switch (admin) {
                case 1 -> {
                    System.out.println("Managing user accounts:");
                    manageUserAccounts();
                }
                case 2 -> {
                    System.out.println("Generating financial reports:");
                    FinancialReportGenerator.generateFinancialReports();
                }
                case 3 -> {
                    System.out.println("Viewing best-selling products:");
                    loadSweetsFromFile("sweets.txt");
                    sweet.getListOfSweet().sort((s1, s2) -> Integer.compare(s2.getSale(), s1.getSale()));
                    System.out.println("Best-selling products [From highest to lowest selling] :");
                    for (newSweet sweet : sweet.getListOfSweet()) {
                        System.out.println(newSweet.printsweet(sweet) + " Sales: " + sweet.getSale());
                    }
                }
                case 4 -> System.out.println("Exiting admin management. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (admin!=4);
    }
    public static void manageUserAccounts() {
        boolean keepManaging = true;
        Scanner scanner = new Scanner(System.in);
        int choi=0;
        while (keepManaging) {
            System.out.println("User Management Menu:");
            System.out.println("1. Add a new user");
            System.out.println("2. Delete a user");
            System.out.println("3. Update user information");
            System.out.println("4. View all users");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");
            choi = scanner.nextInt();


            switch (choi) {
                case 1 -> addUser();
                case 2 -> deleteUserFromFile();
                case 3 -> updateUser();
                case 4 -> viewAllUsers();
                case 5 -> {
                    keepManaging = false;
                    System.out.println("Exiting user management. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email of user to update: ");
        String email = scanner.nextLine();
        person userToUpdate = null;

        for (person p : sweet.getList_of_people()) {
            if (p.getEmail().equals(email)) {
                userToUpdate = p;
                break;
            }
        }

        if (userToUpdate != null) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Enter new type (USER/ADMIN/Supplier/Owner): ");
            String newType = scanner.nextLine();
            System.out.print("Enter new first name: ");
            String newFirstName = scanner.nextLine();
            System.out.print("Enter new last name: ");
            String newLastName = scanner.nextLine();

            userToUpdate.setPass(newPassword);
            userToUpdate.setType(newType);
            userToUpdate.setFirstName(newFirstName);
            userToUpdate.setLastName(newLastName);

            rewriteUserFile();
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void rewriteUserFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("userfile.txt"))) {
            for (person p : sweet.getList_of_people()) {
                writer.println(p.getFirstName() + "," + p.getLastName()  + "," + p.getEmail() + "," + p.getPass() + "," + p.getType());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while rewriting the user file.");
            e.printStackTrace();
        }
    }

    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter type (USER/ADMIN/Supplier/Owner): ");
        String type = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        if (person.emailexi(email)) {
            System.out.println("Email already exists. Please try again.");
            return;
        }

        person newPerson = new person(email, password, type, firstName, lastName);
        sweet.getList_of_people().add(newPerson);
        saveUserToFile(newPerson, "userfile.txt");
        System.out.println("User added successfully.");
    }

    public static void viewAllUsers() {
        if (sweet.getList_of_people().isEmpty()) {
            System.out.println("No users to display.");
            return;
        }

        for (person p : sweet.getList_of_people()) {
            System.out.println("Name: "+ p.getFirstName() + " "+ p.getLastName() + " Email: "+ p.getEmail() + " Password: "+p.getPass() + " Type: "+ p.getType());
        }
    }

    public static void deleteUserFromFile( ) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Pls Enter the email you want to delete:");
        String email=scanner.nextLine();
        File inputFile = new File("userfile.txt");
        File tempFile = new File("tempfile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5 && !data[2].equals(email)) {
                    writer.write(line);
                    writer.newLine();
                } else if (data.length == 5 && data[2].equals(email)) {
                    userFound = true;
                }
            }

            if (userFound) {
                System.out.println("User with email " + email + " has been deleted.");
            } else {
                System.out.println("User with email " + email + " not found.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temporary file.");
        }
    }



}