package org.example;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class EmailGenerateContent {
    public static String generateEmailContent(Customer customer) {
        return String.format(
                "Cảm ơn %s đã tin tưởng 28shop, đơn hàng %s của bạn đã được giao thành công vào ngày %s.\nBest!",
                customer.getName(), customer.getProduct(), customer.getDob()
        );
    }
}

class Customer {
    private String name;
    private String dob;
    private String product;

    public Customer(String name, String dob, String product) {
        this.name = name;
        this.dob = dob;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

public class Main {

    public static void main(String[] args) {
        File file = new File("Infor.txt");
        List<Customer> customers = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String name = sc.nextLine();
                String dob = sc.nextLine();
                String product = sc.nextLine();
                customers.add(new Customer(name, dob, product));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter("Camon.txt");
            for (Customer customer : customers) {
                String emailContent = EmailGenerateContent.generateEmailContent(customer);
                fileWriter.write(emailContent + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}