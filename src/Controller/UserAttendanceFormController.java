package Controller;

import BusinessLogics.AttendanceController;
import BusinessLogics.StudentController;
import BusinessLogics.SubjectController;
import BusinessLogics.TeacherController;
import Model.Attendance;
import Model.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.StudentDetailTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class UserAttendanceFormController {
    public AnchorPane UserAttendanceForm;
    public JFXTextField txtSearchStudent;
    public JFXButton btnMark;
    public ComboBox<String> cmbSubject;
    public ComboBox<String> cmbTeacher;
    public TableView tblStudentDetails;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colGrade;
    public JFXTextField txtSearchAttendance;
    public TableView tblAttendance;
    public TableColumn colStudentID2;
    public TableColumn colName1;
    public TableColumn colGrade1;
    public TableColumn colAttendence;
    public JFXDatePicker dtPicker;

    ArrayList<String> subjectNames = new ArrayList<>();
    ArrayList<String> teacherNames = new ArrayList<>();
    ArrayList<StudentDetailTm> students = new ArrayList<>();
    ArrayList<Student> studentDetails = new ArrayList<>();
    ArrayList<StudentDetailTm> temp = new ArrayList<>();
    ArrayList<Attendance> attendance = new ArrayList<>();
    int selectedRowInStudentTable = -1;
    int selectRowToDelete = -1;
    int attendCount = 0;
    int studentsCount = 0;

    public void initialize() {
        //  lblAttendCount.setText(String.valueOf(attendCount));
        dtPicker.setValue(LocalDate.now());
        cmbTeacher.setDisable(true);
        btnMark.setDisable(true);
        //System.out.println(dtPicker.getTypeSelector());

        try {
            studentDetails = new StudentController().getAllStudentDetails();
            //temp = new StudentController().getAllStudentDetails();
            subjectNames = new SubjectController().getSubjectNames();
            cmbSubject.getItems().setAll(subjectNames);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSubject.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cmbTeacher.getItems().clear();
            cmbTeacher.setDisable(false);
            btnMark.setDisable(true);

            try {
                teacherNames = new TeacherController().getTeacherNames(cmbSubject.getSelectionModel().getSelectedItem());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cmbTeacher.getItems().setAll(teacherNames);
        });

        cmbTeacher.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            btnMark.setDisable(false);
            //selectedRowInStudentTable=-1;
            try {
                attendance = new AttendanceController().getAttendanceSheet(cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem(),/*"09/23/2021"*/
                        String.valueOf(dtPicker.getValue()));
                attendCount = attendance.size();
                //lblAttendCount.setText(String.valueOf(attendCount));
                studentsCount = new StudentController().getStudentCount(cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem());
                //lblTotalStudents.setText(String.valueOf(studentsCount));
                setDataToAttendanceTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        setDataToStudentDetailTable();

        tblStudentDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRowInStudentTable = (int) newValue;
            //System.out.println(selectedRowInStudentTable);
        });

        tblAttendance.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectRowToDelete = (int) newValue;
        });

        txtSearchStudent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        txtSearchAttendance.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchAttendanceOfTable(newValue);
            }
        });
    }

    public void searchAttendanceOnAction(ActionEvent actionEvent) {
        searchAttendanceOfTable(txtSearchAttendance.getText());
    }

    private void searchAttendanceOfTable(String text) {
        try {
            ArrayList<Attendance> student = new ArrayList<>();
            //temp.clear();
            student = AttendanceController.searchStudent(text);


            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblAttendance.getItems().setAll(student);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void search(String value) {
        try {
            ArrayList<Student> student = new ArrayList<>();
            temp.clear();
            student = StudentController.searchStudent(value);
            for (Student s : student) {
                for (StudentDetailTm q : students) {
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

    public void setDataToStudentDetailTable() {

        for (Student s : studentDetails) {
            StudentDetailTm s1 = new StudentDetailTm(s.getSid(), s.getLastname(), s.getAddress(), s.getEmail(), s.getContact(), s.getGrade());
            students.add(s1);
            temp.add(s1);
        }

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));


        tblStudentDetails.setItems(FXCollections.observableArrayList(temp));

    }

    public void searchStudentOnAction(ActionEvent actionEvent) {
        search(txtSearchStudent.getText());
    }

    public void checkAttendanceInfo_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //System.out.println("A");
        if (!cmbSubject.getSelectionModel().isEmpty() && !cmbTeacher.getSelectionModel().isEmpty()) {
            attendance = new AttendanceController().getAttendanceSheet(cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem(),/*"09/23/2021"*/String.valueOf(dtPicker.getValue()));
            attendCount = attendance.size();
            //  lblAttendCount.setText(String.valueOf(attendCount));
            studentsCount = new StudentController().getStudentCount(cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem());
            //   lblTotalStudents.setText(String.valueOf(studentsCount));
            setDataToAttendanceTable();
        }
    }

    private void setDataToAttendanceTable() {
        colStudentID2.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colName1.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colGrade1.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colAttendence.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        tblAttendance.setItems(FXCollections.observableArrayList(attendance));
    }

    private int searchAttendance(StudentDetailTm t) {
        for (Attendance e : attendance) {
            if (e.getSid().equals(t.getSid())) {
                return 0;
            }
        }
        return -1;
    }

    public void markAttendanceOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        attendance.clear();
        attendance = new AttendanceController().getAttendanceSheet(cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem(), String.valueOf(dtPicker.getValue()));
        //System.out.println(selectedRowInStudentTable);
        //System.out.println(attendance.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = simpleDateFormat.format(new Date());
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        //System.out.println(dtPicker.getValue(LocalDate.now()));
        if (!cmbTeacher.getSelectionModel().getSelectedItem().isEmpty() && !cmbSubject.getSelectionModel().getSelectedItem().isEmpty()) {
            if (selectedRowInStudentTable == -1) {
                new Alert(Alert.AlertType.WARNING, "Please select a student and try again..!").show();
            } else {
                StudentDetailTm t = temp.get(selectedRowInStudentTable);
                int count = searchAttendance(t);

                if (!attendance.isEmpty()) {
                    //System.out.println(!attendance.isEmpty());
                    if (count == 0) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Already marked..!").show();
                    } else {
                        if (new StudentController().getRegisterOrNot(t.getSid(), cmbTeacher.getSelectionModel().getSelectedItem(), cmbSubject.getSelectionModel().getSelectedItem())) {
                            Attendance a = new Attendance(t.getSid(), t.getLastname(), "Present", cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem(), t.getGrade(), time, date);
                            if (new AttendanceController().saveAttendance(a)) {
                                attendCount++;
                                //lblAttendCount.setText(String.valueOf(attendCount));
                                attendance.add(a);
                                tblAttendance.setItems(FXCollections.observableArrayList(attendance));
                                //setDataToStudentDetailTable();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Error..!").show();
                            }
                        } else {
                            new Alert(Alert.AlertType.WARNING, "This student is not registered for this class..!").show();
                        }
                    }
                } else {
                    if (new StudentController().getRegisterOrNot(t.getSid(), cmbTeacher.getSelectionModel().getSelectedItem(), cmbSubject.getSelectionModel().getSelectedItem())) {
                        Attendance a = new Attendance(t.getSid(), t.getLastname(), "Present", cmbSubject.getSelectionModel().getSelectedItem(), cmbTeacher.getSelectionModel().getSelectedItem(), t.getGrade(), time, date);
                        if (new AttendanceController().saveAttendance(a)) {
                            attendCount++;
                            //lblAttendCount.setText(String.valueOf(attendCount));
                            attendance.add(a);
                            tblAttendance.setItems(FXCollections.observableArrayList(attendance));
                            //setDataToStudentDetailTable();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Error..!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "This student is not registered for this class..!").show();
                    }
                }
            }
        } else {
        }
    }

    public void deleteAttendanceOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectRowToDelete != -1) {
            Attendance a = attendance.get(selectRowToDelete);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Message");
            alert.setHeaderText("Message of Delete attendance of Students..");
            alert.setContentText("Do you want to Delete this attendance.?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null || option.get() == ButtonType.CANCEL) {


            } else if (option.get() == ButtonType.OK) {
                if (new AttendanceController().deleteAttendance(a)) {
                    attendCount++;
                    //lblAttendCount.setText(String.valueOf(attendCount));
                    attendance.remove(selectRowToDelete);
                    tblAttendance.setItems(FXCollections.observableArrayList(attendance));
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again..!").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select a row and try again").show();
        }
    }


    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToResultForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserResultForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToPaymentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToSubjectForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserSubjectForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToDashBoardForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserDashBoardTwoForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserAttendanceForm.getChildren().clear();
        UserAttendanceForm.getChildren().add(load);
    }

    public void goToDashBord(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) UserAttendanceForm.getScene().getWindow();
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


}
