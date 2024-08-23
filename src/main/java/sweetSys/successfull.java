package sweetSys;

import java.util.regex.Pattern;

public class Successfull {
    private static final String SIMPLE_EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(SIMPLE_EMAIL_PATTERN);

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]{8,}$";

    private final Sweet sweetService; 

    private String password;
    private String email;

    public Successfull(String password, String email, Sweet sweetService) {
        this.password = password;
        this.email = email;
        this.sweetService = sweetService;
    }

    public Successfull(Sweet sweetService) {
        this.sweetService = sweetService;
    }

    public static boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }

    public void addToArray(String password, String email, String firstName, String lastName, String type) {
        Person person = new Person(email, password, type, firstName, lastName);
        sweetService.setToList(person);
    }
}
