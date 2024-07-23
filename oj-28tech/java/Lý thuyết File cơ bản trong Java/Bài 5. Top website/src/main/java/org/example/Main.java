package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        File file = new File("Log.txt");
        Map<String, Integer> logs = new TreeMap<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] lines = sc.nextLine().split("\\s+");
                String website = lines[lines.length - 1];
                logs.put(website, logs.getOrDefault(website, 0) + 1);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(logs.entrySet());
        Collections.sort(entryList, (o1, o2) -> {
            return o2.getValue() - o1.getValue();
        });
        try {
            FileWriter fileWriter = new FileWriter("Topweb.txt");
            for (Map.Entry<String, Integer> entry : entryList) {
                fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}