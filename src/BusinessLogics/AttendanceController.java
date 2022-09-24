package BusinessLogics;

import Db.DbConnection;
import Model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceController {
    public ArrayList<Attendance> getAttendanceSheet(String subjectname, String tName, String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Attendence` WHERE SubjectName=? AND teacherName=? AND ADate=?");
        stm.setObject(1,subjectname);
        stm.setObject(2,tName);
        stm.setObject(3,date);
        ResultSet rst = stm.executeQuery();
        ArrayList<Attendance> attendances = new ArrayList<>();
        while (rst.next()) {
            attendances.add(new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return attendances;
    }

    public static ArrayList<Attendance> searchStudent(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Attendance` WHERE sid LIKE '%"+value+"%' || StudentName LIKE '%"+value+"%' || grade LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();


        ArrayList<Attendance> students = new ArrayList<>();

        while (rst.next()) {
            students.add(new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }

        return students;
    }

    public boolean saveAttendance(Attendance u) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Attendence` VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,u.getSid());
        stm.setObject(2,u.getLastname());
        stm.setObject(3,u.getAttendance());
        stm.setObject(4,u.getSubjectname());
        stm.setObject(5,u.getTeacherlastname());
        stm.setObject(6,u.getGrade());
        stm.setObject(7,u.getaTime());
        stm.setObject(8,u.getaDate());


        return stm.executeUpdate()>0;
    }
    public boolean deleteAttendance(Attendance s) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Attendance` WHERE StudentId='"+s.getSid()+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
}
