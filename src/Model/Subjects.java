package Model;

public class Subjects {
    private String sid;
    private String lastname;
    private String gender;
    private String grade;
    private String subjectname;
    private String teacherlastname;

    public Subjects() {
    }

    public Subjects(String subjectname, String teacherlastname) {
        this.subjectname = subjectname;
        this.teacherlastname = teacherlastname;
    }

    public Subjects(String sid, String lastname, String gender, String grade, String subjectname, String teacherlastname) {
        this.sid = sid;
        this.lastname = lastname;
        this.gender = gender;
        this.grade = grade;
        this.subjectname = subjectname;
        this.teacherlastname = teacherlastname;
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

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getTeacherlastname() {
        return teacherlastname;
    }

    public void setTeacherlastname(String teacherlastname) {
        this.teacherlastname = teacherlastname;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "sid='" + sid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", grade='" + grade + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                '}';
    }
}
