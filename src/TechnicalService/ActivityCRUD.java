package TechnicalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by edwar on 9/18/2017.
 */
public class ActivityCRUD {


    public static int getFromDB(String field,String activityName)throws SQLException{

        Connection con = TechnicalService.DBConnection.getConnection();
        PreparedStatement prepstmt = con.prepareStatement("SELECT `"+field+"` FROM `activity` WHERE `NAME`='"+activityName+"'");
        ResultSet rs = prepstmt.executeQuery();
        rs.next();
       return rs.getInt(1);
    }
}
