package tm;

public class TeacherTm {
    private String teacherId;
    private String teacherfirstname;
    private String teacherlastname;
    private String teacherNic;
    private String teachercontact;
    private String teacheraddress;
    private String teacherEmail;
    private String subjectName;

    public TeacherTm() {
    }

    public TeacherTm(String teacherId, String teacherfirstname, String teacherlastname, String teacherNic, String teachercontact, String teacheraddress, String teacherEmail, String subjectName) {
        this.teacherId = teacherId;
        this.teacherfirstname = teacherfirstname;
        this.teacherlastname = teacherlastname;
        this.teacherNic = teacherNic;
        this.teachercontact = teachercontact;
        this.teacheraddress = teacheraddress;
        this.teacherEmail = teacherEmail;
        this.subjectName = subjectName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherfirstname() {
        return teacherfirstname;
    }

    public void setTeacherfirstname(String teacherfirstname) {
        this.teacherfirstname = teacherfirstname;
    }

    public String getTeacherlastname() {
        return teacherlastname;
    }

    public void setTeacherlastname(String teacherlastname) {
        this.teacherlastname = teacherlastname;
    }

    public String getTeacherNic() {
        return teacherNic;
    }

    public void setTeacherNic(String teacherNic) {
        this.teacherNic = teacherNic;
    }

    public String getTeachercontact() {
        return teachercontact;
    }

    public void setTeachercontact(String teachercontact) {
        this.teachercontact = teachercontact;
    }

    public String getTeacheraddress() {
        return teacheraddress;
    }

    public void setTeacheraddress(String teacheraddress) {
        this.teacheraddress = teacheraddress;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "TeacherTm{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherfirstname='" + teacherfirstname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                ", teacherNic='" + teacherNic + '\'' +
                ", teachercontact='" + teachercontact + '\'' +
                ", teacheraddress='" + teacheraddress + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
