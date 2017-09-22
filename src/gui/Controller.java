package gui;

import TechnicalService.ActivityCRUD;
import domain.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    private static final String ADMIN_USERNAME = "xp";
    private static final String ADMIN_PASSWORD = "xp";
    @FXML
    private Button show1Btn;
    @FXML
    private Button show2Btn;
    @FXML
    private Button show3Btn;
    @FXML
    private Button show4Btn;
    @FXML
    private Button adminLogin;
    @FXML
    private TextField adminLoginField;
    @FXML
    private PasswordField adminPassword;


    int time1 = ActivityCRUD.getFromDB("Duration","GoKart");
    int time2 = ActivityCRUD.getFromDB("Duration","PaintBall");
    int time3;
    int time4;
    int age1= ActivityCRUD.getFromDB("AgeRes","GoKart");
    int age2= ActivityCRUD.getFromDB("AgeRes","PaintBall");
    int age3;
    int age4;

    public Controller() throws SQLException {
    }

    @FXML
    private void showGocart(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is GOCART. Time: "+ time1 +"hr Minimum age:"+ age1 +
                " Equipment will be provided");
        }
    @FXML
    private void showPaintball(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Paintball. Time: "+ time2 +"hr Minimum age:"+ age2);
    }
    @FXML
    private void showMinigolf(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Minigolf. Time: "+ time3 +"hr Minimum age:"+ age3);
    }
    @FXML
    private void showSumo(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Sumo. Time: "+ time4 +"hr Minimum age:"+ age4);
    }

    private boolean grantAccess(String username, String password) {
        return (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) ;
    }

    @FXML
    public void openWindow(ActionEvent event) {
        String admin = adminLoginField.getText();
        String pass = adminPassword.getText();
        if (grantAccess(admin, pass)){
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/adminScene.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            //primaryStage.setResizable(false);
            stage.setResizable(false);
            stage.setTitle("Menu");
            stage.setScene(new Scene(root1,500,500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}else{
            Alert wrongCredentials = new Alert(Alert.AlertType.ERROR);
            wrongCredentials.setTitle("Invalid login");
            wrongCredentials.setHeaderText("Invalid username or password.");
            wrongCredentials.setContentText(null);
            wrongCredentials.showAndWait();
        }

    }
}

