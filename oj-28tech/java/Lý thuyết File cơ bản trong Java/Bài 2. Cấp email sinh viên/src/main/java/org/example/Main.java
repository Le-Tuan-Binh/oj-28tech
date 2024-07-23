package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Student {
    private final String name;
    private final String dob;

    public Student() {
        this.name = "";
        this.dob = "";
    }

    public Student(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return this.name;
    }

    public String getDob() {
        return this.dob;
    }
}

class StudentEmailService {
    private static final String EMAIL_DOMAIN = "@28land.edu.vn";

    public String generateEmail(Student student) {
        String[] nameParts = splitName(student.getName());
        StringBuilder builder = new StringBuilder();
        builder.append(nameParts[nameParts.length - 1].toLowerCase());
        for (int i = 0; i < nameParts.length - 1; i++) {
            builder.append(Character.toLowerCase(nameParts[i].charAt(0)));
        }
        builder.append(EMAIL_DOMAIN);
        return builder.toString();
    }

    private String[] splitName(String fullName) {
        return fullName.trim().split("\\s+");
    }

    private String[] splitDob(String dob) {
        return dob.trim().split("/");
    }

    public String generatePassword(Student student) {
        String[] dobParts = splitDob(student.getDob());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dobParts.length; i++) {
            builder.append(Integer.parseInt(dobParts[i]));
        }
        return builder.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        File file = new File("SinhVien.txt");
        StudentEmailService emailService = new StudentEmailService();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Student> students = new ArrayList<>();
        while (sc.hasNext()) {
            String name = sc.nextLine();
            String dob = sc.nextLine();
            students.add(new Student(name, dob));
        }

        try {
            FileWriter fileWriter = new FileWriter("Email.txt");
            for (int i = 0; i < students.size(); i++) {
                fileWriter.write(emailService.generateEmail(students.get(i)));
                fileWriter.write("\n");
                fileWriter.write(emailService.generatePassword(students.get(i)));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}