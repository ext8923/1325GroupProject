public class ExpenseTracker extends Finance {
    double rent, groceries, tuition, shopping, recreation, commute, health;

    public ExpenseTracker(double rent, double groceries, double tuition, double shopping, double recreation,
            double commute, double health) {
        this.rent = rent;
        this.groceries = groceries;
        this.tuition = tuition;
        this.shopping = shopping;
        this.recreation = recreation;
        this.commute = commute;
        this.health = health;
    }

    public double totalExpensesExcludingSaving() {
        return rent + groceries + tuition + shopping + recreation + commute + health;
    }
}
