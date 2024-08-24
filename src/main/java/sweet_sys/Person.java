package sweet_sys;

public class Person {
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

    public Person(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public  static Person retperson(Person p){
        for (Person pp : Sweet.getPeopleList()) {
            if (pp.getEmail().equals(p.getEmail()) && pp.getPass().equals(p.getPass())) {
                return pp;
            }
        }
        return null;
    }
  

    public Person(String email, String password, String type, String firstName, String lastName) {
        this.email = email;
        this.pass = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
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
    }
    public static boolean findemail(Person p){
        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(p.getEmail())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isRightType( String email, String pass, String ty){
        for( Person p : Sweet.getPeopleList()){
            if(p.getType().equals(ty) && p.getPass().equals(pass) && p.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName=newLastName;
    }
}
