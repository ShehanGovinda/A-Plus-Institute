package BusinessLogics;

import Db.DbConnection;
import Model.Student;
import Model.Teacher;
import javafx.scene.control.Label;
import tm.TeacherTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Teacher").executeQuery();
        List<String> descriptions= new ArrayList<>();
        while (rst.next()){
            descriptions.add(
                    rst.getString(8)
            );
        }
        return descriptions;
    }

    public List<String> getAllItemDescription() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Teacher").executeQuery();
        List<String> descriptions= new ArrayList<>();
        while (rst.next()){
            descriptions.add(
                    rst.getString(3)
            );
        }
        return descriptions;
    }

    public boolean saveTeacher(Teacher c) throws SQLException, ClassNotFoundException {

        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Teacher VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getTeacherId());
        stm.setObject(2,c.getTeacherfirstname());
        stm.setObject(3,c.getTeacherlastname());
        stm.setObject(4,c.getTeacherNic());
        stm.setObject(5,c.getTeachercontact());
        stm.setObject(6,c.getTeacheraddress());
        stm.setObject(7,c.getTeacherEmail());
        stm.setObject(8,c.getSubjectName());
        stm.setObject(9,c.getFee());


        return stm.executeUpdate()>0;
    }


    public String setTeacherIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT teacherId FROM Teacher ORDER BY teacherId DESC LIMIT 1").executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "T-00"+tempId;
            }else if(tempId<=99){
                return "T-0"+tempId;
            }else{
                return "T-"+tempId;
            }

        }else{
            return "T-001";
        }
    }

    public Teacher getTeacher(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Teacher WHERE TeacherNic ='"+id+"'");


        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );

        } else {
            return null;
        }
    }

    public boolean updateTeacher(Teacher c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Teacher SET teacherId=?, teacherfirstname=?, teacherlastname=?, teacherNic=?, teachercontact=?, teacheraddress=?, teacherEmail=?, subjectName=?,fee=? WHERE teacherNic='"+c.getTeacherNic()+"'");
        stm.setObject(1,c.getTeacherId());
        stm.setObject(2,c.getTeacherfirstname());
        stm.setObject(3,c.getTeacherlastname());
        stm.setObject(4,c.getTeacherNic());
        stm.setObject(5,c.getTeachercontact());
        stm.setObject(6,c.getTeacheraddress());
        stm.setObject(7,c.getTeacherEmail());
        stm.setObject(8,c.getSubjectName());
        stm.setObject(9,c.getFee());

        return stm.executeUpdate()>0;

    }


    public boolean deleteTeacher(Teacher e) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Teacher` WHERE TeacherId='"+e.getTeacherId()+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }



    public String getTeacherName(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher` WHERE teacherlastname='"+name+"'");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            return rst.getString(3);
        }
        return null;
    }

    public ArrayList<String> getTeacherNames(String subName) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher` WHERE subjectName='"+subName+"'");
        ResultSet rst = stm.executeQuery();
        ArrayList<String> teacherName = new ArrayList<>();
        while (rst.next()) {
            teacherName.add(rst.getString(2));
        }
        return teacherName;
    }
    public ArrayList<Teacher> getAllTeacherDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher`");
        ResultSet rst = stm.executeQuery();
        ArrayList<Teacher> teachers = new ArrayList<>();
        while (rst.next()) {
            teachers.add(new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return teachers;
    }

    public static ArrayList<Teacher> searchTeacher(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher` WHERE teacherId LIKE '%"+value+"%' || teacherfirstname LIKE '%"+value+"%' || teacherlastname LIKE '%"+value+"%' || teacherNic LIKE '%"+value+"%' || teachercontact LIKE '%"+value+"%' || teacheraddress LIKE '%"+value+"%' || teacherEmail LIKE '%"+value+"%' || subjectName LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();


        ArrayList<Teacher> teachers = new ArrayList<>();

        while (rst.next()) {
            teachers.add(new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)

            ));
        }

        return teachers;
    }

    public String getFeesFromTeacherName(String tName) throws SQLException, ClassNotFoundException {
        //shot circuite or ekedi teacherfirst name eka hari teacher last name eka hari deken ekak hari nam ganna
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher` WHERE Teacherfirstname ='"+tName+"' || teacherlastname ='"+tName+"'");
        ResultSet rst = stm.executeQuery();
        System.out.println(rst.toString());
        while (rst.next()) {
            System.out.println(rst.getString(9));
            return rst.getString(9);
            //System.out.println(rst.getString(9));
        }

        return null;
    }

    public String getStudentGrade(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student WHERE gender = '"+name+"'");
        ResultSet rst = stm.executeQuery();
        System.out.println(rst.toString());
        while (rst.next()) {
            return rst.getString(5);
        }
        return null;
    }

    public ArrayList<Teacher> getDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Teacher");
        ResultSet rst = stm.executeQuery();
        ArrayList<Teacher> teachers = new ArrayList<>();
        while (rst.next()) {
            teachers.add(new Teacher(
                    rst.getString(3),
                    rst.getString(8)
            ));
        }
        return teachers;
    }

    public int getAmountOfTeachers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher`");
        ResultSet rst = stm.executeQuery();
        int count=0;
        while (rst.next()) {
            count++;
        }
        return count;
    }

}
