/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentwellnesssystem;
import java.sql.*;
import studentwellnesssystem.utils.DBConnection;
/**
 *
 * @author vande
 */
public class SystemWellnessSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try {
        Connection conn = DBConnection.getConnection();
        System.out.println("Connected successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
}
