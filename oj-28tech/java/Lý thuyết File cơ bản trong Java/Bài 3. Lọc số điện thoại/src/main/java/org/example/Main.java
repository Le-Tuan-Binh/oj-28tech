package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    private static final String PHONE_NUMBER_REGEX = "0\\d{8,9}";

    public static String extractPhoneNumber(String data) {
        if (data == null) {
            return "";
        }
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static void main(String[] args) {
        File file = new File("BinhLuan.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("SDT.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String data = sc.next();
            String phoneNumber = extractPhoneNumber(data);
            if (!phoneNumber.isEmpty()) {
                list.add(phoneNumber);
            }
        }

        try {
            for (String phone : list) {
                fileWriter.write(phone + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}