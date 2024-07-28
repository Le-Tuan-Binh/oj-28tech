package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setTitle("Time");


        JLabel label = new JLabel("Time:");
        JTextField jTextField = new JTextField(20);
        label.setPreferredSize(new Dimension(50, 30));
        jTextField.setPreferredSize(new Dimension(250, 25));

        JButton button = new JButton("Display");
        button.setPreferredSize(new Dimension(100, 30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("EEE MMM dd HH:mm:ss ")
                        .appendLiteral("ICT ")
                        .appendPattern("yyyy")
                        .toFormatter(Locale.ENGLISH);

                String formattedNow = now.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).format(formatter);
                jTextField.setText(formattedNow);
            }
        });

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        panelTop.add(label);
        panelTop.add(jTextField);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(button);

        frame.setLayout(new BorderLayout());
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
