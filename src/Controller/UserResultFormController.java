package Controller;

import BusinessLogics.ResultController;
import BusinessLogics.StudentController;
import Model.Result;
import Model.Student;
import Utill.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.StudentDetailTm;
import tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.regex.Pattern;

public class UserResultFormController {
    public AnchorPane UserResultForm;
    public Label lblGrade;
    public JFXTextField txtMarks;
    public Label lblStudentID;
    public JFXComboBox<String> cmbSubject;
    public JFXTextField txtSearchStudent;
    public JFXComboBox<String> cmbExamMonth;
    public TableView tblStudentDetails;
    public TableColumn colStudentName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colStudentId;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colGrade;
    public JFXComboBox<String> cmbExamNames;
    public JFXTextField txtExamName;
    public JFXButton btnSave;


    ArrayList<Student> studentDetails = new ArrayList<>();
    ArrayList<StudentDetailTm> students = new ArrayList<>();
    ArrayList<StudentDetailTm> temp = new ArrayList<>();
    ArrayList<String> examNames = new ArrayList<>();

    int selectedRow = -1;
    StudentDetailTm s;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern enterMarksPattern = Pattern.compile("^[0-9]{0,3}$");
    Pattern examNamePattern = Pattern.compile("^[0-9 A-z._-a-z ]{2,}$");

