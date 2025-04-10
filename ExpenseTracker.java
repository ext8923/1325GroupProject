public class ExpenseTracker {

    double rent, groceries, tution, shopping, recreation, commute, remaining ;
    private double saving;

    public ExpenseTracker(double rent, double groceries, double tution, double shopping, double recreation,
            double commute, double remaining) {
        this.rent = rent;
        this.groceries = groceries;
        this.tution = tution;
        this.shopping = shopping;
        this.recreation = recreation;
        this.commute = commute;
        this.remaining = remaining;

    }

    public double totalExpensesExcludingSaving() {
        return rent + groceries + tution + shopping + recreation + commute + remaining;
    }
    public void setSaving(double saving){
        this.saving =saving;
    }
    public double getSaving(){
        return saving;
    }
    }
