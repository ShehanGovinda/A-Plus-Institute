package BusinessLogics;

import Db.DbConnection;
import Model.Student;
import Model.Subject;
import Model.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectController {



    public boolean addSubject(Subjects c) throws SQLException, ClassNotFoundException {

        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Subjects VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getSid());
        stm.setObject(2,c.getLastname());
        stm.setObject(3,c.getGender());
        stm.setObject(4,c.getGrade());
        stm.setObject(5,c.getSubjectname());
        stm.setObject(6,c.getTeacherlastname());

        return stm.executeUpdate()>0;
    }

    public boolean updateSubject(Subjects c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Subjects SET sid=?, lastname=?, gender=?, grade=?, subjectName=?, TeacherName=? WHERE sid='"+c.getSid()+"'");
        stm.setObject(1,c.getSid());
        stm.setObject(2,c.getLastname());
        stm.setObject(3,c.getGender());
        stm.setObject(4,c.getGrade());
        stm.setObject(5,c.getSubjectname());
        stm.setObject(6,c.getTeacherlastname());

        return stm.executeUpdate()>0;
    }

    public Student getSubjects(String sid) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Student WHERE sid='"+sid+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Student(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(5),
                    rst.getString(6)


            );

        } else {
            return null;
        }
    }

    public ArrayList<String> getSubjectNames() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Teacher ");
        ResultSet rst = stm.executeQuery();
        ArrayList<String> subjectNames = new ArrayList<>();
        while (rst.next()) {
            subjectNames.add(rst.getString(8));
        }
        return subjectNames;
    }

    public ArrayList<Subjects> getAllSubjectDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Subjects`");
        ResultSet rst = stm.executeQuery();
        ArrayList<Subjects> subjects = new ArrayList<>();
        while (rst.next()) {
            subjects.add(new Subjects(
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return subjects;
    }

    public static ArrayList<Subjects> searchSubject(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Subjects` WHERE subjectname LIKE '%"+value+"%' || teacherlastname  LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();


        ArrayList<Subjects> subjects = new ArrayList<>();

        while (rst.next()) {
            subjects.add(new Subjects(
                    rst.getString(5),
                    rst.getString(6)
            ));
        }

        return subjects;
    }

    public int getAmountOfSubjects() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Teacher`");
        ResultSet rst = stm.executeQuery();
        int count=0;
        while (rst.next()) {
            count++;
        }
        return count;
    }

}
