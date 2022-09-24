package Model;

public class Attendance {
    private String sid;
    private String lastname;
    private String attendance;
    private String subjectname;
    private String teacherlastname;
    private String grade;
    private String aTime;
    private String aDate;

    public Attendance() {
    }

    public Attendance(String sid, String lastname, String attendance, String subjectname, String teacherlastname, String grade, String aTime, String aDate) {
        this.sid = sid;
        this.lastname = lastname;
        this.attendance = attendance;
        this.subjectname = subjectname;
        this.teacherlastname = teacherlastname;
        this.grade = grade;
        this.aTime = aTime;
        this.aDate = aDate;
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

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "sid='" + sid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", attendance='" + attendance + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                ", grade='" + grade + '\'' +
                ", aTime='" + aTime + '\'' +
                ", aDate='" + aDate + '\'' +
                '}';
    }
}
