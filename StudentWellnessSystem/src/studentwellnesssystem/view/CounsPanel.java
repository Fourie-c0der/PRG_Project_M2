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
import studentwellnesssystem.controller.CounselorController;

/**
 *
 * @author herma
 */
public class CounsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public CounsPanel() {
            setLayout(new BorderLayout());

            String[] columns = {"ID", "Name", "Specialization", "Availability"};
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
            addButton.addActionListener(e -> addCounselor());
            deleteButton.addActionListener(e -> deleteCounselor());
            updateButton.addActionListener(e -> updateCounselor());
                            }

    private void addCounselor() {
        JTextField nameField = new JTextField();
        JTextField specializationField = new JTextField();
        JTextField availabilityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);
        panel.add(new JLabel("Availability:"));
        panel.add(availabilityField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Counselor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                CounselorController.addCounselor(
                        nameField.getText(),
                        specializationField.getText(),
                        availabilityField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add counselor: " + e.getMessage());
            }
        }
    }

    private void deleteCounselor() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        try {
            CounselorController.deleteCounselor(id);
            JOptionPane.showMessageDialog(this, "Row deleted succesfully.");
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete counselor: " + e.getMessage());
        }
    }

    private void updateCounselor() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        JTextField nameField = new JTextField((String) model.getValueAt(row, 1));
        JTextField specializationField = new JTextField((String) model.getValueAt(row, 2));
        JTextField availabilityField = new JTextField((String) model.getValueAt(row, 3));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);
        panel.add(new JLabel("Availability:"));
        panel.add(availabilityField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Counselor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                CounselorController.updateCounselor(
                        id,
                        nameField.getText(),
                        specializationField.getText(),
                        availabilityField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update counselor: " + e.getMessage());
            }
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM COUNSELORS")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("SPECIALIZATION"),
                        rs.getString("AVAILABILITY")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading counselors: " + e.getMessage());
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
