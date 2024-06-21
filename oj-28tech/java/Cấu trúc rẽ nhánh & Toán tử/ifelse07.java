import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Reading input
        long a = sc.nextLong();
        long b = sc.nextLong();
        
        // Performing calculations
        long result_one = a / b * b;
        long result_two = (a + b - 1) / b * b;
        
        // Outputting results
        System.out.print(result_one + " " + result_two);

        // Closing scanner (not essential in this case, but good practice)
        sc.close();
    }
}
