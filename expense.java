import java.util.Scanner;

public class expense {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expenses in following fields. \n");// asks user to input their expenses on the following.

        System.out.print("Rent: $");
        double rent = scanner.nextDouble();

        System.out.print("Groceries: $");
        double groceries = scanner.nextDouble();

        System.out.print("Tution: $");
        double tution = scanner.nextDouble();

        System.out.print("Shopping: $");
        double shopping = scanner.nextDouble();

        System.out.print("Commute: $");
        double commute = scanner.nextDouble();

        System.out.print("Recreation: $");
        double recreation = scanner.nextDouble();

        System.out.print("Health: $");
        double health = scanner.nextDouble();

        ExpenseTracker expenses = new ExpenseTracker(rent, groceries, tution, shopping, recreation, commute, health);

        double totalSpent = rent + groceries + tution + shopping + commute + recreation + health;
        // double saving = totalIncome - totalSpent; // should be in report

        /*
         * if (saving < 0) { System.out.println("\n Warning: YOU ARE OVERSPENDING!");
         * saving = 0; }
         * 
         * if (saving > 0) { System.out.println("\n Great Job: you are doing great1!");
         * }
         */

        // expenses.setSaving(saving); // should be in report
        // System.out.println("Your total expense are: $" + totalSpent);
        // System.out.println("Saving: " + expenses.getSaving()); // should be in report

        System.out.print("How many more expenses would you like to enter? ");
        int numberOfExpenses = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Arrays to store descriptions and amounts
        String[] descriptions = new String[numberOfExpenses];
        double[] amounts = new double[numberOfExpenses];

        // Loop for entering each expense
        for (int i = 0; i < numberOfExpenses; i++) {
            System.out.println("\nExpense " + (i + 1) + ":");

            System.out.print("Enter description: ");//to input information on where that money was spent on
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
        total = total + totalSpent;
        // Displaying all expenses
        //System.out.println("\n--- Expense Summary ---");
         //for (int i = 0; i < numberOfExpenses; i++) {
       // System.out.printf("%d. %s - $%.2f\n", (i + 1), descriptions[i], amounts[i]);
       // }

        System.out.printf("Total Expenses: $%.2f\n", total); //adds and prints out the total expenses

        scanner.close();
    }
}
