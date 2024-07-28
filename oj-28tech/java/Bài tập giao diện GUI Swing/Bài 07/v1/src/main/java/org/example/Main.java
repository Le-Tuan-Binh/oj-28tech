package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        frame.setTitle("Email Generator");

        JLabel label_v1 = new JLabel("Name List");
        JTextArea jTextArea_v1 = new JTextArea(10, 30);
        jTextArea_v1.setWrapStyleWord(true);
        jTextArea_v1.setLineWrap(true);
        label_v1.setPreferredSize(new Dimension(100, 30));
        jTextArea_v1.setPreferredSize(new Dimension(250, 25));

        JLabel label_v2 = new JLabel("Email List");
        JTextArea jTextArea_v2 = new JTextArea(10, 30);
        jTextArea_v2.setWrapStyleWord(true);
        jTextArea_v2.setLineWrap(true);
        label_v2.setPreferredSize(new Dimension(100, 30));
        jTextArea_v2.setPreferredSize(new Dimension(250, 25));
        jTextArea_v2.setEditable(false); // Make the email text area read-only

        JButton generateButton = new JButton("Generate");
        JButton clearNamesButton = new JButton("Clear Name List");
        JButton clearEmailsButton = new JButton("Clear Email List");

        generateButton.setPreferredSize(new Dimension(100, 30));
        clearNamesButton.setPreferredSize(new Dimension(150, 30));
        clearEmailsButton.setPreferredSize(new Dimension(150, 30));

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameList = jTextArea_v1.getText();
                String emailList = generateEmails(nameList);
                jTextArea_v2.setText(emailList);
            }
        });

        clearNamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea_v1.setText("");
            }
        });

        clearEmailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea_v2.setText("");
            }
        });

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelTop.add(label_v1);
        panelTop.add(jTextArea_v1);

        JPanel panelMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelMiddle.add(label_v2);
        panelMiddle.add(jTextArea_v2);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelButton.add(generateButton);
        panelButton.add(clearNamesButton);
        panelButton.add(clearEmailsButton);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(panelTop);
        frame.add(panelMiddle);
        frame.add(panelButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static String generateEmails(String nameList) {
        String[] names = nameList.split("\\n");
        StringBuilder emailList = new StringBuilder();
        String domain = "@28tech.com.vn";

        for (String name : names) {
            String[] parts = name.trim().split("\\s+");
            if (parts.length > 1) {
                String lastName = parts[parts.length - 1].toLowerCase();
                StringBuilder initials = new StringBuilder();
                for (int i = 0; i < parts.length - 1; i++) {
                    initials.append(parts[i].charAt(0));
                }
                String email = lastName + initials.toString() + domain;
                emailList.append(email).append("\n");
            }
        }

        return emailList.toString();
    }
}
