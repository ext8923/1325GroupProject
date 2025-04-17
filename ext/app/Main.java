package app;

/**
 * Main entry point class for the Expense Tracker application
 */
public class Main {
    
    /**
     * Main method to start the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting Personal Finance Expense Tracker...");
        
        try {
            // Create and start the expense tracker application
            ExpenseTrackerApp app = new ExpenseTrackerApp();
            app.start();
        } catch (Exception e) {
            // Catch any uncaught exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            System.out.println("The application will now exit.");
        }
    }
}