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

public class AdminSettingFormController {
    public AnchorPane AdminSettingFormController;

    public void goToDashBoardForm(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AdminSettingFormController.getScene().getWindow();
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

    public void goToTeacherDashBoard(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminTeacherForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSettingFormController.getChildren().clear();
        AdminSettingFormController.getChildren().add(load);
    }

    public void goToEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminEmployeeSalaryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSettingFormController.getChildren().clear();
        AdminSettingFormController.getChildren().add(load);
    }

    public void goToSystemReports(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminSystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSettingFormController.getChildren().clear();
        AdminSettingFormController.getChildren().add(load);
    }

    public void goToInformation(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminInformationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminSettingFormController.getChildren().clear();
        AdminSettingFormController.getChildren().add(load);
    }
}
