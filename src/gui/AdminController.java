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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import java.sql.*;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

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
    @FXML
    private Button editBtt;
    @FXML
    private Button saveBtt;
    @FXML
    private Button deleteBtt;

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

            System.out.println("new activity: " + prepstmt);
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

        while (rs.next()) {
            activityData.add(new Activity(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
        }

        con.close();

        columnName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        columnAgeRes.setCellValueFactory(new PropertyValueFactory<>("colAgeRes"));
        columnDuration.setCellValueFactory(new PropertyValueFactory<>("colDuration"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));

        activityTable.setItems(activityData);
    }

    @FXML
    void editActivities(ActionEvent editEvent) {
        activityTable.setEditable(true);
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAgeRes.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnDuration.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //saves the edited values to the tableview
        columnName.setOnEditCommit(
                (TableColumn.CellEditEvent<Activity, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setColName(t.getNewValue()));
        columnAgeRes.setOnEditCommit(
                (TableColumn.CellEditEvent<Activity, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setColAgeRes(t.getNewValue()));
        columnDuration.setOnEditCommit(
                (TableColumn.CellEditEvent<Activity, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setColDuration(t.getNewValue()));
        columnPrice.setOnEditCommit(
                (TableColumn.CellEditEvent<Activity, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setColPrice(t.getNewValue()));

    }

    @FXML
    void saveUpdates(ActionEvent event) throws SQLException {
        activityTable.setEditable(false);

        int selectedIndex = activityTable.getSelectionModel().getSelectedIndex(); //the index of selected row
        String name = columnName.getCellData(selectedIndex);
        int ageRes = columnAgeRes.getCellData(selectedIndex);
        int duration = columnDuration.getCellData(selectedIndex);
        int price = columnPrice.getCellData(selectedIndex);


        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement prepstmt = con.prepareStatement("UPDATE `adventure`.`activity` " +
                "SET `name` = ?, `AgeRes` = ?, `Duration` = ?, `Price` = ? " +
                "WHERE`activity`.`name` = ?");
        prepstmt.setString(1, name);
        prepstmt.setInt(2, ageRes);
        prepstmt.setInt(3, duration);
        prepstmt.setInt(4, price);
        prepstmt.setString(5, name);

        System.out.println(prepstmt);

        prepstmt.execute();


        con.close();
    }

    @FXML
    void deleteActivity(ActionEvent event) throws SQLException {
        String message = "Do you really want to delete this activity\n"
                + "and all information about it?";
        Object[] options = {"Yes, I do",
                "No I do not!"};

        final JOptionPane optionPane = new JOptionPane(null,
                JOptionPane.QUESTION_MESSAGE,
                YES_NO_OPTION);
        int dialogue = JOptionPane.showOptionDialog(optionPane, message, "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null , options, options[1]);

        if (dialogue == YES_OPTION){
            ObservableList<Activity> activityDelete, activities; //make observable list for specific motorhome, and all motorhomes
            activities = activityTable.getItems();
            activityDelete = activityTable.getSelectionModel().getSelectedItems(); //get the specific row in table that is selected
            int selectedIndex = activityTable.getSelectionModel().getSelectedIndex(); //the index of selected row
            String name = columnName.getCellData(selectedIndex); //license data for the selected row
            //db connection
            Connection con = DBConnection.getConnection();
            PreparedStatement prepstmt = con.prepareStatement("DELETE FROM `adventure`.`activity` \n" +
                    "WHERE `activity`.`name` = ?");
            prepstmt.setString(1, name);
            prepstmt.execute();
            System.out.println("Delete Activity: " + prepstmt);
            System.out.println(" Activity with " + name +  " has been Deleted");
            con.close();
            activityDelete.forEach(activities::remove); // remove specific motorhome from list of all motorhomes
        } else if (dialogue == NO_OPTION) {
            System.out.println("Cancelled");

        }

    }
}

