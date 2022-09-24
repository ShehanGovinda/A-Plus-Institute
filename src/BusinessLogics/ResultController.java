package BusinessLogics;

import Db.DbConnection;
import Model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultController {
    public boolean setInfo(Result u) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Mark VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,u.getSid());
        stm.setObject(2,u.getLastname());
        stm.setObject(3,u.getSubjectname());
        stm.setObject(4,u.getIssuedDate());
        stm.setObject(5,u.getResult());
        stm.setObject(6,u.getExamName());
        stm.setObject(7,u.getExamMonth());
        stm.setObject(8,u.getMarks());

        return stm.executeUpdate()>0;
    }
    public boolean searchResults(String sID,String month,String subject,String examName) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Mark` WHERE sid='"+sID+"' AND examMonth='"+month+"' AND subjectname='"+subject+"' AND examName='"+examName+"'");
        ResultSet rst = stm.executeQuery();
        boolean b = rst.next();
        //ArrayList<Result> results = new ArrayList<>();
        if(b == true){
            return true;
        }
        return false;
    }

    public ArrayList<String> getExamNames() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Mark");
        ResultSet rst = stm.executeQuery();
        ArrayList<String> examNames = new ArrayList<>();
        while (rst.next()) {
            System.out.println();
            examNames.add(new String(
                    rst.getString(5)
            ));
        }
        return examNames;
    }
}
