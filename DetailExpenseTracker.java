public class DetailExpenseTracker extends ExpenseTracker {
    public DetailExpenseTracker(double rent, double groceries, double tuition, double shopping, double recreation, 
                                double commute, double health, double remaining) {
        super(rent, groceries, tuition, shopping, recreation, commute, health);
    }

    public double totalExpenseExcludingSaving() {
        double total = super.totalExpensesExcludingSaving();
        double savings = getSaving();  // Ensure this method exists in ExpenseTracker
        System.out.println("Savings: " + savings);
        double totalWithoutSavings = total - savings;
        System.out.println("Total expenses excluding Savings: " + totalWithoutSavings);
        return totalWithoutSavings;  // Fixed issue: No duplicate method call
    }
}
