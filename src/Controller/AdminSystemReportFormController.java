package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminSystemReportFormController {
    public AnchorPane AdminSystemReportForm;

    public void goToDashBoardForm(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AdminSystemReportForm.getScene().getWindow();
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
        AdminSystemReportForm.getChildren().clear();
        AdminSystemReportForm.getChildren().add(load);
    }

    public void goToEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminEmployeeSalaryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSystemReportForm.getChildren().clear();
        AdminSystemReportForm.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSystemReportForm.getChildren().clear();
        AdminSystemReportForm.getChildren().add(load);
    }

    public void goToSettings(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSettingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSystemReportForm.getChildren().clear();
        AdminSystemReportForm.getChildren().add(load);
    }

    public void goToInformation(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminInformationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSystemReportForm.getChildren().clear();
        AdminSystemReportForm.getChildren().add(load);
    }
}
