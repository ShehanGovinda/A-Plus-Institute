package tm;

public class SubjectTm {
    private String subject;
    private String teacher;

    public SubjectTm() {
    }

    public SubjectTm(String subject, String teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "SubjectTm{" +
                "subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
