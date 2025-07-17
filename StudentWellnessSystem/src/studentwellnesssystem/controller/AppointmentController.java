/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentwellnesssystem.controller;

import java.util.ArrayList;
import studentwellnesssystem.utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author herma
 */
public class AppointmentController {
   
    
    public void DisplayAPP(DefaultTableModel model){
       
     try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM APPOINTMENTS")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("STUDENTNAME"),
                        rs.getString("COUNSELORNAME"),
                        rs.getString("APPOINTMENTDATE"),
                        rs.getString("APPOINTMENTTIME"),
                        rs.getString("STATUS")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading Appointments: " + e.getMessage());
        }
    }
    
    
}
