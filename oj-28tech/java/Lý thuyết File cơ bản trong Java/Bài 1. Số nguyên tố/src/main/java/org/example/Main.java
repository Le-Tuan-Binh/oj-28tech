package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean isPrime(int value) {
        for (int i = 2; i * i <= value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return value >= 2;
    }

    public static void main(String[] args) {
        File file = new File("28tech_number.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Integer> primes = new ArrayList<>();
        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            if (isPrime(value)) {
                primes.add(value);
            }
        }
        Collections.sort(primes);
        try {
            FileWriter fileWriter = new FileWriter("28tech_prime.txt");
            for (int i = 0; i < primes.size(); i++) {
                fileWriter.write(primes.get(i) + " ");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}