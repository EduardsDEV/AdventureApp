package domain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created by antonia on 2017/09/18.
 */
public class Admin {
    private String name;
    private String password;

    public Admin(String name, String password){
        this.name = name;
        this.password = password;
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


        @FXML
        void LoadPage(ActionEvent event) throws NoSuchAlgorithmException {

            try {
                Connection con = TechnicalService.DBConnection.getConnection();
                Statement stmt = con.createStatement();

                PreparedStatement prepstmt = con.prepareStatement("SELECT * FROM `users` " +
                        "WHERE `username` = MD5(?) " +
                        "AND password = MD5(?)");

                //prepstmt.setString(1, hashUser);
                //prepstmt.setString(2, hashPass);
                prepstmt.execute();

                ResultSet rs = prepstmt.getResultSet();



                if(rs.first()) {
                    //open page
                }else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Invalid username or password");
                }

            }catch (SQLException e){
            }

        }



}

