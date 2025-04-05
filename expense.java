import java.util.Scanner;

public class ExpensePage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many expenses would you like to enter? ");
        int numberOfExpenses = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Arrays to store descriptions and amounts
        String[] descriptions = new String[numberOfExpenses];
        double[] amounts = new double[numberOfExpenses];

        // Loop for entering each expense
        for (int i = 0; i < numberOfExpenses; i++) {
            System.out.println("\nExpense " + (i + 1) + ":");

            
            System.out.print("Enter description: ");
            descriptions[i] = scanner.nextLine();

            
            System.out.print("Enter amount: $");
            amounts[i] = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline
        }

        // Calculate total expenses
        double total = 0;
        for (double amount : amounts) {
            total += amount;
        }

        // Displaying all expenses
        System.out.println("\n--- Expense Summary ---");
        for (int i = 0; i < numberOfExpenses; i++) {
            System.out.printf("%d. %s - $%.2f\n", (i + 1), descriptions[i], amounts[i]);
        }

       
        System.out.printf("Total Expenses: $%.2f\n", total);

        
        scanner.close();
    }
}
