package tm;

public class StudentDetailTm {
    private String sid;
    private String lastname;
    private String address;
    private String email;
    private String contact;
    private String grade;

    public StudentDetailTm() {
    }

    public StudentDetailTm(String sid, String lastname, String address, String contact, String grade) {
        this.sid = sid;
        this.lastname = lastname;
        this.address = address;
        this.contact = contact;
        this.grade = grade;
    }

    public StudentDetailTm(String sid, String lastname, String address, String email, String contact, String grade) {
        this.sid = sid;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.grade = grade;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentDetailTm{" +
                "sid='" + sid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
