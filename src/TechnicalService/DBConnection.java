package TechnicalService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gabriele on 18/09/2017.
 */
public class DBConnection {

        //private final static String url = "jdbc:mysql://localhost:3306/";
        private final static String url = "jdbc:mysql://52.29.93.75:3306/";
        private final static String DB_NAME = "adventure";
        private final static String USER = "";
        private final static String PASS = "root";



        public static Connection getConnection(){

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url +
                                DB_NAME,
                        USER,
                        PASS);

                //here footballtable is database name, root is username and password

                return con;
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

        }
}
