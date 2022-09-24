package BusinessLogics;

import Db.DbConnection;
import Model.Employee;
import Model.Student;
import Model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeController {

    public boolean saveEmployee(Employee c) throws SQLException, ClassNotFoundException {

        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getEmpId());
        stm.setObject(2,c.getFirstName());
        stm.setObject(3,c.getLastName());
        stm.setObject(4,c.getNic());
        stm.setObject(5,c.getContact());
        stm.setObject(6,c.getEmail());
        stm.setObject(7,c.getAddress());
        stm.setObject(8,c.getSalary());
        return stm.executeUpdate()>0;
    }

    public String setEmployeeIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT empId FROM Employee ORDER BY empId DESC LIMIT 1").executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "E-00"+tempId;
            }else if(tempId<=99){
                return "E-0"+tempId;
            }else{
                return "E-"+tempId;
            }

        }else{
            return "E-001";
        }
    }
    public Employee getEmployee(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Employee WHERE Nic ='"+id+"'");


        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)

            );

        } else {
            return null;
        }
    }
    public boolean updateEmployee(Employee c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET empId=?, firstName=?, lastName=?, nic=?, contact=?, email=?, address=?, salary=? WHERE nic='"+c.getNic()+"'");
        stm.setObject(1,c.getEmpId());
        stm.setObject(2,c.getFirstName());
        stm.setObject(3,c.getLastName());
        stm.setObject(4,c.getNic());
        stm.setObject(5,c.getContact());
        stm.setObject(6,c.getEmail());
        stm.setObject(7,c.getAddress());
        stm.setObject(8,c.getSalary());
        return stm.executeUpdate()>0;

    }
    public boolean deleteEmployee(Employee e) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Employee` WHERE Nic='"+e.getNic()+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Employee> getAllEmployeeDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Employee`");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()) {
            employees.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)
            ));
        }
        return employees;
    }

    public static ArrayList<Employee> searchEmployee(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Employee` WHERE empId LIKE '%"+value+"%' || firstName LIKE '%"+value+"%' || lastName LIKE '%"+value+"%' || nic LIKE '%"+value+"%' || contact LIKE '%"+value+"%' || email LIKE '% "+value+"%' || address LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();


        ArrayList<Employee> employees = new ArrayList<>();

        while (rst.next()) {
            employees.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)

            ));
        }

        return employees;
    }
}
