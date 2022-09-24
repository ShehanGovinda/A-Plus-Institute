package Controller;

import BusinessLogics.StudentController;
import BusinessLogics.SubjectController;
import BusinessLogics.TeacherController;
import Model.Student;
import Model.StudentDetail;
import Model.Subject;
import Model.Subjects;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.StudentDetailTm;
import tm.StudentTm;
import tm.SubjectTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSubjectFormController {

    public JFXTextField txtStudentId;
    public JFXTextField txtLName;
    public JFXTextField txtGrade;
    public JFXTextField txtGender;
    public JFXComboBox<String> cmbSubjectName;
    public JFXComboBox<String> cmbTeacherName;
    public AnchorPane UserSubjectDashBoardForm;
    public TableColumn colTeacherName;
    public TableColumn colSubjectName;
    public TableColumn colGrade;
    public TableView tblDelete;
    public JFXTextField txtStudentId2;
    public TableColumn colStId;
    public TableColumn colLastName;
    public TableColumn colGender;
    public JFXTextField txtSearchStudent;
    public JFXComboBox<String> cmbSelectTeacher;

    public TableView<StudentDetailTm> tblStudentDetails;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableView<SubjectTm> tblSubjectDetails;
    public TableColumn colSubject;
    public TableColumn colTeacher;
    public JFXComboBox<String> cmbSelectedSubject;
    public TableColumn colGrades;

    int selectedRow = -1;
    ArrayList<SubjectTm> subjects = new ArrayList<>();
    ArrayList<StudentDetailTm> student = new ArrayList<>();
    ArrayList<Student> studentDetails = new ArrayList<>();
    ArrayList<String> subjectNames = new ArrayList<>();
    ArrayList<String> teacherNames = new ArrayList<>();


    public void initialize() throws SQLException, ClassNotFoundException {
        loadItemIds();
        loadItemId();

        studentDetails = new StudentController().getAllStudentDetails();
        setDataToStudentDetailTable();

        cmbSelectTeacher.setDisable(true);

        subjectNames = new SubjectController().getSubjectNames();
        cmbSelectedSubject.getItems().setAll(subjectNames);

        cmbSelectedSubject.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cmbSelectTeacher.getItems().clear();
            cmbSelectTeacher.setDisable(false);
            try {
//balanna ona
                teacherNames = new TeacherController().getTeacherNames(cmbSelectedSubject.getSelectionModel().getSelectedItem());
                //System.out.println(teacherNames.toString());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cmbSelectTeacher.getItems().setAll(teacherNames);
        });


        tblStudentDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRow = (int) newValue;
        });

        tblSubjectDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRow = (int) newValue;
        });

