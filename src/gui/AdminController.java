package gui;

import TechnicalService.DBConnection;
import domain.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by edwar on 9/22/2017.
 */
public class AdminController {

    @FXML
    private TextField activity;
    @FXML
    private TextField minAge;
    @FXML
    private TextField duration;
    @FXML
    private TextField price;
    @FXML
    private Button loadBtt;

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

            System.out.println("new activity: "+prepstmt);
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
}
