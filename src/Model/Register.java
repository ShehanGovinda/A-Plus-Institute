package Model;

public class Register {
    private String firstName;
    private String LastName;
    private String Nic;
    private String telephone;
    private String userType;
    private String username;
    private String password;

    public Register() {
    }

    public Register(String firstName, String lastName, String nic, String telephone, String userType, String username, String password) {
        this.firstName = firstName;
        LastName = lastName;
        Nic = nic;
        this.telephone = telephone;
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Nic='" + Nic + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userType='" + userType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

