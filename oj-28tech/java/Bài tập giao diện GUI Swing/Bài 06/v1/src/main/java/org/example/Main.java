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
        frame.setTitle("Caesar Cipher");

        JLabel label_v1 = new JLabel("Plain Text");
        JTextArea jTextArea_v1 = new JTextArea(10, 30);
        jTextArea_v1.setWrapStyleWord(true);
        jTextArea_v1.setLineWrap(true);
        label_v1.setPreferredSize(new Dimension(100, 30));
        jTextArea_v1.setPreferredSize(new Dimension(250, 25));

        JLabel label_v2 = new JLabel("Cipher Text");
        JTextArea jTextArea_v2 = new JTextArea(10, 30);
        jTextArea_v2.setWrapStyleWord(true);
        jTextArea_v2.setLineWrap(true);
        label_v2.setPreferredSize(new Dimension(100, 30));
        jTextArea_v2.setPreferredSize(new Dimension(250, 25));
        jTextArea_v2.setEditable(false); // Make the cipher text area read-only

        JButton button = new JButton("Encode");
        button.setPreferredSize(new Dimension(100, 30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plainText = jTextArea_v1.getText();
                String cipherText = encodeCaesarCipher(plainText, 7);
                jTextArea_v2.setText(cipherText);
            }
        });

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        panelTop.add(label_v1);
        panelTop.add(jTextArea_v1);

        JPanel panelMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        panelMiddle.add(label_v2);
        panelMiddle.add(jTextArea_v2);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(button);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(panelTop);
        frame.add(panelMiddle);
        frame.add(panelButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static String encodeCaesarCipher(String text, int shift) {
        StringBuilder encoded = new StringBuilder();
        shift = shift % 26 + 26;
        for (char i : text.toCharArray()) {
            if (Character.isLetter(i)) {
                char base = Character.isUpperCase(i) ? 'A' : 'a';
                encoded.append((char) ((i - base + shift) % 26 + base));
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }
}
