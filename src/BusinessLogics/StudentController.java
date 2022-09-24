package BusinessLogics;

import Db.DbConnection;
import Model.Employee;
import Model.Student;
import Model.Subjects;
import Model.Teacher;
import tm.SubjectTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {

    public String setCustomerIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT sid FROM Student ORDER BY sid DESC LIMIT 1").executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "S-00" + tempId;
            } else if (tempId <= 99) {
                return "S-0" + tempId;
            } else {
                return "S-" + tempId;
            }

        } else {
            return "S-001";
        }
    }

    public boolean saveStudent(Student c) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, c.getSid());
        stm.setObject(2, c.getSnamefname());
        stm.setObject(3, c.getLastname());
        stm.setObject(4, c.getDateofbirth());
        stm.setObject(5, c.getGender());
        stm.setObject(6, c.getGrade());
        stm.setObject(7, c.getParentname());
        stm.setObject(8, c.getContact());
        stm.setObject(9, c.getAddress());
        stm.setObject(10, c.getEmail());
        stm.setObject(11, c.getRegisterfee());
        return stm.executeUpdate() > 0;
    }

    public Student getStudent(String sid) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Student WHERE sid='" + sid + "'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            );

        } else {
            return null;
        }
    }

    public boolean updateStudent(Student c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Student SET sid=?, snamefname=?, lastname=?, dateofbirth=?, gender=?, grade=?, parentname=?, contact=? , address=?,email=? WHERE sid='" + c.getSid() + "'");
        stm.setObject(1, c.getSid());
        stm.setObject(2, c.getSnamefname());
        stm.setObject(3, c.getLastname());
        stm.setObject(4, c.getDateofbirth());
        stm.setObject(5, c.getGender());
        stm.setObject(6, c.getGrade());
        stm.setObject(7, c.getParentname());
        stm.setObject(8, c.getContact());
        stm.setObject(9, c.getAddress());
        stm.setObject(10, c.getEmail());

        return stm.executeUpdate() > 0;
    }

    public boolean deleteStudent(Student e) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Student` WHERE sid='" + e.getSid() + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Student getStudents(String sid) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Student WHERE sid='" + sid + "'");

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

    public Subjects getDetails(String sid) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Subjects WHERE sid='" + sid + "'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Subjects(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );

        } else {
            return null;
        }
    }

    public boolean deleteStudentFromStudentDetailTable(String studentId) throws SQLException, ClassNotFoundException {
        System.out.println(studentId);
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Student` WHERE sid='"+studentId+"'").executeUpdate() > 0) {
            System.out.println(studentId);
            return true;
        } else {
            System.out.println("ss");
            return false;
        }

    }

    public void setInfoToStudentDetail(ArrayList<Subjects> Subjects) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO `Subjects` VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        for (Subjects c : Subjects) {
            stm.setObject(1, c.getSid());
            stm.setObject(2, c.getLastname());
            stm.setObject(3, c.getGender());
            stm.setObject(4, c.getGrade());
            stm.setObject(5, c.getSubjectname());
            stm.setObject(6, c.getTeacherlastname());
             stm.executeUpdate();
        }

    }

    public ArrayList<Student> getAllStudentDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Student`");
        ResultSet rst = stm.executeQuery();
        ArrayList<Student> students = new ArrayList<>();
        while (rst.next()) {
            students.add(new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11)
            ));
        }
        return students;
    }

    public ArrayList<String> getSubjectsFromSID(String sID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Subjects` WHERE sid='"+sID+"'");
        ResultSet rst = stm.executeQuery();
        ArrayList<String> subjects = new ArrayList<>();
        while (rst.next()) {
            subjects.add(rst.getString(5));
        }
        return subjects;
    }

    public static ArrayList<Student> searchStudent(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Student` WHERE sid LIKE '%"+value+"%' || lastname LIKE '%"+value+"%' || address LIKE '%"+value+"%' || contact LIKE '%"+value+"%' || grade LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();


        ArrayList<Student> students = new ArrayList<>();

        while (rst.next()) {
            students.add(new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }

        return students;
    }

    public ArrayList<SubjectTm> getSubjectDetailsFromSID(String sID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Subjects` WHERE sid='"+sID+"'");
        ResultSet rst = stm.executeQuery();
        ArrayList<SubjectTm> subjects = new ArrayList<>();
        while (rst.next()) {

                    subjects.add(new SubjectTm(
                            rst.getString(5),rst.getString(6)

                            )


                    );
        }
        return subjects;
    }

    public int getAmountOfStudent() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student");
        ResultSet rst = stm.executeQuery();
        int count = 0;
        while (rst.next()) {
            count++;
        }
        return count;
    }

    public int getStudentCount(String subName,String tName) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `subjects` WHERE TeacherName ='"+tName+"' AND subjectname='"+subName+"'");
        ResultSet rst = stm.executeQuery();
        int count = 0;
        while (rst.next()) {
            count++;
        }
        return count;
    }

    public boolean getRegisterOrNot(String sID,String tName,String subName) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `subjects` WHERE sid='"+sID+"' AND TeacherName='"+tName+"' AND subjectName='"+subName+"'");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            return true;
        }
        return false;
    }

//    public void  getAmountOfStudent() throws SQLException, ClassNotFoundException {
//        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM Teacher");
//        ResultSet rst = stm.executeQuery();
//        //int count = 0;
//       // ArrayList<Teacher> details = new ArrayList<>();
//        while (rst.next()) {
////            //subjects.add(new Subjects(
////            rst.getString(5)
////            //count++;
////            //));
//        }
////        return rst;
//    }



}
