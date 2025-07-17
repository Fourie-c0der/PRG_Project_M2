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
import studentwellnesssystem.controller.AppointmentController;

/**
 *
 * @author herma
 */
public class AppPanel extends javax.swing.JPanel {

    /**
     * Creates new form AppPanel
     */
    private JTable table;
    private DefaultTableModel model;

    public AppPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Student Name", "Counselor Name", "Appointment Date", "Appointment Time", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        refreshTable();  // Load data into table

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
        addButton.addActionListener(e -> addAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
        updateButton.addActionListener(e -> updateAppointment());
    }

    private void addAppointment() {
        JTextField studentField = new JTextField();
        JTextField counselorField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField statusField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Student Name:"));
        panel.add(studentField);
        panel.add(new JLabel("Counselor Name:"));
        panel.add(counselorField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Time (HH:MM):"));
        panel.add(timeField);
        panel.add(new JLabel("Status:"));
        panel.add(statusField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Appointment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                AppointmentController.addAppointment(
                        studentField.getText(),
                        counselorField.getText(),
                        dateField.getText(),
                        timeField.getText(),
                        statusField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add appointment: " + e.getMessage());
            }
        }
    }

    private void deleteAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        try {
            AppointmentController.deleteAppointment(id);
            JOptionPane.showMessageDialog(this, "Row deleted succesfully.");
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete appointment: " + e.getMessage());
        }
    }

    private void updateAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        JTextField studentField = new JTextField((String) model.getValueAt(row, 1));
        JTextField counselorField = new JTextField((String) model.getValueAt(row, 2));
        JTextField dateField = new JTextField((String) model.getValueAt(row, 3));
        JTextField timeField = new JTextField((String) model.getValueAt(row, 4));
        JTextField statusField = new JTextField((String) model.getValueAt(row, 5));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Student Name:"));
        panel.add(studentField);
        panel.add(new JLabel("Counselor Name:"));
        panel.add(counselorField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Time:"));
        panel.add(timeField);
        panel.add(new JLabel("Status:"));
        panel.add(statusField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Appointment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                AppointmentController.updateAppointment(
                        id,
                        studentField.getText(),
                        counselorField.getText(),
                        dateField.getText(),
                        timeField.getText(),
                        statusField.getText()
                );
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update appointment: " + e.getMessage());
            }
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        DefaultTableModel newModel = AppointmentController.loadAppointments();
        for (int i = 0; i < newModel.getRowCount(); i++) {
            model.addRow(newModel.getDataVector().elementAt(i));
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
