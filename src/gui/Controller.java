package gui;

import TechnicalService.ActivityCRUD;
import TechnicalService.DBConnection;
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

import javax.swing.*;
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


    int time1 = ActivityCRUD.getFromDB("Duration", "GoKart");
    int time2 = ActivityCRUD.getFromDB("Duration", "PaintBall");
    int time3;
    int time4;
    int age1 = ActivityCRUD.getFromDB("AgeRes", "GoKart");
    int age2 = ActivityCRUD.getFromDB("AgeRes", "PaintBall");
    int age3;
    int age4;

    public Controller() throws SQLException {
    }

    @FXML
    private void showGocart(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is GOCART. Time: " + time1 + "hr Minimum age:" + age1 +
                " Equipment will be provided");
    }

    @FXML
    private void showPaintball(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Paintball. Time: " + time2 + "hr Minimum age:" + age2);
    }

    @FXML
    private void showMinigolf(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Minigolf. Time: " + time3 + "hr Minimum age:" + age3);
    }

    @FXML
    private void showSumo(ActionEvent actionEvent) {
        SceneManager.getInstance().displayInformation("Adventure info", null, "This is Sumo. Time: " + time4 + "hr Minimum age:" + age4);
    }

    private boolean grantAccess(String username, String password) {
        return (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password));
    }

    @FXML
    public void openWindow(ActionEvent event) {
        String admin = adminLoginField.getText();
        String pass = adminPassword.getText();
        if (grantAccess(admin, pass)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/adminScene.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                //primaryStage.setResizable(false);
                stage.setResizable(false);
                stage.setTitle("Menu");
                stage.setScene(new Scene(root1, 500, 500));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert wrongCredentials = new Alert(Alert.AlertType.ERROR);
            wrongCredentials.setTitle("Invalid login");
            wrongCredentials.setHeaderText("Invalid username or password.");
            wrongCredentials.setContentText(null);
            wrongCredentials.showAndWait();
        }




    }


    @FXML
    private TextField activity;
    @FXML
    private TextField minAge;
    @FXML
    private TextField duration;
    @FXML
    private TextField price;


    @FXML
    void addNewActivity(ActionEvent event) throws SQLException {
        String newActivity = activity.getText();
        String newMinAge = minAge.getText();
        String newDuration = duration.getText();
        String newPrice = price.getText();


        try {
            Connection con = TechnicalService.DBConnection.getConnection();

            PreparedStatement prepstmt = con.prepareStatement("INSERT INTO activity VALUES (?, ?, ?, ?)");
            prepstmt.setString(1, newActivity);
            prepstmt.setString(2, newMinAge);
            prepstmt.setString(3, newDuration);
            prepstmt.setString(4, newPrice);

            System.out.println("new motorhome: " + prepstmt);
            prepstmt.execute();

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Saved Activity to database");
            activity.clear();
            minAge.clear();
            duration.clear();
            price.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField activityName;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField numOfPeople;
    @FXML
    private Button bookBtn;



    @FXML
    void addNewReservation(ActionEvent event) throws SQLException {

        String newActivity = activityName.getText();
        String newName = name.getText();
        String newAge = age.getText();
        String newEmail = email.getText();
        String newPhone = phone.getText();
        String newNumOfPeople = numOfPeople.getText();

        try {
            Connection con = TechnicalService.DBConnection.getConnection();

            PreparedStatement prepstmt = con.prepareStatement("INSERT INTO Reservations VALUES (?, ?, ?, ?, ?, ?)");
            prepstmt.setString(1, newActivity);
            prepstmt.setString(2, newName);
            prepstmt.setString(3, newAge);
            prepstmt.setString(4, newEmail);
            prepstmt.setString(5, newPhone);
            prepstmt.setString(6, newNumOfPeople);

            System.out.println("new reservation: " + prepstmt);
            prepstmt.execute();

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Saved Reservation to database");
            activityName.clear();
            name.clear();
            age.clear();
            email.clear();
            numOfPeople.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("see i pushed");

    }
}




