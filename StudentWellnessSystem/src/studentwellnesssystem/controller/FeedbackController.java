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
public class FeedbackController {

    public static void addFeedback(String studentName, String rating, String comment) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO FEEDBACK (STUDENTNAME, RATING, COMMENTS) VALUES (?, ?, ?)")) {
            stmt.setString(1, studentName);
            stmt.setString(2, rating);
            stmt.setString(3, comment);
            stmt.executeUpdate();
        }
    }

    public static void deleteFeedback(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM FEEDBACK WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void updateFeedback(int id, String studentName, String rating, String comment) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE FEEDBACK SET STUDENTNAME = ?, RATING = ?, COMMENTS = ? WHERE ID = ?")) {
            stmt.setString(1, studentName);
            stmt.setString(2, rating);
            stmt.setString(3, comment);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }
}