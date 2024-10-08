package sweet_sys;

import java.util.regex.Pattern;

public class Successfull {
private static final String SIMPLE_EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern pattern = Pattern.compile(SIMPLE_EMAIL_PATTERN);

    String password;
    String email;

    public Successfull(String pass, String em) {
        this.password = pass;
        this.email = em;
    }
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return pattern.matcher(email).matches();
    }
    public static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]+$";
        return password.length() >= 8 && password.matches(pattern);
    }

    public void addtoarray(String password, String email, String firstName, String finalName, String type) {
        Sweet obj = new Sweet();
        Person b = new Person(email, password, type, firstName, finalName);
        obj.setToList(b);
    }

}
