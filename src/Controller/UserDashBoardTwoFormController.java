package Controller;

import BusinessLogics.StudentController;
import BusinessLogics.SubjectController;
import BusinessLogics.TeacherController;
import Model.Teacher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDashBoardTwoFormController {
    public AnchorPane UserDashBoardTwoForm;
    public CategoryAxis month;
    public NumberAxis student;
    public javafx.scene.chart.BarChart BarChart;
    public TableView tblTeacherDetails;
    public TableColumn colTeacher;
    public TableColumn colSubject;
    public Label lblStudentCount;
    public Label lblTeacherCount;
    public Label lblSubjectCount;

    Stage stage = null;

    ArrayList<Teacher> details = new ArrayList<>();

    public void initialize() {

        try {

            details = new TeacherController().getDetails();
            setDataToTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblTeacherDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println(a.toString());
//            cartSelectedRowForRemove = (int) newValue;
        });

        try {

            lblStudentCount.setText(String.valueOf(new StudentController().getAmountOfStudent()));
            lblSubjectCount.setText(String.valueOf(new SubjectController().getAmountOfSubjects()));
            lblTeacherCount.setText(String.valueOf(new TeacherController().getAmountOfTeachers()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        //create a data serice object
//        XYChart.Series<String,Double> series2020 = new XYChart.Series<>();
//
//        //set name for this series
//        series2020.setName("2020");
//
//        //add data to this serise
//        series2020.getData().add(new XYChart.Data<>("Jan",10.2));
//        series2020.getData().add(new XYChart.Data<>("Feb",5.2));
//        series2020.getData().add(new XYChart.Data<>("Mar",200.3));
//        series2020.getData().add(new XYChart.Data<>("Apr",200.3));
//        series2020.getData().add(new XYChart.Data<>("May",200.3));
//        series2020.getData().add(new XYChart.Data<>("Jun",200.3));
//        series2020.getData().add(new XYChart.Data<>("Jul",200.3));
//        series2020.getData().add(new XYChart.Data<>("Aug",200.3));
//        series2020.getData().add(new XYChart.Data<>("Sep",200.3));
//        series2020.getData().add(new XYChart.Data<>("Oct",200.3));
//        series2020.getData().add(new XYChart.Data<>("Nov",200.3));
//        series2020.getData().add(new XYChart.Data<>("Dec",200.3));
//
//        //add XYchart series to the barchart object
//        BarChart.getData().addAll(series2020);
    }

    private void setDataToTable() {
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherlastname"));

        tblTeacherDetails.setItems(FXCollections.observableArrayList(details));
    }

    public void goToDashBord(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) UserDashBoardTwoForm.getScene().getWindow();
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

    public void goToStudentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserStudentDetailBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }

    public void goToSubjectForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserSubjectForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }

    public void goToPaymentForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }

    public void goToAttendanceForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserAttendanceForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }

    public void goToResultForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserResultForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }

    public void goToScheduleForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/UserScheduleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        UserDashBoardTwoForm.getChildren().clear();
        UserDashBoardTwoForm.getChildren().add(load);
    }
}
