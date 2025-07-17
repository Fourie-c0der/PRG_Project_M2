/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentwellnesssystem.controller;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import studentwellnesssystem.utils.DBConnection;

/**
 *
 * @author herma
 */
public class AppointmentController {
   
     public static DefaultTableModel loadAppointments() {
        String[] columns = {"ID", "Student Name", "Counselor Name", "Appointment Date", "Appointment Time", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

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
        }
        return model;
    }

    public static void addAppointment(String student, String counselor, String date, String time, String status) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO APPOINTMENTS (STUDENTNAME, COUNSELORNAME, APPOINTMENTDATE, APPOINTMENTTIME, STATUS) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, student);
            stmt.setString(2, counselor);
            stmt.setString(3, date);
            stmt.setString(4, time);
            stmt.setString(5, status);
            stmt.executeUpdate();
        }
    }
    
     public static void deleteAppointment(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM APPOINTMENTS WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void updateAppointment(int id, String student, String counselor, String date, String time, String status) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE APPOINTMENTS SET STUDENTNAME=?, COUNSELORNAME=?, APPOINTMENTDATE=?, APPOINTMENTTIME=?, STATUS=? WHERE ID=?")) {
            stmt.setString(1, student);
            stmt.setString(2, counselor);
            stmt.setString(3, date);
            stmt.setString(4, time);
            stmt.setString(5, status);
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    
    
    
}
