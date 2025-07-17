/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentwellnesssystem.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import studentwellnesssystem.utils.DBConnection;

/**
 *
 * @author herma
 */
public class CounselorController {
    
     public static void addCounselor(String name, String specialization, String availability) throws SQLException {
         //validation
         if (name == null || name.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor name cannot be null or empty. Operation aborted.");
            return; 
        }
        if (specialization == null || specialization.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor specialization cannot be null or empty. Operation aborted.");
            return; 
        }
        if (availability == null || availability.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor availability cannot be null or empty. Operation aborted.");
            return; 
        }
        String sql = "INSERT INTO COUNSELORS (NAME, SPECIALIZATION, AVAILABILITY) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setString(3, availability);
            stmt.executeUpdate();
        }
    }

    public static void deleteCounselor(int id) throws SQLException {
        //validation
        if (id <= 0) {
            System.err.println("Validation Error: Counselor ID must be a positive integer. Operation aborted.");
            return; 
        }
        String sql = "DELETE FROM COUNSELORS WHERE ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void updateCounselor(int id, String name, String specialization, String availability) throws SQLException {
        //validation
        if (id <= 0) {
            System.err.println("Validation Error: Counselor ID must be a positive integer. Operation aborted.");
            return; // Abort operation
        }
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor name cannot be null or empty. Operation aborted.");
            return; 
        }
        if (specialization == null || specialization.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor specialization cannot be null or empty. Operation aborted.");
            return; 
        }
        if (availability == null || availability.trim().isEmpty()) {
            System.err.println("Validation Error: Counselor availability cannot be null or empty. Operation aborted.");
            return; 
        }

        String sql = "UPDATE COUNSELORS SET NAME = ?, SPECIALIZATION = ?, AVAILABILITY = ? WHERE ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setString(3, availability);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }
    
}
