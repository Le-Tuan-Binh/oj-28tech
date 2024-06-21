import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Reading input
        long a = sc.nextLong(),  b = sc.nextLong();
        
        // Find the largest number <= a that is divisible by b
        long largest_divisible = a / b * b;

        // Find the smallest number >= a that is divisible by b
        long smallest_divisible = (a + b - 1) / b * b;
        
        // Outputting results
        System.out.print(largest_divisible + " " + smallest_divisible);

        // Closing scanner (not essential in this case, but good practice)
        sc.close();
    }
}
