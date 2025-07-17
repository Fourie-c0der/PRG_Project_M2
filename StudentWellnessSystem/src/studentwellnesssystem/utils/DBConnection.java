package studentwellnesssystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/WellnessDB";
    private static final String  DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String USER = "app";
    private static final String PASSWORD = "app";
    Connection con;

    public void connect() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL,USER,PASSWORD);
            if(this.con != null){
                System.out.println("Connected to the database!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
    
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
}
