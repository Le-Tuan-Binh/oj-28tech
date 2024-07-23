package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Student {
    private final String name;
    private final String email;
    private final String phone;
    private final double math;
    private final double physic;
    private final double chemistry;

    public Student(String name, String email, String phone, double math, double physic, double chemistry) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.math = math;
        this.physic = physic;
        this.chemistry = chemistry;
    }

    public double getTotalScore() {
        return math + physic + chemistry;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append("\n");
        builder.append(this.email).append("\n");
        builder.append(this.phone).append("\n");
        builder.append(this.getTotalScore()).append("\n");
        return builder.toString();
    }
}

public class Main {

    public static void main(String[] args) {
        File file = new File("DiemThi.txt");
        List<Student> students = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String name = sc.nextLine();
                String email = sc.nextLine();
                String phone = sc.nextLine();
                double[] grades = Arrays.stream(sc.nextLine().split("\\s+")).mapToDouble(x -> Double.parseDouble(x)).toArray();
                students.add(new Student(name, email, phone, grades[0], grades[1], grades[2]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Student> passStudent = students.stream()
                .filter(student -> student.getTotalScore() >= 27.5)
                .collect(Collectors.toList());
        Collections.sort(passStudent, (o1, o2) -> {
            return (int) (o2.getTotalScore() - o1.getTotalScore());
        });

        try {
            FileWriter fileWriter = new FileWriter("TrungTuyen.txt");
            for (int i = 0; i < passStudent.size(); i++) {
                fileWriter.write(passStudent.get(i).toString());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}