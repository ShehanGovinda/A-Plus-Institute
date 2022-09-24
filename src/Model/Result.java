package Model;

public class Result {
    private String sid;
    private String lastname;
    private String subjectname;
    private String issuedDate;
    private String result;
    private String examName;
    private String examMonth;
    private String marks;

    public Result() {
    }

    public Result(String sid, String lastname, String subjectname, String issuedDate, String result, String examName, String examMonth, String marks) {
        this.sid = sid;
        this.lastname = lastname;
        this.subjectname = subjectname;
        this.issuedDate = issuedDate;
        this.result = result;
        this.examName = examName;
        this.examMonth = examMonth;
        this.marks = marks;
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

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamMonth() {
        return examMonth;
    }

    public void setExamMonth(String examMonth) {
        this.examMonth = examMonth;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Result{" +
                "sid='" + sid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", result='" + result + '\'' +
                ", examName='" + examName + '\'' +
                ", examMonth='" + examMonth + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }
}
