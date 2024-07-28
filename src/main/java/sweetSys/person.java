package sweetSys;

public class person {
    String email;
    String pass;
    String type;
    String lastName;
    String firstName;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public person(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public person() {
    }

    public person(String email, String password, String type, String firstName, String lastName) {
        this.email = email;
        this.pass = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "person{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }}