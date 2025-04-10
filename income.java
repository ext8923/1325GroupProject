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

        System.out.print("Rent: ");
        double rent = scanner.nextDouble();

        System.out.print("Groceries: ");
        double groceries = scanner.nextDouble();

        System.out.print("Tution: ");
        double tution = scanner.nextDouble();

        System.out.print("Shopping: ");
        double shopping = scanner.nextDouble();

        System.out.print("Commute: ");
        double commute = scanner.nextDouble();

        System.out.print("Recreation: ");
        double recreation = scanner.nextDouble();

        System.out.print("Remaining(other stuff): ");
        double remaining = scanner.nextDouble();
        
        ExpenseTracker expenses = new ExpenseTracker(rent, groceries, tution, shopping, recreation, commute, remaining);

        double totalSpent = rent + groceries + tution + shopping + commute + recreation + remaining;
        double saving = totalIncome - totalSpent;

        if (saving < 0) {
            System.out.println("\n Warning: YOU ARE OVERSPENDING!");
            saving = 0;
        }

        if (saving > 0) {
            System.out.println("\n Great Job: you are doing great1!");
        }

        expenses.setSaving(saving);
        System.out.println("Your total income is: $" + totalIncome);
        System.out.println("Your total expense are: $" + totalSpent);
        System.out.println("Saving: " + expenses.getSaving());

    }

}
//there is a issue with displaying the saving...
