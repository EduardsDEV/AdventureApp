package gui;

import TechnicalService.ActivityCRUD;
import domain.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Minigolf. Time: "+ time3 +"hr Minimum age: 10");
    }
    @FXML
    private void showSumo(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Sumo. Time: "+ time4 +"hr Minimum age: 12");
    }


}