    public void initialize(){
        btnSave.setDisable(true);
        storeValidations();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date date = new Date();
        String month = formatter.format(date);
        cmbExamMonth.setValue(checkMonth(month));

        try {

            studentDetails = new StudentController().getAllStudentDetails();
            examNames = new ResultController().getExamNames();
            setExamNames();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbExamMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

        setDataToStudentDetailTable();

        txtSearchStudent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        tblStudentDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRow = (int) newValue;
            if(selectedRow == -1){

            }else {
                s = temp.get(selectedRow);
                lblStudentID.setText(s.getSid());
                try {
                    ArrayList<String> sub = new StudentController().getSubjectsFromSID(s.getSid());
                    cmbSubject.getItems().setAll(sub);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void storeValidations() {
        map.put(txtMarks, enterMarksPattern);
        map.put(txtExamName, examNamePattern);
    }

    public void jfxTextField_Key_Release1(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXTextFields(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void openResultReportForm(ActionEvent actionEvent) {
    }

    private void setExamNames() throws SQLException, ClassNotFoundException {
        examNames = new ResultController().getExamNames();
        System.out.println(examNames.toString());
        ArrayList<String> tempArray = new ArrayList<>();
        for(String e : examNames){
            int count=-1;
            if(tempArray.isEmpty()){tempArray.add(e);}
            else {
                for (int i = 0; i < tempArray.size(); i++) {
                    if (e.equals(tempArray.get(i))) {
                        count = 0;
                    }
                }
                if(count==-1){
                    tempArray.add(e);
                }
            }
        }
        cmbExamNames.getItems().addAll(tempArray);
    }

    private void checkGrade(int marks){
        if(marks >= 75 && marks<=100){
            lblGrade.setText("A");
            lblGrade.setStyle("-fx-text-fill: green");
        }else if(marks >= 65 && marks<=75){
            lblGrade.setText("B");
            lblGrade.setStyle("-fx-text-fill: darkgreen");
        }else if(marks >= 55 && marks<=65){
            lblGrade.setText("C");
            lblGrade.setStyle("-fx-text-fill: darkblue");
        }else if(marks >= 35 && marks<=55){
            lblGrade.setText("S");
            lblGrade.setStyle("-fx-text-fill: darkgoldenrod");
        }else if(marks >= 0 && marks<=35){
            lblGrade.setText("F");
            lblGrade.setStyle("-fx-text-fill: darkred");
        }else{
            lblGrade.setText(" ");
            new Alert(Alert.AlertType.WARNING,"Incorrect marks..!").show();
        }
    }

    private String checkMonth(String month){
        if(month.equals("01")){
            return "January";
        }else if(month.equals("02")){
            return "February";
        }else if(month.equals("03")){
            return "March";
        }else if(month.equals("04")){
            return "April";
        }else if(month.equals("05")){
            return "May";
        }else if(month.equals("06")){
            return "June";
        }else if(month.equals("07")){
            return "July";
        }else if(month.equals("08")){
            return "August";
        }else if(month.equals("09")){
            return "September";
        }else if(month.equals("10")){
            return "October";
        }else if(month.equals("11")){
            return "November";
        }else if(month.equals("12")){
            return "December";
        }
        return null;
    }

    private void search(String value){
        try {
            ArrayList<Student> student = new ArrayList<>();
            temp.clear();
            student = StudentController.searchStudent(value);
            for(Student s : student){
                for(StudentDetailTm q : students){
                    if(s.getSid().equals(q.getSid())){
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

    public void setDataToStudentDetailTable(){

        for(Student s : studentDetails){
            StudentDetailTm s1 = new StudentDetailTm(s.getSid(),s.getLastname(),s.getAddress(),s.getEmail(),s.getContact(),s.getGrade());
            students.add(s1);
            temp.add(s1);
        }

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        tblStudentDetails.setItems(FXCollections.observableArrayList(temp));

    }
    private boolean searchStudentMarks(String sID,String examName) throws SQLException, ClassNotFoundException {
        boolean b = new ResultController().searchResults(sID,cmbExamMonth.getSelectionModel().getSelectedItem(),cmbSubject.getSelectionModel().getSelectedItem(),examName);
        return b;
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        if(selectedRow!=-1 && !cmbExamMonth.getSelectionModel().isEmpty() && !cmbSubject.getSelectionModel().isEmpty() && !txtMarks.getText().equals("")) {
            if(!txtExamName.getText().equals("")) {
                Result result = new Result(lblStudentID.getText(), s.getLastname(), cmbSubject.getSelectionModel().getSelectedItem(), date, lblGrade.getText(),txtExamName.getText(),cmbExamMonth.getSelectionModel().getSelectedItem(),txtMarks.getText());

                //Result result = new Result(lblStudentID.getText(), s.getLastname(), cmbSubject.getSelectionModel().getSelectedItem(), date,  cmbExamNames.getSelectionModel().getSelectedItem(),txtExamName.getText(), cmbExamMonth.getSelectionModel().getSelectedItem(), txtMarks.getText());


                //=========================================================================================================================
                if (searchStudentMarks(lblStudentID.getText(), txtExamName.getText())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Results already added..!").show();
                } else {
                //METHANA SEARCH EKAK KARANNA THIYANAWA

                //KALIN MARKS ADD KARALANAM AYETH ADD KARANNA DENNA BAA
                //=========================================================================================================================
                    if (new ResultController().setInfo(result)) {
                         setExamNames();
                        txtMarks.clear();
                        cmbSubject.getItems().clear();
                        lblGrade.setText(" ");
                        lblStudentID.setText(" ");
                        new Alert(Alert.AlertType.CONFIRMATION, "Result saved successfully..!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Error..!").show();
                    }
                }
            }else if (!cmbExamNames.getSelectionModel().isEmpty()) {
                Result result = new Result(lblStudentID.getText(), s.getLastname(), cmbSubject.getSelectionModel().getSelectedItem(), date, cmbExamNames.getSelectionModel().getSelectedItem() ,lblGrade.getText(),cmbExamMonth.getSelectionModel().getSelectedItem(),txtMarks.getText());

                // Result result = new Result(lblStudentID.getText(), s.getLastname(), cmbSubject.getSelectionModel().getSelectedItem(), date,  cmbExamNames.getSelectionModel().getSelectedItem(),txtExamName.getText(), cmbExamMonth.getSelectionModel().getSelectedItem(), txtMarks.getText());


                //=========================================================================================================================
                if (searchStudentMarks(lblStudentID.getText(), cmbExamNames.getSelectionModel().getSelectedItem())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Results already added..!").show();
                } else {
                    if (new ResultController().setInfo(result)) {
                        setExamNames();
                        txtMarks.clear();
                        cmbSubject.getItems().clear();
                        lblGrade.setText(" ");
                        lblStudentID.setText(" ");
                        new Alert(Alert.AlertType.CONFIRMATION, "Result saved successfully..!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Error..!").show();
                    }
                }
            }

        }
    }

    public void setGradeOnAction(ActionEvent actionEvent) {
        if(!txtMarks.getText().equals("")) {
            checkGrade(Integer.parseInt(txtMarks.getText()));
        }else {lblGrade.setText(" ");}
    }





    public void searchStudentOnAction(ActionEvent actionEvent) {
        search(txtSearchStudent.getText());
    }

    public void goToDashBord(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) UserResultForm.getScene().getWindow();
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

    public void goToDashBoardTwoForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserDashBoardTwoForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }

    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }

    public void goToSubjectForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserSubjectForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }

    public void goToPaymentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }

    public void goToAttendanceForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserAttendanceForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }

    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserResultForm.getChildren().clear();
        UserResultForm.getChildren().add(load);
    }



}
