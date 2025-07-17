package studentwellnesssystem.utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/WellnessDB";
    private static final String  DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String USER = "app";
    private static final String PASSWORD = "app";
    Connection con;

     public static Connection getConnection() throws SQLException {
          Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        return DriverManager.getConnection(URL, props);
    }
    
    
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
