package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static List<String> normalizeNames(List<String> names) {
        return names.stream()
                .map(name -> {
                    String[] parts = name.split("\\s+");
                    return Arrays.stream(parts)
                            .map(part -> part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase())
                            .collect(Collectors.joining(" "));
                })
                .collect(Collectors.toList());
    }

    private static List<String> sortNames(List<String> names) {
        return names.stream()
                .sorted((name1, name2) -> {
                    String[] parts1 = name1.split("\\s+");
                    String[] parts2 = name2.split("\\s+");
                    String lastName1 = parts1[parts1.length - 1];
                    String lastName2 = parts2[parts2.length - 1];
                    int cmp = lastName1.compareTo(lastName2);
                    if (cmp != 0) {
                        return cmp;
                    }
                    return name1.compareTo(name2);
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file = new File("Name.txt");
        List<String> users = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                users.add(sc.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> normalizedNames = normalizeNames(users);

        List<String> sortedNames = sortNames(normalizedNames);
        try {
            FileWriter fileWriter = new FileWriter("Convert.txt");
            for (String user : sortedNames) {
                fileWriter.write(user + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}