package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardFormController {
    public AnchorPane DashBoardForm;
    public Text CreateAccount;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    public void initialize() {

    }

    public void goToSignInForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) DashBoardForm.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle(" A Plus Education Institute ");
        stage1.show();
        stage1.setResizable(false);
    }

    public void goToAdminDashBordForm(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        boolean include = getUserInfo("Admin");

        if (include == true) {
            Stage stage = (Stage) DashBoardForm.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle(" A Plus Education Institute ");
            stage1.show();
            stage1.setResizable(false);
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password is incorrect..please Try again..!").show();
        }
    }

    public void goToUserLoginForm(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        boolean include = getUserInfo("User");

        if (include == true) {
            Stage stage = (Stage) DashBoardForm.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("../views/UserDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle(" A Plus Education Institute ");
            stage1.show();
            stage1.setResizable(false);
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password is incorrect..please Try again..!").show();
        }

    }

    public boolean getUserInfo(String type) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Register` WHERE userType='" + type + "'");
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            if (rst.getString(6).equals(txtUserName.getText()) && rst.getString(7).equals(txtPassword.getText())) {
                //txtUserName =rst.getString(1);
                //firstnameofcashier=rst.getString(1);//set karanawa name eka ss kiyana eka admin dana onama ekak
                return true;

            }
        }
        return false;
    }

    public void goToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}