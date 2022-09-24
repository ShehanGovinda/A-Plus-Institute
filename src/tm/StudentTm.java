package tm;

public class StudentTm {
    private String sid;
    private String snamefname;
    private String lastname;
    private String dateofbirth;
    private String gender;
    private String grade;
    private String parentname;
    private String contact;
    private String address;
    private String email;

    public StudentTm() {
    }

    public StudentTm(String sid, String lastname, String grade, String contact, String address, String email) {
        this.sid = sid;
        this.lastname = lastname;
        this.grade = grade;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }

    public StudentTm(String sid, String snamefname, String lastname, String dateofbirth, String gender, String grade, String parentname, String contact, String address, String email) {
        this.sid = sid;
        this.snamefname = snamefname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.grade = grade;
        this.parentname = parentname;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSnamefname() {
        return snamefname;
    }

    public void setSnamefname(String snamefname) {
        this.snamefname = snamefname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public String toString() {
        return "StudentTm{" +
                "sid='" + sid + '\'' +
                ", snamefname='" + snamefname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", gender='" + gender + '\'' +
                ", grade='" + grade + '\'' +
                ", parentname='" + parentname + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
