package Controller;

import BusinessLogics.PaymentController;
import BusinessLogics.StudentController;
import BusinessLogics.TeacherController;
import Model.Payment;
import Model.Student;
import Model.Subject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.PaymentInfoTM;
import tm.StudentMonthlyPaymentsDetailTM;
import tm.SubjectTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class UserPaymentFormController {
    public AnchorPane UserPaymentForm;
    public JFXTextField txtSearchStudent;
    public ComboBox<String> cmbPaymentMonth;
    public TableView tblStudentDetails;
    public ComboBox<String> cmbSubject;
    public TextField txtStudentID;
    public JFXRadioButton rdBTNPayForAllSubjects;
    public TableView tblSubjectDetails;
    public JFXCheckBox chBoxPaidSuccessfully;
    public Label lblTotalFee;
    public JFXTextField txtCardNumber;
    public JFXButton btnPay;
    public TableColumn colPaymentOK;
    public TableColumn colSubjectFee;
    public TableColumn colTeacherName;
    public TableColumn colSubjectName;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colGrade;

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<StudentMonthlyPaymentsDetailTM> studentPaymentInfo = new ArrayList<>();
   // ArrayList<Subject> subjects = new ArrayList<>();
    ArrayList<SubjectTm> subjects = new ArrayList<>();
    int selectedRow = -1;
    Double total = 0.00;
    String teacherName = null;

    public void initialize() throws SQLException, ClassNotFoundException {
        //colPaymentOK.setText("-fx-text-fill:red");
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date date = new Date();
        String month = formatter.format(date);
        cmbPaymentMonth.setValue(checkMonth(month));
        cmbSubject.setDisable(true);
        btnPay.setDisable(true);

        students = new StudentController().getAllStudentDetails();
        for(Student s : students){
            studentPaymentInfo.add(new StudentMonthlyPaymentsDetailTM(s.getSid(),s.getLastname(),s.getAddress(),s.getEmail(),s.getContact(),s.getGrade()));
        }
        setDataToStudentDetailsTable();
        cmbPaymentMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");


        txtSearchStudent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        tblStudentDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRow = (int) newValue;
            lblTotalFee.setText("0.00 /=");
            rdBTNPayForAllSubjects.setSelected(false);
        });

        cmbPaymentMonth.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<PaymentInfoTM> temp = new ArrayList<>();
            setDataToPaymentTable(temp);
            selectedRow=-1;

        });

        cmbSubject.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            for(SubjectTm s : subjects){
                if(s.getSubject().equals(cmbSubject.getSelectionModel().getSelectedItem())){
                    try {
                        teacherName = s.getTeacher();
                        String fee = new TeacherController().getFeesFromTeacherName(s.getTeacher());
                        lblTotalFee.setText(fee+" /=");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


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

    public void setSubjectsOnActions(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if(selectedRow==-1){

        }else{
            //colPaymentOK.setStyle("-fx-border-color: red;");
            cmbSubject.setDisable(false);
            cmbSubject.getItems().clear();
            //System.out.println("A");
            ArrayList<String> fees = new ArrayList<>();
            ArrayList<PaymentInfoTM> tableInfo = new ArrayList<>();

            StudentMonthlyPaymentsDetailTM s = studentPaymentInfo.get(selectedRow);
            txtStudentID.setText(s.getSid());

            subjects = new StudentController().getSubjectDetailsFromSID(s.getSid());

            //System.out.println(subjects.toString());

            for(SubjectTm tempsubject : subjects){
                System.out.println(tempsubject.getTeacher());
                String f = new TeacherController().getFeesFromTeacherName(tempsubject.getTeacher());
                fees.add(f);
            }
            //System.out.println(fees.toString());

            int count=0;
            for(SubjectTm n : subjects) {
                String condition = new PaymentController().getFeesFromTeacherName(s.getSid(),n.getSubject(),cmbPaymentMonth.getSelectionModel().getSelectedItem());

                PaymentInfoTM paymentInFo = new PaymentInfoTM(n.getSubject(),n.getTeacher(),String.valueOf(fees.get(count)),condition);
                count=count+1;
                tableInfo.add(paymentInFo);
            }
            setDataToPaymentTable(tableInfo);


            ArrayList<String> paymentMonths = new StudentController().getSubjectsFromSID(s.getSid());
            cmbSubject.getItems().setAll(paymentMonths);
        }
    }
    private void setDataToPaymentTable(ArrayList<PaymentInfoTM> tableInfo){
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherlastname"));
        colSubjectFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colPaymentOK.setCellValueFactory(new PropertyValueFactory<>("condition"));

        tblSubjectDetails.setItems(FXCollections.observableArrayList(tableInfo));
    }

    public void searchStudentOnAction(ActionEvent actionEvent) {
        search(txtSearchStudent.getText());
    }

    private void search(String value){
        try {
            ArrayList<Student> studentTemp = new ArrayList<>();

            studentPaymentInfo.clear();
            studentTemp = StudentController.searchStudent(value);
            for(Student s : studentTemp){
                studentPaymentInfo.add(new StudentMonthlyPaymentsDetailTM(s.getSid(),s.getLastname(),s.getAddress(),s.getEmail(),s.getContact(),s.getGrade()));
            }

            //ObservableList<EmployeeDetailsTM> tableData = FXCollections.observableArrayList();
            tblStudentDetails.getItems().setAll(studentPaymentInfo);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToStudentDetailsTable(){

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));


        tblStudentDetails.setItems(FXCollections.observableArrayList(studentPaymentInfo));

    }


    public void calculateTotalFee(ActionEvent actionEvent) {
        boolean b = rdBTNPayForAllSubjects.isSelected();

        if(b == true){
            cmbSubject.setDisable(true);
            cmbSubject.getSelectionModel().clearSelection();
            total = 0.00;
            for(SubjectTm s : subjects){
                //if(s.getSubject().equals(cmbSubject.getSelectionModel().getSelectedItem())){
                try {
                    String fee = new TeacherController().getFeesFromTeacherName(s.getTeacher());
                    total = total + Double.parseDouble(fee);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //}
            }
            lblTotalFee.setText(total+" /=");

        }else{
            cmbSubject.setDisable(false);
            lblTotalFee.setText("0.00 /=");
        }
    }

    public void chBoxPaidSuccessfullyOnAction(ActionEvent actionEvent) {
        if(chBoxPaidSuccessfully.isSelected()){
            btnPay.setDisable(false);
        }else{
            btnPay.setDisable(true);
        }
    }

    public void payOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String pDate = formatter.format(date);
        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        String pTime = formatter1.format(time);

        boolean b = rdBTNPayForAllSubjects.isSelected();
        System.out.println(b);
        if(!txtStudentID.getText().equals("") && (!cmbSubject.getSelectionModel().isEmpty() || b==true) && !txtCardNumber.getText().equals("")){
            if(b==true){
                for(SubjectTm s : subjects){
                    System.out.println(s.getTeacher());
                    Double fee = Double.parseDouble(new TeacherController().getFeesFromTeacherName(s.getTeacher()));
                    Payment payment = new Payment(txtStudentID.getText(),txtCardNumber.getText(),s.getSubject(),s.getTeacher(),cmbPaymentMonth.getSelectionModel().getSelectedItem(),fee,pDate,pTime);
                    if(new PaymentController().savePaymentInfo(payment)){



                    }else{
                        new Alert(Alert.AlertType.WARNING,"Something went wrong..!").show();
                    }
                }
                try {
                    JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/reports/Student_Payment.jrxml"));
                    JasperReport compileReport = JasperCompileManager.compileReport(design);
                    /*Get all values from table*/
                    // ObservableList<CustomerTM> items = tblCustomer.getItems();
                    /*Create a Bean Array Data Source and pass the table values to it*/
                    StudentMonthlyPaymentsDetailTM studentPayment = studentPaymentInfo.get(selectedRow);
                    /*setting values for parameters*/
                    String studentId = txtStudentID.getText();
                    String sName = studentPayment.getLastname();
                    String sAddress = studentPayment.getAddress();
                    String sContact = studentPayment.getContact();
                    String sGrade = studentPayment.getGrade();
                    String sCardNumber = txtCardNumber.getText();
                    String sPaymentMonth = cmbPaymentMonth.getSelectionModel().getSelectedItem();
                    String sPaymentAmount = lblTotalFee.getText();

                    /*Setting parameter values*/
                    HashMap map = new HashMap();
                    map.put("sid", studentId);// id= param name of report // customerID= input value of text field
                    map.put("studentName", sName);
                    map.put("address", sAddress);
                    map.put("contact", sContact);
                    map.put("grade", sGrade);
                    map.put("cardNumber", sCardNumber);
                    map.put("paymentMonth", sPaymentMonth);
                    map.put("amount", sPaymentAmount);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource(1));
                    JasperViewer.viewReport(jasperPrint, false);

                    //if you wanna print the report directly you can use this instead of JasperViewer
                    /*JasperPrintManager.printReport(jasperPrint,false);*/

                } catch (JRException e) {
                    e.printStackTrace();
                }
                txtCardNumber.clear();
                txtStudentID.clear();
                chBoxPaidSuccessfully.setSelected(false);
                studentPaymentInfo.clear();
                tblSubjectDetails.getItems().clear();
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION,"Payment success!").show();
            }else{
                Double fee = Double.parseDouble(new TeacherController().getFeesFromTeacherName(teacherName));
                Payment payment = new Payment(txtStudentID.getText(),txtCardNumber.getText(),cmbSubject.getSelectionModel().getSelectedItem(),teacherName,cmbPaymentMonth.getSelectionModel().getSelectedItem(),fee,pDate,pTime);
                if(new PaymentController().savePaymentInfo(payment)){
                    txtCardNumber.clear();
                    txtStudentID.clear();
                    chBoxPaidSuccessfully.setSelected(false);
                    studentPaymentInfo.clear();
                    tblSubjectDetails.getItems().clear();
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION,"Payment success!").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Something went wrong..!").show();
                }
            }

        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill all fields and try again..!").show();
        }
    }


    public void goToDashBord(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) UserPaymentForm.getScene().getWindow();
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
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }

    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }

    public void goToSubjectForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserSubjectForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }

    public void goToAttendanceForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserAttendanceForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }

    public void goToResultForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserResultForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }

    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserPaymentForm.getChildren().clear();
        UserPaymentForm.getChildren().add(load);
    }



    public void jfxTextField_Key_Release(KeyEvent keyEvent) {
        String regEx = "^(A)[0-9]{1,}$";
        String text = txtCardNumber.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(text).matches();

        if (matches) {
            txtCardNumber.setStyle("-fx-text-fill: black");
        } else {
            //txtCardNumber.setStyle("-fx-border-color: red");
            txtCardNumber.setStyle("-fx-text-fill: red");
        }
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                if (lblTotalFee.getText().equals(txtCardNumber.getText())) {
                } else {
                    txtCardNumber.setStyle("-fx-border-color: red");
                    txtCardNumber.setStyle("-fx-text-fill: red");
                }
            }
        }
    }
}
