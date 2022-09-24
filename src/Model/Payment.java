package Model;

public class Payment {
    private String sid;
    private String cardNumber;
    private String subject;
    private String teacherlastname;
    private String paymentMonth;
    private Double amount;
    private String pDate;
    private String pTime;

    public Payment() {
    }

    public Payment(String sid, String cardNumber, String subject, String teacherlastname, String paymentMonth, Double amount, String pDate, String pTime) {
        this.sid = sid;
        this.cardNumber = cardNumber;
        this.subject = subject;
        this.teacherlastname = teacherlastname;
        this.paymentMonth = paymentMonth;
        this.amount = amount;
        this.pDate = pDate;
        this.pTime = pTime;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherlastname() {
        return teacherlastname;
    }

    public void setTeacherlastname(String teacherlastname) {
        this.teacherlastname = teacherlastname;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "sid='" + sid + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", subject='" + subject + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                ", paymentMonth='" + paymentMonth + '\'' +
                ", amount=" + amount +
                ", pDate='" + pDate + '\'' +
                ", pTime='" + pTime + '\'' +
                '}';
    }
}
