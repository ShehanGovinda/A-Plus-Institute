package Controller;

import BusinessLogics.EmployeeController;
import BusinessLogics.StudentController;
import BusinessLogics.SubjectController;
import BusinessLogics.TeacherController;
import Db.DbConnection;
import Model.*;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdminInformationFormController {

    public AnchorPane AdminInformationForm;
    public JFXTextField txtSearchEmployee;
    public JFXTextField txtSearchStudent;
    public JFXTextField txtSearchTeacher;
    public JFXTextField txtSearchSubject;
    public Label lblTime;
    public Label lblDate;
    public TableView tblEmployeeTable;
    public TableView tblStudentDetails;
    public TableView tblTeacher;
    public TableView tblSubject;
    public TableColumn colSubjectName;
    public TableColumn colSubjectTeacherName;
    public TableColumn colTeacherId;
    public TableColumn colTeacherFirstName;
    public TableColumn colTeacherLastName;
    public TableColumn colTeacherNic;
    public TableColumn colTeacherContact;
    public TableColumn colTeacherAddress;
    public TableColumn colTeacherEmail;
    public TableColumn colTeacherSubjectName;
    public TableColumn colStudentid;
    public TableColumn colStudentFirstName;
    public TableColumn colStudentlastName;
    public TableColumn colStudentDateOfBirth;
    public TableColumn colStudentGender;
    public TableColumn colStudentGrade;
    public TableColumn colStudentParentName;
    public TableColumn colStudentContact;
    public TableColumn colStudentAddress;
    public TableColumn colStudentEmail;
    public TableColumn colEmployeeId;
    public TableColumn colEmployeeFirstName;
    public TableColumn colEmployeeLastName;
    public TableColumn coEmployeelNic;
    public TableColumn colEmployeeContact;
    public TableColumn colEmployeeEmail;
    public TableColumn colEmployeeAddress;
    public TableColumn colEmployeeSalary;
    public TableColumn colEmployeelNic;
    public TableColumn colTeacherfees;

    Stage stage = null;

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Subjects> subjects = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<StudentTm> tblTemp = new ArrayList();

    public void initialize() {
        loadDateAndTime();

        try {

            students = new StudentController().getAllStudentDetails();
            teachers = new TeacherController().getAllTeacherDetails();
            subjects = new SubjectController().getAllSubjectDetails();
            employees = new EmployeeController().getAllEmployeeDetails();
            setDataToStudents();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setDataToTeacher();
        setDataToSubject();
        setDataToEmployee();

        txtSearchStudent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchStudent(newValue);
            }
        });

        txtSearchTeacher.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchTeacher(newValue);
            }
        });

        txtSearchSubject.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchSubject(newValue);
            }
        });

        txtSearchEmployee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchEmployee(newValue);
            }
        });
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void setDataToEmployeeTable(ActionEvent actionEvent) {
        searchEmployee(txtSearchEmployee.getText());
    }

    public void setDataToStudentDetailTable(ActionEvent actionEvent) {
        searchStudent(txtSearchStudent.getText());
    }

    public void setDataToTeacherTable(ActionEvent actionEvent) {
        searchTeacher(txtSearchTeacher.getText());
    }

    public void setDataToSubjectTable(ActionEvent actionEvent) {
        searchSubject(txtSearchSubject.getText());
    }

    private void searchEmployee(String value) {
        try {
            //ArrayList<Teacher> teacher = new ArrayList<>();
            //ArrayList<StudentInformationTM> temp = new ArrayList<>();
            employees.clear();
            employees = EmployeeController.searchEmployee(value);


            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblEmployeeTable.getItems().setAll(employees);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchSubject(String value) {
        try {
            //ArrayList<Teacher> teacher = new ArrayList<>();
            //ArrayList<StudentInformationTM> temp = new ArrayList<>();
            subjects.clear();
            subjects = SubjectController.searchSubject(value);


            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblSubject.getItems().setAll(subjects);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchTeacher(String value) {
        try {
            //ArrayList<Teacher> teacher = new ArrayList<>();
            //ArrayList<StudentInformationTM> temp = new ArrayList<>();
            teachers.clear();
            teachers = TeacherController.searchTeacher(value);


            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblTeacher.getItems().setAll(teachers);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchStudent(String value) {
        try {
            ArrayList<Student> student = new ArrayList<>();
            ArrayList<StudentTm> temp = new ArrayList<>();
            temp.clear();
            student = StudentController.searchStudent(value);
            for (Student s : student) {
                for (StudentTm q : tblTemp) {
                    if (s.getSid().equals(q.getSid())) {
                        temp.add(q);
                    }
                }
            }

            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblStudentDetails.getItems().setAll(temp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToStudents() throws SQLException, ClassNotFoundException {

        for (Student s : students) {
            ArrayList<String> subjects = new ArrayList<>();
            String tempSubjects = "";
            //String tID = new StudentController().getTeacherIDFromSID(s.getStudentID());
            subjects = new StudentController().getSubjectsFromSID(s.getSid());
            for (String temp : subjects) {
                tempSubjects = temp + "/" + tempSubjects;
            }
            //System.out.println(tempSubjects);
            //tempSubjects = tempSubjects+"\b";
            StudentTm s1 = new StudentTm(s.getSid(), s.getSnamefname(), s.getLastname(), s.getDateofbirth(), s.getGender(), s.getGrade(), s.getParentname(), s.getContact(), s.getAddress(), s.getEmail());
            tblTemp.add(s1);
        }

        colStudentid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStudentFirstName.setCellValueFactory(new PropertyValueFactory<>("snamefname"));
        colStudentlastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colStudentDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        colStudentGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colStudentGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colStudentParentName.setCellValueFactory(new PropertyValueFactory<>("parentname"));
        colStudentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tblStudentDetails.setItems(FXCollections.observableArrayList(tblTemp));
    }

    private void setDataToTeacher() {

        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colTeacherFirstName.setCellValueFactory(new PropertyValueFactory<>("teacherfirstname"));
        colTeacherLastName.setCellValueFactory(new PropertyValueFactory<>("teacherlastname"));
        colTeacherNic.setCellValueFactory(new PropertyValueFactory<>("teacherNic"));
        colTeacherAddress.setCellValueFactory(new PropertyValueFactory<>("teacheraddress"));
        colTeacherContact.setCellValueFactory(new PropertyValueFactory<>("teachercontact"));
        colTeacherEmail.setCellValueFactory(new PropertyValueFactory<>("teacherEmail"));
        colTeacherSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colTeacherfees.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblTeacher.setItems(FXCollections.observableArrayList(teachers));
    }

    private void setDataToSubject() {

        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
        colSubjectTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherlastname"));


        tblSubject.setItems(FXCollections.observableArrayList(subjects));
    }

    private void setDataToEmployee() {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmployeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colEmployeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmployeelNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmployeeContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tblEmployeeTable.setItems(FXCollections.observableArrayList(employees));
    }


    public void goToDashBoardForm(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AdminInformationForm.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle(" A Plus Education Institute ");
        stage1.show();
        stage1.setResizable(false);
    }

    public void goToTeacher(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminTeacherForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminInformationForm.getChildren().clear();
        AdminInformationForm.getChildren().add(load);
    }

    public void goToEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminEmployeeSalaryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminInformationForm.getChildren().clear();
        AdminInformationForm.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminInformationForm.getChildren().clear();
        AdminInformationForm.getChildren().add(load);
    }

    public void goToSettings(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSettingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminInformationForm.getChildren().clear();
        AdminInformationForm.getChildren().add(load);
    }

    public void goToViewReport(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/Reports/Student_Details.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
