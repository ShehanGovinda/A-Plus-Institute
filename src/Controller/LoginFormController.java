package Controller;

import Model.Register;
import Utill.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import BusinessLogics.RegisterController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginFormController {
    public AnchorPane LoginForm;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtPhoneNumber;
    public JFXComboBox<String> cmbUserType;

  
    public JFXButton btnSignIn;

    public JFXPasswordField txtConformedPassword;
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    LinkedHashMap<JFXTextField, Pattern> map2 = new LinkedHashMap();
    LinkedHashMap<JFXPasswordField, Pattern> map1 = new LinkedHashMap();
    Pattern firstNamePattern = Pattern.compile("^[A-z.]{3,}$");
    Pattern lastNamePattern = Pattern.compile("^[A-z]{4,}$");
    Pattern nicPattern = Pattern.compile("^[0-9V]{8,15}$");
    Pattern phoneNumberPattern = Pattern.compile("^[\\d]{8,10}$");
    Pattern userNamePattern = Pattern.compile("^[A-z]{3,5}$");
    Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9@#$%&_]{3,8}$");
    Pattern ConformedPasswordPattern = Pattern.compile("^[a-zA-Z0-9@#$%&_]{2,8}$");




    public void initialize(){
        cmbUserType.getItems().setAll("Admin","User");

        btnSignIn.setDisable(true);
        storeValidations();
        storeValidations1();
        storeValidations2();
    }


    private void storeValidations() {
        map.put(txtFirstName, firstNamePattern);
        map.put(txtLastName, lastNamePattern);
        map.put(txtNic, nicPattern);
        map.put(txtPhoneNumber, phoneNumberPattern);



    }

    private void storeValidations1() {
        map2.put(txtUserName, userNamePattern);
        map2.put(txtPassword, passwordPattern);
    }

    private void storeValidations2() {

        map1.put(txtConformedPassword, ConformedPasswordPattern);
    }

////    public void firstNameOnAction(KeyEvent keyEvent) {
////        String regEx1 = "^[A-z.]{3,}$";
////        String text1 = txtFirstName.getText();
////        Pattern compile = Pattern.compile(regEx1);
////        boolean matches = compile.matcher(text1).matches();
////
////        if(matches){
////            txtFirstName.setStyle("-fx-text-fill: green");
////            btnSignIn.setDisable(false);
////        }else {
////            txtFirstName.setStyle("-fx-text-fill: #800000");
////            //btnSignIn.setDisable(true);
////        }
////
////        if(keyEvent.getCode()== KeyCode.ENTER){
////            if(matches){
////                txtLastName.requestFocus();
////            }
////        }
////    }
//
////    public void lastNameOnAction(KeyEvent keyEvent) {
////        String regEx2 = "^[A-z]{4,}$";
////        String text2 = txtLastName.getText();
////        Pattern compile = Pattern.compile(regEx2);
////        boolean matches = compile.matcher(text2).matches();
////
////        if(matches){
////            txtLastName.setStyle("-fx-text-fill: green");
////            btnSignIn.setDisable(false);
////        }else {
////            txtLastName.setStyle("-fx-text-fill: #800000");
////           // btnSignIn.setDisable(true);
////        }
////
////        if(keyEvent.getCode()== KeyCode.ENTER){
////            if(matches){
////                txtNic.requestFocus();
////            }
////        }
////    }
//
//    public void nicOnAction(KeyEvent keyEvent) {
//        String regEx3 = "^[0-9V]{8,15}$";
//        String text3 = txtNic.getText();
//        Pattern compile = Pattern.compile(regEx3);
//        boolean matches = compile.matcher(text3).matches();
//
//        if(matches){
//            txtNic.setStyle("-fx-text-fill: green");
//            btnSignIn.setDisable(false);
//        }else {
//            txtNic.setStyle("-fx-text-fill: #800000");
//           // btnSignIn.setDisable(true);
//        }
//
//        if(keyEvent.getCode()== KeyCode.ENTER){
//            if(matches){
//                txtPhoneNumber.requestFocus();
//            }
//        }
//    }
//
//    public void phoneNumberOnAction(KeyEvent keyEvent) {
//        String regEx4 = "^[\\d]{8,10}$";
//        String text4 = txtPhoneNumber.getText();
//        Pattern compile = Pattern.compile(regEx4);
//        boolean matches = compile.matcher(text4).matches();
//
//        if(matches){
//            txtPhoneNumber.setStyle("-fx-text-fill: green");
//            btnSignIn.setDisable(false);
//
//        }else {
//            txtPhoneNumber.setStyle("-fx-text-fill: #800000");
//            //txtPhoneNumber.setDisable(true);
//        }
//
//        if(keyEvent.getCode()== KeyCode.ENTER){
//            if(matches){
//                cmbUserType.requestFocus();
//            }
//        }
//    }
//
//
//
//
//
////    public void conformedPasswordOnAction(KeyEvent keyEvent) {
////        String regEx = "^[a-zA-Z0-9@#$%&_]*";
////        String text = txtPassword1.getText();
////        Pattern compile = Pattern.compile(regEx);
////        boolean matches = compile.matcher(text).matches();
////
////        if(matches){
////            txtPassword1.setStyle("-fx-text-fill: green");
////            txtPassword1.setDisable(false);
////        }else {
////            txtPassword1.setStyle("-fx-text-fill: #800000");
////            //btnSignIn.setDisable(true);
////        }
////
////        if(keyEvent.getCode()== KeyCode.ENTER){
////            if(matches){
////                txtPassword.requestFocus();
////            }
////        }
////    }
////
////    public void passwordOnAction(KeyEvent keyEvent) {
////        String regEx = "^[a-zA-Z0-9@#$%&_]*$";
////        String text = txtPassword.getText();
////        Pattern compile = Pattern.compile(regEx);
////        boolean matches = compile.matcher(text).matches();
////
////        if(matches){
////            txtPassword.setStyle("-fx-text-fill: black");
////            txtPassword.setDisable(false);
////        }else {
////            txtPassword.setStyle("-fx-text-fill: red");
////            btnSignIn.setDisable(true);
////        }
////
////        if(keyEvent.getCode()== KeyCode.ENTER){
////            if(matches){
////
////
////                if(txtPassword1.getText().equals(txtPassword.getText())){
////                }else{
////                    txtPassword.setStyle("-fx-border-color: red");
////                    txtPassword.setStyle("-fx-text-fill: red");
////                }
////            }
////        }
////        //btnSignIn.requestFocus();
////    }
////




    public void goToDashBoardForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) LoginForm.getScene().getWindow();
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

    public void allSignIn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(txtUserName.getText().equals(txtPassword.getText())){

            if(!txtFirstName.getText().equals("") && !txtLastName.getText().equals("") && !txtNic.getText().equals("") && !txtPhoneNumber.getText().equals("") && !cmbUserType.getSelectionModel().getSelectedItem().equals("null") && !txtUserName.getText().equals("") &&!txtPassword.getText().equals("") &&!txtConformedPassword.getText().equals("") ){

                Register register = new Register(txtFirstName.getText(),txtLastName.getText(),txtNic.getText(),txtPhoneNumber.getText(),cmbUserType.getSelectionModel().getSelectedItem(),txtUserName.getText(),txtConformedPassword.getText());

                if (new RegisterController().saveRegister(register)) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Register successfully", ButtonType.OK).show();
                    txtFirstName.clear();
                    txtLastName.clear();
                    txtNic.clear();
                    txtPhoneNumber.clear();
                    cmbUserType.getSelectionModel().clearSelection();
                    txtPassword.clear();
                    txtUserName.clear();
                    txtConformedPassword.clear();

                }else{
                    new Alert(Alert.AlertType.CONFIRMATION, "Not Registered successfully", ButtonType.OK).show();
                }
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
            }
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Not a password", ButtonType.OK).show();
        }

    }


    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForNormalTextFields(map, btnSignIn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }




    public void jfxPasswordField_Key_Relesed(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXPasswordFields(map1, btnSignIn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void jfxTextField_Key_Relesed(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForJFXTextFields(map2, btnSignIn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }
}
