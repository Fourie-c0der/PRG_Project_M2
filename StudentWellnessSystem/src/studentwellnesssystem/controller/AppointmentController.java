/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentwellnesssystem.controller;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import studentwellnesssystem.utils.DBConnection;

/**
 *
 * @author herma
 */
public class AppointmentController {
    // Define valid date and time formats as constants
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final String[] VALID_STATUSES = {"Scheduled", "Completed", "Cancelled", "Rescheduled"};

   
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
        validateAppointmentData(0, student, counselor, date, time, status, true);
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
         if (id <= 0) {
            throw new IllegalArgumentException("Appointment ID must be a positive integer for deletion.");
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM APPOINTMENTS WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void updateAppointment(int id, String student, String counselor, String date, String time, String status) throws SQLException {
        validateAppointmentData(id, student, counselor, date, time, status, false); // false for update (ID is applicable)

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
//validation method
    private static void validateAppointmentData(int id, String student, String counselor, String date, String time, String status, boolean isNewAppointment) {
        // Validate ID for update/delete contexts
        if (!isNewAppointment && id <= 0) {
            throw new IllegalArgumentException("Appointment ID must be a positive integer for update operations.");
        }

        // Basic Null/Empty String Validation
        if (student == null || student.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (counselor == null || counselor.trim().isEmpty()) {
            throw new IllegalArgumentException("Counselor name cannot be null or empty.");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment date cannot be null or empty.");
        }
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment time cannot be null or empty.");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment status cannot be null or empty.");
        }

        // Specific Date Validation
        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(date.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid appointment date format. Please use YYYY-MM-DD.");
        }
        if (isNewAppointment && appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past for new appointments.");
        } else if (!isNewAppointment && appointmentDate.isBefore(LocalDate.now()) && id > 0 /* only check if an actual ID is passed */ ) {
            
            throw new IllegalArgumentException("Updated appointment date cannot be in the past.");
        }


        // Specific Time Validation
        try {
            LocalTime.parse(time.trim(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid appointment time format. Please use HH:MM (24-hour format, e.g., 14:30).");
        }

        if (!Arrays.asList(VALID_STATUSES).contains(status.trim())) {
            throw new IllegalArgumentException("Invalid appointment status. Allowed values are: " + String.join(", ", VALID_STATUSES) + ".");
        }
    }
    
    
}
