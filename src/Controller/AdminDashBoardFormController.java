package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {

    public AnchorPane AdminDashBoard;

    public void goToDashBoardForm(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AdminDashBoard.getScene().getWindow();
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

    public void goToEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminEmployeeSalaryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBoard.getChildren().clear();
        AdminDashBoard.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBoard.getChildren().clear();
        AdminDashBoard.getChildren().add(load);
    }

    public void goToSettings(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSettingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBoard.getChildren().clear();
        AdminDashBoard.getChildren().add(load);
    }

    public void goToInformation(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminInformationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBoard.getChildren().clear();
        AdminDashBoard.getChildren().add(load);
    }


    public void goToTeacherDashBoard(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminTeacherForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBoard.getChildren().clear();
        AdminDashBoard.getChildren().add(load);
    }


}
