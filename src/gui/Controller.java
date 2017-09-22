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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

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


    int time1 = ActivityCRUD.getFromDB("GoKart");
    int time2 = ActivityCRUD.getFromDB("PaintBall");
    int time3;
    int time4;

    public Controller() throws SQLException {
    }

    @FXML
    private void showGocart(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is GOCART. Time: "+ time1 +"hr Minimum age: 13" +
                " Equipment will be provided");
        }
    @FXML
    private void showPaintball(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Paintball. Time: "+ time2 +"hr Minimum age: 16");
    }
    @FXML
    private void showMinigolf(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Minigolf. Time: "+ time3 +"hr Minimum age: 10");
    }
    @FXML
    private void showSumo(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Sumo. Time: "+ time4 +"hr Minimum age: 12");
    }

    @FXML
    private void adminLogin(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().loadAdministratorScene();
    }

    @FXML
    public void openWindow(ActionEvent event) {
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
        }

    }
}

