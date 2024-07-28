package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Class", "GPA"};

        // Initial data for the table
        Object[][] data = {
                {"001", "Tran Van Long", "CNTT1", 2.7},
                {"003", "Hoang Thi Lan", "CNTT2", 2.9},
                {"002", "Pham Van Phi", "DTVT1", 3.2},
                {"004", "Nguyen Thu Huong", "CNTT3", 3.1}
        };

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel for the buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the add student form
                JDialog addStudentDialog = new JDialog(frame, "Add Student", true);
                addStudentDialog.setLayout(new BorderLayout());
                addStudentDialog.setLocationRelativeTo(null);

                JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
                formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JTextField idField = new JTextField();
                JTextField nameField = new JTextField();
                JTextField classField = new JTextField();
                JTextField gpaField = new JTextField();

                formPanel.add(new JLabel("ID:"));
                formPanel.add(idField);
                formPanel.add(new JLabel("Name:"));
                formPanel.add(nameField);
                formPanel.add(new JLabel("Class:"));
                formPanel.add(classField);
                formPanel.add(new JLabel("GPA:"));
                formPanel.add(gpaField);

                JButton submitButton = new JButton("Submit");
                formPanel.add(new JLabel());
                formPanel.add(submitButton);

                addStudentDialog.add(formPanel, BorderLayout.CENTER);

                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = idField.getText();
                        String name = nameField.getText();
                        String className = classField.getText();
                        if (id.isEmpty() || name.isEmpty() || className.isEmpty() || gpaField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(addStudentDialog, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            try {
                                double gpa = Double.parseDouble(gpaField.getText());

                                tableModel.addRow(new Object[]{id, name, className, gpa});
                                addStudentDialog.dispose();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(addStudentDialog, "GPA must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                addStudentDialog.setSize(300, 250);
                addStudentDialog.setVisible(true);
            }
        });


        frame.setVisible(true);
    }
}
