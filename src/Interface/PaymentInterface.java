package Interface;

import Model.Payment;

import java.sql.SQLException;

public interface PaymentInterface {

    public boolean savePaymentInfo(Payment u) throws SQLException, ClassNotFoundException;
}
