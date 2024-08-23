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

    public  static person retperson(person p){
        for (person pp : sweet.getList_of_people()) {
            if (pp.getEmail().equals(p.getEmail()) && pp.getPass().equals(p.getPass())) {
                return pp;
            }
        }
        return null;
    }
  

    public person(String email, String password, String type, String firstName, String lastName) {
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
    public static boolean findemail(person p){
        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(p.getEmail())) {
                return true;
            }
        }
        return false;
    }
    public static boolean ifpersonsignup(person pp) {
        for (person p : sweet.getList_of_people()) {
            if (pp.getEmail().equals(p.getEmail())) {
                if (!pp.getPass().equals(p.getPass())) {
                    sweet.is_loggin = false;
                }
                return true;
            }
        }
        return false;
    }
    public static boolean isRightType( String email, String pass, String ty){
        for( person p : sweet.getList_of_people()){
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
