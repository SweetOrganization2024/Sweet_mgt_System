package sweetSys;

import java.util.regex.Pattern;

public class successfull {
    private static final String String SIMPLE_EMAIL_PATTERN ="^[a-zA-Z0-9_+&*-]{1,64}(?:\\.[a-zA-Z0-9_+&*-]{1,64})*" +"@[a-zA-Z0-9-]{1,63}(?:\\.[a-zA-Z0-9-]{1,63}){1,3}$";
    private static final Pattern pattern = Pattern.compile(SIMPLE_EMAIL_PATTERN);

    String password;
    String email;

    public successfull(String pass, String em) {
        this.password = pass;
        this.email = em;
    }

    public successfull() {
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
        sweet obj = new sweet();
        person b = new person(email, password, type, firstName, finalName);
        obj.setToList(b);
    }

}
