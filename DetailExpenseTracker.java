public class DetailExpenseTracker extends ExpenseTracker{
  public DetailedexpenseTracker(double rent, double groceries, double tution, double shopping, double recreation, double commute, double health, double remaining){
    super(rent, groceries, tution, shopping, recreation, commute, health, remaining);
  }

  @Override
  public double totalExpenseExcludingSaving(){
    return super.totalExpenseExcludingSaving();
  }
}
  
