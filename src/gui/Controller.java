package gui;

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

    @FXML
    private void showGocart(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is GOCART. Time: 1hr Minimum age: 13" +
                " Equipment will be provided");
        }
    @FXML
    private void showPaintball(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Paintball. Time: 0hr 30 min Minimum age: 16");
    }
    @FXML
    private void showMinigolf(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Minigolf. Time: 2hr Minimum age: 10");
    }
    @FXML
    private void showSumo(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Sumo. Time: 1hr Minimum age: 12");
    }


}

