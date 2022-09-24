package tm;

public class PaymentInfoTM {
    private String subjectname;
    private String teacherlastname;
    private String fee;
    private String condition;

    public PaymentInfoTM() {
    }

    public PaymentInfoTM(String subjectname, String teacherlastname, String fee, String condition) {
        this.subjectname = subjectname;
        this.teacherlastname = teacherlastname;
        this.fee = fee;
        this.condition = condition;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "PaymentInfoTM{" +
                "subjectname='" + subjectname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                ", fee='" + fee + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
