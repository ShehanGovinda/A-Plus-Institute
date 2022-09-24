package Controller;

import BusinessLogics.StudentController;
import BusinessLogics.TeacherController;
import Model.Student;
import Model.Teacher;
import Utill.ValidationUtil;
import com.jfoenix.controls.JFXButton;
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
import tm.TeacherTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class AdminTeacherFormController {
    public JFXTextField txtSubject;

    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtLName;
    public JFXTextField txtFName;

    public AnchorPane AdminTeacherForms;
    public Label lblTeacherId;
    public JFXTextField txtSubject1;
    public JFXTextField txtAddress1;
    public JFXTextField txtContact1;
    public JFXTextField txtNic1;
    public JFXTextField txtEmail1;
    public JFXTextField txtLName1;
    public JFXTextField txtFName1;
    public Label lblTeacherId1;
    public JFXTextField txtNicSearch;
    public TableView tblDelete;

    public JFXTextField txtNicSearch1;
    public Label lblTeacherId2;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNic;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colSubjectName;
    public JFXTextField txtFees1;
    public JFXTextField txtFees;
    public TableColumn colFee;
    public JFXButton btnUpdate;
    public JFXButton btnAdd;


    ArrayList<TeacherTm> temp = new ArrayList<>();
    ArrayList<TeacherTm> teacher = new ArrayList<>();
    ArrayList<Teacher> a = new ArrayList<>();


    int cartSelectedRowForRemove = -1;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern firstNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern nicPattern = Pattern.compile("^[0-9V]{8,15}$");
    Pattern phoneNumberPattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern addressPattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern emailPattern = Pattern.compile("^[a-z0-9.@]{3,}$");
    Pattern subjectNamePattern = Pattern.compile("^[A-Z a-z\\s]{2,}$");
    Pattern feePattern = Pattern.compile("^[0-9.]{3,}$");


    LinkedHashMap<JFXTextField, Pattern> map1 = new LinkedHashMap();
    Pattern firstName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern nic1Pattern = Pattern.compile("^[0-9V]{8,15}$");
    Pattern phoneNumber1Pattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern address1Pattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern email1Pattern = Pattern.compile("^[a-z0-9.@]{3,}$");
    Pattern subjectName1Pattern = Pattern.compile("^[A-Z a-z\\s]{2,}$");
    Pattern fee1Pattern = Pattern.compile("^[0-9.]{3,}$");


    public void initialize() throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        storeValidations();
        storeValidations1();

        lblTeacherId.setText(new TeacherController().setTeacherIDS());


        tblDelete.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    private void storeValidations() {
        map.put(txtFName, firstNamePattern);
        map.put(txtLName, lastNamePattern);
        map.put(txtNic, nicPattern);
        map.put(txtContact, phoneNumberPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtEmail, emailPattern);
        map.put(txtSubject, subjectNamePattern);
        map.put(txtFees, feePattern);
    }

    private void storeValidations1() {
        map1.put(txtFName1, firstName1Pattern);
        map1.put(txtLName1, lastName1Pattern);
        map1.put(txtNic1, nic1Pattern);
        map1.put(txtContact1, phoneNumber1Pattern);
        map1.put(txtAddress1, address1Pattern);
        map1.put(txtEmail1, email1Pattern);
        map1.put(txtSubject1, subjectName1Pattern);
        map1.put(txtFees1, fee1Pattern);
    }

    public void jfxTextField_Key_Release1(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXTextFields(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void jfxTextField_Key_Release2(KeyEvent keyEvent) {
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

    public void goToDashBoardForm(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AdminTeacherForms.getScene().getWindow();
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
        AdminTeacherForms.getChildren().clear();
        AdminTeacherForms.getChildren().add(load);
    }

    public void goToEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminEmployeeSalaryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminTeacherForms.getChildren().clear();
        AdminTeacherForms.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminTeacherForms.getChildren().clear();
        AdminTeacherForms.getChildren().add(load);
    }

    public void goToSettings(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSettingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminTeacherForms.getChildren().clear();
        AdminTeacherForms.getChildren().add(load);
    }

    public void goToInformation(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminInformationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminTeacherForms.getChildren().clear();
        AdminTeacherForms.getChildren().add(load);
    }

    public void addTeacherDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtFName.getText().equals("") && !txtLName.getText().equals("") && !txtNic.getText().equals("") && !txtContact.getText().equals("")
                && !txtContact.getText().equals("") && !txtAddress.getText().equals("") && !txtEmail.getText().equals("") &&
                !txtSubject.getText().equals("") && !txtFees.getText().equals("")) {


            Teacher teacher = new Teacher(
                    lblTeacherId.getText(), txtFName.getText(), txtLName.getText(),
                    txtNic.getText(), txtContact.getText(),
                    txtAddress.getText(), txtEmail.getText(), txtSubject.getText(), txtFees.getText());

            if (new TeacherController().saveTeacher(teacher)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                initialize();
                txtFName.clear();
                txtLName.clear();
                txtNic.clear();
                txtContact.clear();
                txtAddress.clear();
                txtEmail.clear();
                txtSubject.clear();
                txtFees.clear();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Fill all Fields and Try Again..").show();
        }
    }


    public void selectIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtNicSearch.getText();

        Teacher c1 = new TeacherController().getTeacher(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(Teacher c) {
        lblTeacherId1.setText(c.getTeacherId());
        txtFName1.setText(c.getTeacherfirstname());
        txtLName1.setText(c.getTeacherlastname());
        txtNic1.setText(c.getTeacherNic());
        txtContact1.setText(c.getTeachercontact());
        txtAddress1.setText(c.getTeacheraddress());
        txtEmail1.setText(c.getTeacherEmail());
        txtSubject1.setText(c.getSubjectName());
        txtFees1.setText(c.getFee());
    }


    public void selectIdOnAction2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtNicSearch1.getText();

        Teacher c1 = new TeacherController().getTeacher(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData2(c1);
        }

    }

    void setData2(Teacher c) {

        a.add(c);

        lblTeacherId2.setText(c.getTeacherId());
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("teacherfirstname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("teacherlastname"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("teacherNic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("teachercontact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("teacheraddress"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("teacherEmail"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));


        tblDelete.setItems(FXCollections.observableArrayList(a));


    }

    public void updateTeacher(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!lblTeacherId1.getText().equals("") && !txtFName1.getText().equals("") && !txtLName1.getText().equals("") &&
                !txtNic1.getText().equals("") && !txtContact1.getText().equals("") && !txtAddress1.getText().equals("")
                && !txtEmail1.getText().equals("") && !txtSubject1.getText().equals("") && !txtFees1.getText().equals("")) {

            Teacher c1 = new Teacher(
                    lblTeacherId1.getText(), txtFName1.getText(), txtLName1.getText(), txtNic1.getText(),
                    txtContact1.getText(), txtAddress1.getText(), txtEmail1.getText(), txtSubject1.getText(), txtFees1.getText()
            );

            if (new TeacherController().updateTeacher(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update successfully..", ButtonType.OK).show();

                initialize();
                txtNicSearch.clear();
                txtFName1.clear();
                txtLName1.clear();
                txtNic1.clear();
                txtContact1.clear();
                txtAddress1.clear();
                txtEmail1.clear();
                txtSubject1.clear();
                txtFees1.clear();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Not a successfully update customer details", ButtonType.OK).show();
            }

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
        }
    }


    public void deleteTeacher(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row..").show();
        } else {

            Teacher s = a.get(cartSelectedRowForRemove);


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Message");
            alert.setHeaderText("Message of delete details of Students..");
            alert.setContentText("Do you want to delete this Student ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null || option.get() == ButtonType.CANCEL) {


            } else if (option.get() == ButtonType.OK) {
                if (new TeacherController().deleteTeacher(s)) {

                    a.clear();
                    tblDelete.setItems(FXCollections.observableArrayList(a));
                    txtNicSearch1.clear();
                    lblTeacherId2.setText(" ");


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again..!").show();
                }
            }
        }

    }


}
