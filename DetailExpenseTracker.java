public class DetailExpenseTracker extends ExpenseTracker{
  public DetailedExpenseTracker(double rent, double groceries, double tution, double shopping, double recreation, double commute, double health, double remaining){
    super(rent, groceries, tution, shopping, recreation, commute, health);
  }

  @Override
  public double totalExpenseExcludingSaving(){
    double total = super.totalExpensesExcludingSaving();
     double savings = getSaving();
    System.out.println("Savings: " + savings);
    double totalWithoutSavings = total - savings;
    return super.totalExpenseExcludingSaving();
    System.out.println("Total expenses excluding Savings:" +totalWithoutSavings);
    return totalWithoutSavings;
  }
}
  
