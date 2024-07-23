package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String id;
    private String name;
    private String address;
    private String className;

    public Student(String id, String name, String address, String className) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return id + "\n" + name + "\n" + address + "\n" + className;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = readStudentsFromFile("student.txt");
        writeStudentsToBinaryFile(students, "student.bin");

        List<Student> updatedStudents = readStudentsFromBinaryFile("student.bin");
        updateStudentInfo(updatedStudents, "CNTT1", "Hoang Van Loc", "Thai Binh", "IT2");
        writeStudentsToBinaryFile(updatedStudents, "student.bin");

        printStudentsFromBinaryFile("student.bin");
    }

    private static List<Student> readStudentsFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String id;
            while ((id = br.readLine()) != null) {
                String name = br.readLine();
                String address = br.readLine();
                String className = br.readLine();
                students.add(new Student(id, name, address, className));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void writeStudentsToBinaryFile(List<Student> students, String fileName) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            for (Student student : students) {
                dos.writeUTF(student.getId());
                dos.writeUTF(student.getName());
                dos.writeUTF(student.getAddress());
                dos.writeUTF(student.getClassName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Student> readStudentsFromBinaryFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (dis.available() > 0) {
                String id = dis.readUTF();
                String name = dis.readUTF();
                String address = dis.readUTF();
                String className = dis.readUTF();
                students.add(new Student(id, name, address, className));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void updateStudentInfo(List<Student> students, String targetId, String newName, String newAddress, String newClassName) {
        for (Student student : students) {
            if (student.getId().equals(targetId)) {
                student.setName(newName);
                student.setAddress(newAddress);
                student.setClassName(newClassName);
            }
        }
    }

    private static void printStudentsFromBinaryFile(String fileName) {
        List<Student> students = readStudentsFromBinaryFile(fileName);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
