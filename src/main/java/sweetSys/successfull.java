package sweetSys;

import java.util.regex.Pattern;

public class successfull {
    private static final String SIMPLE_EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(SIMPLE_EMAIL_PATTERN);

    String password;
    String email;

    public successfull(String pass, String em) {
        this.password = pass;
        this.email = em;
    }

    public successfull() {
        this.password = password;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\p{Punct}])[A-Za-z\\d\\p{Punct}]+$";
        return password.length() >= 8 && password.matches(pattern);
    }

    public void addtoarray(String password, String email, String firstName, String finalName, String type) {
        sweet obj = new sweet();
        person b = new person(email, password, type, firstName, finalName);
        obj.setToList(b);
    }

    public String passwordStrength(String password) {
        if (password.length() < 8) {
            return "Password is too short";
        }
        if (!password.matches(".[A-Z].")) {
            return "Password should contain at least one uppercase letter";
        }
        if (!password.matches(".[a-z].")) {
            return "Password should contain at least one lowercase letter";
        }
        if (!password.matches(".\\d.")) {
            return "Password should contain at least one digit";
        }
        if (!password.matches(".[\\p{Punct}].")) {
            return "Password should contain at least one special character";
        }
        return "Password is strong";
    }
}