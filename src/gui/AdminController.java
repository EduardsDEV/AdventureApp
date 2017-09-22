package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            System.out.println("new motorhome: "+prepstmt);
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
}
