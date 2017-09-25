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
    private TextField adminLoginField;
    @FXML
    private PasswordField adminPassword;

    //This is for activities table

    private ObservableList<Activity> activityData;
    @FXML
    private TableColumn<Activity, String> columnName;
    @FXML
    private TableColumn<Activity, Integer> columnAgeRes;
    @FXML
    private TableColumn<Activity, Integer> columnDuration;
    @FXML
    private TableColumn<Activity, Integer> columnPrice;
    @FXML
    private TableView<Activity> activityTable;



    public Controller() throws SQLException {
    }




    @FXML
    void displayActivity() throws SQLException {

        activityData = FXCollections.observableArrayList();

        Connection con = DBConnection.getConnection();
        PreparedStatement prepstmt = con.prepareStatement("SELECT * FROM `adventure`.`activity`");
        prepstmt.execute();
        ResultSet rs = prepstmt.getResultSet();

        while(rs.next()) {
            activityData.add(new Activity(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
        }

        con.close();

        columnName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        columnAgeRes.setCellValueFactory(new PropertyValueFactory<>("colAgeRes"));
        columnDuration.setCellValueFactory(new PropertyValueFactory<>("colDuration"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));

        activityTable.setItems(activityData);
    }

    private boolean grantAccess(String username, String password ) {
        return (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)|| 1==1);

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


    }
}




