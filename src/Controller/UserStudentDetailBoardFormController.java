package Controller;

import BusinessLogics.EmployeeController;
import BusinessLogics.StudentController;
import Model.Employee;
import Model.Student;
import Utill.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import tm.StudentTm;
import tm.TeacherTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserStudentDetailBoardFormController {
    public Label lblStudentId;
    public JFXComboBox<String> cmbRegistationFee;
    public ComboBox<String> cmbGender;
    public ComboBox<String> cmbGrade;
    public JFXTextField txtParentName;
    public JFXTextField txtAddress;
    public JFXTextField txtBirthday;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtLName;
    public JFXTextField txtFName;
    public JFXTextField txtId;

    public AnchorPane UserStudentDetailsBordForm;
    public TableColumn colStId;
    public TableColumn colSName;
    public TableColumn colLName;
    public TableColumn colDate;
    public TableColumn colGender;
    public TableColumn colGrade;
    public TableColumn colParentName;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableView tblUpdate;
    public JFXTextField txtFName1;
    public JFXTextField txtLName2;
    public JFXTextField txtEmail2;
    public JFXTextField txtContact2;
    public JFXTextField txtBirthday2;
    public JFXTextField txtAddress2;
    public JFXTextField txtParentName2;
    public ComboBox<String> cmbGrade2;
    public ComboBox<String> cmbGender2;
    public JFXTextField txtID2;
    public Label colStId2;
    public TableView tblDelete;
    public TableColumn colStId1;
    public TableColumn colSName1;
    public TableColumn colLName1;
    public TableColumn colDate1;
    public TableColumn colGender1;
    public TableColumn colGrade1;
    public TableColumn colParentName1;
    public TableColumn colContact1;
    public TableColumn colAddress1;
    public TableColumn colEmail1;
    public Label lblStID2;
    public JFXButton btnUpdate;
    public JFXButton btnRegister;

    ArrayList<Student> a = new ArrayList<>();
    int cartSelectedRowForRemove = -1;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern firstNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern parentNamePattern = Pattern.compile("^[A-Za-z.]{3,}$");
    Pattern phoneNumberPattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern dateOfBirthPattern = Pattern.compile("^[0-9.-]{3,}$");
    Pattern addressPattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern emailPattern = Pattern.compile("^[a-z0-9.@]{3,}$");


    LinkedHashMap<JFXTextField, Pattern> map1 = new LinkedHashMap();
    Pattern firstName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern parentName1Pattern = Pattern.compile("^[A-Za-z.]{3,}$");
    Pattern phoneNumber1Pattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern dateOfBirth1Pattern = Pattern.compile("^[0-9.-]{3,}$");
    Pattern address1Pattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern email1Pattern = Pattern.compile("^[a-z0-9.@]{3,}$");


    public void initialize() throws SQLException, ClassNotFoundException {
        storeValidations();
        storeValidations1();
        btnUpdate.setDisable(true);
        btnRegister.setDisable(true);

        cmbGender.getItems().setAll("Male", "Female");
        cmbGender2.getItems().setAll("Male", "Female");
        cmbGrade.getItems().setAll("Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11");
        cmbGrade2.getItems().setAll("Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11");
        cmbRegistationFee.getItems().setAll("200", "500");
        lblStudentId.setText(new StudentController().setCustomerIDS());

        tblUpdate.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println(a.toString());
            cartSelectedRowForRemove = (int) newValue;
        });

        tblDelete.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println(a.toString());
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    private void storeValidations() {
        map.put(txtFName, firstNamePattern);
        map.put(txtLName, lastNamePattern);
        map.put(txtParentName, parentNamePattern);
        map.put(txtContact, phoneNumberPattern);
        map.put(txtBirthday, dateOfBirthPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtEmail, emailPattern);
    }

    private void storeValidations1() {
        map1.put(txtFName1, firstName1Pattern);
        map1.put(txtLName2, lastName1Pattern);
        map1.put(txtParentName2, parentName1Pattern);
        map1.put(txtContact2, phoneNumber1Pattern);
        map1.put(txtBirthday2, dateOfBirth1Pattern);
        map1.put(txtAddress2, address1Pattern);
        map1.put(txtEmail2, email1Pattern);
    }

    public void jfxTextField_Key_Release(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXTextFields(map, btnRegister);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void jfxTextField_Key_Release1(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXTextFields(map1, btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }


    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }

    public void goToResultForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserResultForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }

    public void goToAttendanceForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserAttendanceForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }

    public void goToPaymentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }

    public void goToSubjectForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserSubjectForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }

    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);

    }

    public void goToDashBoard(ActionEvent mouseEvent) throws IOException {
        Stage stage = (Stage) UserStudentDetailsBordForm.getScene().getWindow();
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
        UserStudentDetailsBordForm.getChildren().clear();
        UserStudentDetailsBordForm.getChildren().add(load);
    }


    public void studentRegister(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtFName.getText().equals("") && !txtLName.getText().equals("") && !txtBirthday.getText().equals("") && !cmbGender.getSelectionModel().getSelectedItem().isEmpty()
                && !cmbGrade.getSelectionModel().getSelectedItem().isEmpty() && !txtParentName.getText().equals("") && !txtContact.getText().equals("") && !txtAddress.getText().equals("") &&
                !txtEmail.getText().equals("") && !cmbRegistationFee.getSelectionModel().getSelectedItem().isEmpty()) {
            Student student = new Student(
                    lblStudentId.getText(), txtFName.getText(), txtLName.getText(),
                    txtBirthday.getText(), cmbGender.getSelectionModel().getSelectedItem(), cmbGrade.getSelectionModel().getSelectedItem()
                    , txtParentName.getText(), txtContact.getText(), txtAddress.getText(), txtEmail.getText(), cmbRegistationFee.getSelectionModel().getSelectedItem()
            );

            if (new StudentController().saveStudent(student)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                initialize();

                //setstudentId();

                txtFName.clear();
                txtLName.clear();
                txtBirthday.clear();
                cmbGender.getSelectionModel().clearSelection();
                cmbGrade.getSelectionModel().clearSelection();
                txtParentName.clear();
                txtContact.clear();
                txtAddress.clear();
                txtEmail.clear();
                //cmbRegistationFee.getSelectionModel().clearSelection();
                cmbRegistationFee.getItems().clear();


            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Fill all Fields and Try Again..").show();
        }
    }


    public void selectedIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        a.clear();
        Student c1 = new StudentController().getStudent(studentId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(Student c) {

        a.add(c);

        colStId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("snamefname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colParentName.setCellValueFactory(new PropertyValueFactory<>("parentname"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tblUpdate.setItems(FXCollections.observableArrayList(a));


    }

    void setData2(Student c) {


        txtId.setText(c.getSid());
        txtFName1.setText(c.getSnamefname());
        txtLName2.setText(c.getLastname());
        txtBirthday2.setText(c.getDateofbirth());
        cmbGender2.setValue(c.getGender());
        cmbGrade2.setValue(c.getGrade());
        txtParentName2.setText(c.getParentname());
        txtContact2.setText(c.getContact());
        txtAddress2.setText(c.getAddress());
        txtEmail2.setText(c.getEmail());


        tblUpdate.setItems(FXCollections.observableArrayList(a));


    }

    public void selectedRowOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row..").show();
        } else {
            Student student = a.get(cartSelectedRowForRemove);

            initialize();
            setData2(student);


        }
        a.clear();
    }

    public void updateStudentDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtId.getText().equals("") && !txtFName1.getText().equals("") && !txtLName2.getText().equals("") &&
                !txtBirthday2.getText().equals("") &&
                !cmbGender2.getSelectionModel().getSelectedItem().isEmpty()
                && !cmbGrade2.getSelectionModel().getSelectedItem().equals("") &&
                !txtParentName2.getText().equals("")
                && !txtContact2.getText().equals("") && !txtAddress2.getText().equals("") && !txtEmail2.getText().equals("")) {
            System.out.println(a.toString());


            Student c = new Student(
                    txtId.getText(), txtFName1.getText(), txtLName2.getText(), txtBirthday2.getText(), cmbGender2.getSelectionModel().getSelectedItem(),
                    cmbGrade2.getSelectionModel().getSelectedItem(), txtParentName2.getText(), txtContact2.getText(), txtAddress2.getText(),
                    txtEmail2.getText()
            );
            initialize();

            if (new StudentController().updateStudent(c)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update successfully..", ButtonType.OK).show();


                txtId.setText(" ");
                txtFName1.clear();
                txtLName2.clear();
                txtBirthday2.clear();
                cmbGender2.getSelectionModel().clearSelection();
                cmbGrade2.getSelectionModel().clearSelection();
                txtParentName2.clear();
                txtContact2.clear();
                txtAddress2.clear();
                txtEmail2.clear();


            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Not a successfully update customer details", ButtonType.OK).show();
            }

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
        }

    }

    public void selectedIdOnAction2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtID2.getText();

        Student c1 = new StudentController().getStudent(studentId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData1(c1);
        }
    }

    void setData1(Student c) {

        a.add(c);
        lblStID2.setText(c.getSid());
        colStId1.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colSName1.setCellValueFactory(new PropertyValueFactory<>("snamefname"));
        colLName1.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        colGender1.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colParentName1.setCellValueFactory(new PropertyValueFactory<>("parentname"));
        colContact1.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress1.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<>("email"));


        tblDelete.setItems(FXCollections.observableArrayList(a));


    }

    public void studentDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row..").show();
        } else {

            Student s = a.get(cartSelectedRowForRemove);


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Message");
            alert.setHeaderText("Message of delete details of Students..");
            alert.setContentText("Do you want to delete this Student ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null || option.get() == ButtonType.CANCEL) {


            } else if (option.get() == ButtonType.OK) {
                if (new StudentController().deleteStudent(s)) {

                    a.clear();
                    tblDelete.setItems(FXCollections.observableArrayList(a));
                    txtID2.clear();
                    lblStID2.setText(" ");


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again..!").show();
                }
            }
        }

    }


    public void updateDetails(MouseEvent mouseEvent) {
        btnUpdate.setDisable(false);
    }
}