//
        txtSearchStudent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });


    }

    private void search(String value) {
        try {
            ArrayList<Student> studentTemp = new ArrayList<>();
            ArrayList<StudentDetailTm> temp = new ArrayList<>();
            temp.clear();
            //balannna ona
            studentTemp = StudentController.searchStudent(value);
            for (Student s : studentTemp) {
                for (StudentDetailTm q : student) {
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

    public void setDataToStudentDetailTable() throws SQLException, ClassNotFoundException {

        for (Student s : studentDetails) {
            ArrayList<String> subjects = new ArrayList<>();
            String tempSubjects = "";
            //String tID = new StudentController().getTeacherIDFromSID(s.getStudentID());
            subjects = new StudentController().getSubjectsFromSID(s.getSid());
            for (String temp : subjects) {
                tempSubjects = temp + "/" + tempSubjects;
            }
            //System.out.println(tempSubjects);
            StudentDetailTm s1 = new StudentDetailTm(s.getSid(), s.getLastname(), s.getAddress(), s.getContact(), s.getGrade());
            student.add(s1);
        }

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));

        tblStudentDetails.setItems(FXCollections.observableArrayList(student));

    }

    public void goToDashBord(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) UserSubjectDashBoardForm.getScene().getWindow();
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


    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }


    public void goToPaymentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }

    public void goToAttendanceForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserAttendanceForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }

    public void goToResultForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserResultForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }

    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }

    public void goToDashBoardTwoForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserDashBoardTwoForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserSubjectDashBoardForm.getChildren().clear();
        UserSubjectDashBoardForm.getChildren().add(load);
    }


    @FXML
    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new TeacherController().getAllItemDescriptions();
        cmbSelectedSubject.getItems().addAll(itemIds);
    }

    private void loadItemId() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new TeacherController().getAllItemDescription();
        cmbSelectTeacher.getItems().addAll(itemIds);
    }


    public void selectedIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtStudentId.getText();

        Student c1 = new StudentController().getStudents(studentId);
        Subjects s1 = new StudentController().getDetails(c1.getSid());
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Student is not Available").show();
        } else {
            setData(c1, s1);
        }
    }

    void setData(Student c, Subjects s1) {
        //cmbSubjectName.setValue(c.);
        txtStudentId.setText(c.getSid());
        txtLName.setText(c.getLastname());
        txtGender.setText(c.getGender());
        txtGrade.setText(c.getGrade());


    }


    public void deleteSubjectOnAction(ActionEvent actionEvent) {
        if (selectedRow == -1) {
            new Alert(Alert.AlertType.WARNING, "Please select a row and try again..!").show();
        } else {


            subjects.remove(selectedRow);

            colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));

            colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));

            tblSubjectDetails.setItems(FXCollections.observableArrayList(subjects));


        }


    }

    public void addSubjectOnAction(ActionEvent actionEvent) {
        if (!cmbSelectedSubject.getSelectionModel().isEmpty() && !cmbSelectTeacher.getSelectionModel().isEmpty()) {
            SubjectTm s = new SubjectTm(cmbSelectedSubject.getSelectionModel().getSelectedItem(), cmbSelectTeacher.getSelectionModel().getSelectedItem());
            int count = -1;
            for (SubjectTm n : subjects) {
                if (n.getSubject().equals(s.getSubject()) && n.getTeacher().equals(s.getTeacher())) {
                    count = 0;
                } else {
                }
            }

            if (count == -1) {
                subjects.add(s);
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Already Selected..!").show();
            }

            colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));

            tblSubjectDetails.setItems(FXCollections.observableArrayList(subjects));

        } else {
            new Alert(Alert.AlertType.WARNING, "Select Subject and Teacher And try again..!").show();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("A");
        ArrayList<Subjects> stuDetails = new ArrayList<>();
        System.out.println("b");
        StudentDetailTm temp = student.get(selectedRow);
        System.out.println("c");
        // ArrayList<Subjects> stuDetail = new ArrayList<>();
        // ArrayList<Student> studentDetails = new ArrayList<>();
        Student temps = studentDetails.get(selectedRow);
        String sID = temp.getSid();

//        StudentTm temp1 = subjects.get(selectedRow);
//        String gender = temp1.getSid(
        System.out.println("d");
        for (SubjectTm s : subjects) {
            System.out.println("e");
            String name = s.getSubject();
            System.out.println(name);
            System.out.println("f");

            //String tID = new TeacherController().getStudentGrade(name);
            System.out.println("g");
            //System.out.println(tID);
            Subjects subjects = new Subjects(sID, temp.getLastname(), temps.getGender(), temp.getGrade(), s.getSubject(), s.getTeacher());
            System.out.println("h");
            stuDetails.add(subjects);
            System.out.println("i");
            System.out.println(stuDetails.toString());
            System.out.println("j");
        }
        System.out.println("x");
//        if (new StudentController().deleteStudentFromStudentDetailTable(sID)) {
//            System.out.println("k");
//            new StudentController().setInfoToStudentDetail(stuDetails);
//
//            cmbSelectTeacher.getItems().clear();
//
//            cmbSelectedSubject.getItems().clear();
//
//            student.clear();
//
//            subjects.clear();
//
//            colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
//
//            colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
//
//            tblSubjectDetails.setItems(FXCollections.observableArrayList(subjects));
//
//            initialize();
//
//            new Alert(Alert.AlertType.CONFIRMATION, "Student Subject Details Updated Successfully..!").show();
//            System.out.println("u");
//        } else {}

            System.out.println("k");
            new StudentController().setInfoToStudentDetail(stuDetails);

            cmbSelectTeacher.getItems().clear();

            cmbSelectedSubject.getItems().clear();

            student.clear();

            subjects.clear();

            colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));

            colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));

            tblSubjectDetails.setItems(FXCollections.observableArrayList(subjects));

            initialize();

            new Alert(Alert.AlertType.CONFIRMATION, "Student Subject Details Updated Successfully..!").show();
            System.out.println("u");

        }

//        StudentDetailTm selectedItem = tblStudentDetails.getSelectionModel().getSelectedItem();
//        //SubjectTm item = tblSubjectDetails.getItems().add(StudentDetail);
//        if(selectedItem ==null){
//            System.out.println("Not found");
//        }else{
//            ObservableList<SubjectTm> items = tblSubjectDetails.getItems();
//            String subject=null;
//            String teacher=null;
//            for (int i = 1; i <items.size() ; i++) {
//                subject=items.get(i).getSubject()+",";
//                teacher=items.get(i).getTeacher()+",";
//            }
//            System.out.println(subject);
//            System.out.println(teacher);
//
//        }
//    }
}
