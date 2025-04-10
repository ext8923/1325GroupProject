public class ExpenseTracker extends Finance{

    double rent, groceries, tution, shopping, recreation, commute, health ;
   

    public ExpenseTracker(double rent, double groceries, double tution, double shopping, double recreation,
            double commute, double health) {
        this.rent = rent;
        this.groceries = groceries;
        this.tution = tution;
        this.shopping = shopping;
        this.recreation = recreation;
        this.commute = commute;
        this.health = health;
       ;

    }

    public double totalExpensesExcludingSaving() {
        return rent + groceries + tution + shopping + recreation + commute + health;
    }

    }
