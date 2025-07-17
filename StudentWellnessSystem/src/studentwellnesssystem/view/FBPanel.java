/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package studentwellnesssystem.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import studentwellnesssystem.utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import studentwellnesssystem.controller.FeedbackController;

/**
 *
 * @author herma
 */
public class FBPanel extends javax.swing.JPanel {

    /**
     * Creates new form FBPanel
     */
   private JTable table;
    private DefaultTableModel model;

    public FBPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Student Name", "Rating", "Comment"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addFeedback());
        deleteButton.addActionListener(e -> deleteFeedback());
        updateButton.addActionListener(e -> updateFeedback());
    }

    private void addFeedback() {
        JTextField studentNameField = new JTextField();
        JTextField ratingField = new JTextField();
        JTextField commentField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Student Name:"));
        panel.add(studentNameField);
        panel.add(new JLabel("Rating (1-5):"));
        panel.add(ratingField);
        panel.add(new JLabel("Comment:"));
        panel.add(commentField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Feedback", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                FeedbackController.addFeedback(
                        studentNameField.getText(),
                        ratingField.getText(),
                        commentField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add feedback: " + e.getMessage());
            }
        }
    }

    private void deleteFeedback() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        try {
            FeedbackController.deleteFeedback(id);
            JOptionPane.showMessageDialog(this, "Row deleted succesfully.");
            refreshTable();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete feedback: " + e.getMessage());
        }
    }

    private void updateFeedback() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        JTextField studentNameField = new JTextField((String) model.getValueAt(row, 1));
        JTextField ratingField = new JTextField((String) model.getValueAt(row, 2));
        JTextField commentField = new JTextField((String) model.getValueAt(row, 3));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Student Name:"));
        panel.add(studentNameField);
        panel.add(new JLabel("Rating:"));
        panel.add(ratingField);
        panel.add(new JLabel("Comment:"));
        panel.add(commentField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Feedback", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                FeedbackController.updateFeedback(
                        id,
                        studentNameField.getText(),
                        ratingField.getText(),
                        commentField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update feedback: " + e.getMessage());
            }
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FEEDBACK")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("STUDENTNAME"),
                        rs.getString("RATING"),
                        rs.getString("COMMENTS")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading feedback: " + e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
