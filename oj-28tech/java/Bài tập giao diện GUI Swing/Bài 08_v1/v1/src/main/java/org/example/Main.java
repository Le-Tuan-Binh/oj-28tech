package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static double exchangeRate = 23500.0; // Default exchange rate

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setTitle("Exchange Rate");

        // Components
        JLabel labelUSD = new JLabel("USD:");
        JTextField textUSD = new JTextField(8); // Input for USD

        JLabel labelVND = new JLabel("VND:");
        JTextField textVND = new JTextField(8); // Input for VND

        JLabel labelRate = new JLabel("Exchange Rate:");
        JTextField textRate = new JTextField(String.valueOf(exchangeRate), 10); // Field for exchange rate

        JButton btnUSDToVND = new JButton("USD To VND");
        JButton btnVNDToUSD = new JButton("VND To USD");

        // Action Listeners
        btnUSDToVND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double usd = Double.parseDouble(textUSD.getText());
                    exchangeRate = Double.parseDouble(textRate.getText()); // Update exchange rate
                    double vnd = usd * exchangeRate;
                    textVND.setText(String.format("%.2f", vnd));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVNDToUSD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double vnd = Double.parseDouble(textVND.getText());
                    exchangeRate = Double.parseDouble(textRate.getText()); // Update exchange rate
                    double usd = vnd / exchangeRate;
                    textUSD.setText(String.format("%.2f", usd));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout
        JPanel panelInputs = new JPanel(new GridLayout(3, 2, 5, 5)); // Adjusted for 3 rows
        panelInputs.add(labelUSD);
        panelInputs.add(textUSD);
        panelInputs.add(labelVND);
        panelInputs.add(textVND);
        panelInputs.add(labelRate);
        panelInputs.add(textRate);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Reduced gaps
        panelButtons.add(btnUSDToVND);
        panelButtons.add(btnVNDToUSD);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.add(panelInputs, BorderLayout.CENTER);
        contentPanel.add(panelButtons, BorderLayout.SOUTH);

        frame.setContentPane(contentPanel);
        frame.add(panelInputs, BorderLayout.CENTER);
        frame.add(panelButtons, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
