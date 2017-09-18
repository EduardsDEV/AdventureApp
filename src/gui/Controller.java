package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    void displayActivities() throws SQLException {


        Connection con = TechnicalService.DBConnection.getConnection();
        PreparedStatement prepstmt = con.prepareStatement("SELECT * FROM `Activity`");
        prepstmt.execute();
        ResultSet rs = prepstmt.getResultSet();

        while(rs.next()) {
        }
        con.close();

    }
}
