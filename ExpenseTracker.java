public class DetailExpenseTracker extends Finance {
    double rent, groceries, tuition, shopping, recreation, commute, health, remaining;

    public DetailExpenseTracker(double rent, double groceries, double tuition, double shopping, double recreation, 
                                double commute, double health, double remaining) {
        this.rent = rent;
        this.groceries = groceries;
        this.tuition = tuition;
        this.shopping = shopping;
        this.recreation = recreation;
        this.commute = commute;
        this.health = health;
        this.remaining = remaining;
    }

    public double totalExpensesExcludingSaving() {
        return rent + groceries + tuition + shopping + recreation + commute + health;
    }
}
