import java.util.Scanner;

public class income {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many income sources do you have? ");
        int count = scanner.nextInt();

        double totalIncome = 0;

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter income for source " + i + ": ");
            totalIncome += scanner.nextDouble();
            //totalIncome += income;
        }

       System.out.println("Your total income is: $" + totalIncome);
        
    }

}
