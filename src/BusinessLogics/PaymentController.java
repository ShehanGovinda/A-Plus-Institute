package BusinessLogics;

import Db.DbConnection;
import Interface.PaymentInterface;
import Model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController implements PaymentInterface {


    public String getFeesFromTeacherName(String studentID, String subName, String payMonth) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Payment` WHERE sid='" + studentID + "' AND subject='" + subName + "' AND paymentMonth='" + payMonth + "'");
        ResultSet rst = stm.executeQuery();
        //System.out.println(rst.toString());
        boolean b = rst.next();
        if (b == true) {
            return "Paid";
        }
        return "Not Paid";
    }

    @Override
    public boolean savePaymentInfo(Payment u) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO `Payment` VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, u.getSid());
        stm.setObject(2, u.getCardNumber());
        stm.setObject(3, u.getSubject());
        stm.setObject(4, u.getTeacherlastname());
        stm.setObject(5, u.getPaymentMonth());
        stm.setObject(6, u.getAmount());
        stm.setObject(7, u.getpDate());
        stm.setObject(8, u.getpTime());

        return stm.executeUpdate() > 0;
    }
}

