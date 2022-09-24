package Controller;

import BusinessLogics.EmployeeController;
import BusinessLogics.StudentController;
import BusinessLogics.TeacherController;
import Model.Attendance;
import Model.Employee;
import Model.Student;
import Model.Teacher;
import Utill.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class AdminEmployeeSalaryFormController {
    public Label lblEmpId;
    public JFXTextField txtSalary;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtNic;
    public JFXTextField txtEmail;
    public JFXTextField txtLName;
    public JFXTextField txtFName;
    public AnchorPane AdminEmployeeSalaryForm;
    public JFXTextField txtNicSearch;
    public JFXTextField txtFName1;
    public JFXTextField txtLName1;
    public JFXTextField txtEmail1;
    public JFXTextField txtNic1;
    public JFXTextField txtContact1;
    public JFXTextField txtAddress1;
    public JFXTextField txtSalary1;
    public Label lblEmpId1;
    public TableView tblDelete;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNic;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public JFXTextField txtNicSearch1;
    public Label lblEmpId2;
    public JFXButton btnUpdate;
    public JFXButton btnAdd;


    ArrayList<Employee> a = new ArrayList<>();
    int cartSelectedRowForRemove = -1;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern firstNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern addressPattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern phoneNumberPattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern nicPattern = Pattern.compile("^[0-9V]{8,15}$");
    Pattern emailPattern = Pattern.compile("^[a-z0-9.]{3,}$");
    Pattern salaryPattern = Pattern.compile("^[0-9.]{3,}$");

    LinkedHashMap<JFXTextField, Pattern> map1 = new LinkedHashMap();
    Pattern firstName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastName1Pattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern address1Pattern = Pattern.compile("^[A-Za-z0-9.,/\\s]{3,}$");
    Pattern phoneNumber1Pattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern nic1Pattern = Pattern.compile("^[0-9V]{8,15}$");
    Pattern email1Pattern = Pattern.compile("^[a-z0-9.@]{3,}$");
    Pattern salary1Pattern = Pattern.compile("^[0-9.]{3,}$");


    public void initialize() throws SQLException, ClassNotFoundException {

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        storeValidations();
        storeValidations1();
        lblEmpId.setText(new EmployeeController().setEmployeeIDS());

        tblDelete.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    private void storeValidations() {
        map.put(txtFName, firstNamePattern);
        map.put(txtLName, lastNamePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtContact, phoneNumberPattern);
        map.put(txtNic, nicPattern);
        map.put(txtEmail, emailPattern);
        map.put(txtSalary, salaryPattern);
    }

    private void storeValidations1() {
        map1.put(txtFName1, firstName1Pattern);
        map1.put(txtLName1, lastName1Pattern);
        map1.put(txtAddress1, address1Pattern);
        map1.put(txtContact1, phoneNumber1Pattern);
        map1.put(txtNic1, nic1Pattern);
        map1.put(txtEmail1, email1Pattern);
        map1.put(txtSalary1, salary1Pattern);
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
        Stage stage = (Stage) AdminEmployeeSalaryForm.getScene().getWindow();
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
        AdminEmployeeSalaryForm.getChildren().clear();
        AdminEmployeeSalaryForm.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminEmployeeSalaryForm.getChildren().clear();
        AdminEmployeeSalaryForm.getChildren().add(load);
    }

    public void goToSettings(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSettingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminEmployeeSalaryForm.getChildren().clear();
        AdminEmployeeSalaryForm.getChildren().add(load);
    }

    public void goToInformation(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminInformationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminEmployeeSalaryForm.getChildren().clear();
        AdminEmployeeSalaryForm.getChildren().add(load);
    }


    public void addEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtFName.getText().equals("") && !txtFName.getText().equals("") && !txtNic.getText().equals("") &&
                !txtContact.getText().equals("") && !txtEmail.getText().equals("") && !txtAddress.getText().equals("")
                && !txtSalary.getText().equals("")) {
            Employee employee = new Employee(
                    lblEmpId.getText(), txtFName.getText(), txtFName.getText(),
                    txtNic.getText(), txtContact.getText(), txtEmail.getText(),
                    txtAddress.getText(), Double.parseDouble(txtSalary.getText())
            );

            if (new EmployeeController().saveEmployee(employee)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                initialize();
                txtFName.clear();
                txtLName.clear();
                txtNic.clear();
                txtContact.clear();
                txtEmail.clear();
                txtAddress.clear();
                txtSalary.clear();


            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "fill all fields").show();
        }
    }

    public void selectIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtNicSearch.getText();

        Employee c1 = new EmployeeController().getEmployee(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(Employee c) {
        lblEmpId1.setText(c.getEmpId());
        txtFName1.setText(c.getFirstName());
        txtLName1.setText(c.getLastName());
        txtNic1.setText(c.getNic());
        txtContact1.setText(c.getContact());
        txtEmail1.setText(c.getEmail());
        txtAddress1.setText(c.getAddress());
        String salary = String.valueOf(c.getSalary());
        txtSalary1.setText(salary);
    }


    public void selectIdOnAction2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtNicSearch1.getText();

        Employee c1 = new EmployeeController().getEmployee(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData2(c1);
        }
    }

    void setData2(Employee c) {

        a.add(c);

        lblEmpId2.setText(c.getEmpId());
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        tblDelete.setItems(FXCollections.observableArrayList(a));


    }

    public void updateEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!lblEmpId1.getText().equals("") && !txtFName1.getText().equals("") && !txtLName1.getText().equals("") &&
                !txtNic1.getText().equals("") && !txtContact1.getText().equals("") && !txtEmail1.getText().equals("") &&
                !txtAddress1.getText().equals("")
                && !txtSalary1.getText().equals("")) {

            Employee c1 = new Employee(
                    lblEmpId1.getText(), txtFName1.getText(), txtLName1.getText(), txtNic1.getText(),
                    txtContact1.getText(), txtAddress1.getText(), txtEmail1.getText(), Double.parseDouble(txtSalary1.getText())
            );

            if (new EmployeeController().updateEmployee(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update successfully..", ButtonType.OK).show();

                initialize();

                txtNicSearch.clear();
                txtFName1.clear();
                txtLName1.clear();
                txtNic1.clear();
                txtContact1.clear();
                txtEmail1.clear();
                txtAddress1.clear();
                txtSalary1.clear();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Not a successfully update customer details", ButtonType.OK).show();
            }

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
        }
    }


    public void deleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row..").show();
        } else {

            Employee s = a.get(cartSelectedRowForRemove);


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Message");
            alert.setHeaderText("Message of delete details of Students..");
            alert.setContentText("Do you want to delete this Student ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null || option.get() == ButtonType.CANCEL) {


            } else if (option.get() == ButtonType.OK) {
                if (new EmployeeController().deleteEmployee(s)) {

                    a.clear();
                    tblDelete.setItems(FXCollections.observableArrayList(a));
                    txtNicSearch1.clear();
                    lblEmpId2.setText(" ");


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again..!").show();
                }
            }
        }

    }


}
