public class ExpenseTracker {

    double rent, groceries, tution, shopping, recreation, commute, health, remaining ;
    private double saving;

    public ExpenseTracker(double rent, double groceries, double tution, double shopping, double recreation,
            double commute, double health, double remaining) {
        this.rent = rent;
        this.groceries = groceries;
        this.tution = tution;
        this.shopping = shopping;
        this.recreation = recreation;
        this.commute = commute;
        this.health = health;
        this.remaining = remaining;

    }

    public double totalExpensesExcludingSaving() {
        return rent + groceries + tution + shopping + recreation + commute + health + remaining;
    }
    public void setSaving(double saving){
        this.saving =saving;
    }
    public double getSaving(){
        return saving;
    }
    }
