package BusinessLogics;

import Db.DbConnection;
import Model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    public boolean saveRegister(Register r) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Register VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,r.getFirstName());
        stm.setObject(2,r.getLastName());
        stm.setObject(3,r.getNic());
        stm.setObject(4,r.getTelephone());
        stm.setObject(5,r.getUserType());
        stm.setObject(6,r.getUsername());
        stm.setObject(7,r.getPassword());
        return stm.executeUpdate()>0;
    }
}
