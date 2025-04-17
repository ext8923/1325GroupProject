package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.InvalidTransactionException;
import manager.TransactionManager;
import model.Expense;
import model.Income;
import model.Transaction;
import util.DateValidator;

/**
 * Simple test class to verify the functionality of the Expense Tracker
 */
public class TestExpenseTracker {
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Main method to run tests
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting Expense Tracker Tests...");
        
        // Create a test file path - use test file so we don't mess up real data
        String testFilePath = "test_transactions.txt";
        
        try {
            // Test transaction manager
            testTransactionManager(testFilePath);
            
            // Test validation
            testValidation();
            
            System.out.println("\nAll tests completed!");
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test the TransactionManager functionality
     * 
     * @param filePath Test file path
     */
    private static void testTransactionManager(String filePath) {
        System.out.println("\n=== Testing TransactionManager ===");
        
        try {
            // Create a new transaction manager
            TransactionManager manager = new TransactionManager(filePath);
            
            // Test adding transactions
            System.out.println("Testing adding transactions...");
            
            // Add some test income transactions
            Date today = new Date();
            Income income1 = manager.addIncome(today, 1000.0, "Monthly Salary", "Employer", true);
            Income income2 = manager.addIncome(today, 50.0, "Bank Interest", "Bank", true);
            
            // Add some test expense transactions
            Expense expense1 = manager.addExpense(today, 500.0, "Rent Payment", "Housing", true);
            Expense expense2 = manager.addExpense(today, 75.50, "Grocery Shopping", "Food", false);
            
            // Check if transactions were added correctly
            if (manager.getAllTransactions().size() != 4) {
                throw new Exception("Failed to add all transactions. Expected 4, got " + 
                                     manager.getAllTransactions().size());
            }
            
            System.out.println("Added 2 income and 2 expense transactions successfully.");
            
            // Test financial calculations
            System.out.println("\nTesting financial calculations...");
            double totalIncome = manager.calculateTotalIncome();
            double totalExpenses = manager.calculateTotalExpenses();
            double balance = manager.calculateBalance();
            
            System.out.println("Total Income: $" + totalIncome);
            System.out.println("Total Expenses: $" + totalExpenses);
            System.out.println("Balance: $" + balance);
            
            // Verify calculations
            if (Math.abs(totalIncome - 1050.0) > 0.001) {
                throw new Exception("Incorrect total income calculation. Expected 1050.0, got " + totalIncome);
            }
            
            if (Math.abs(totalExpenses - 575.5) > 0.001) {
                throw new Exception("Incorrect total expenses calculation. Expected 575.5, got " + totalExpenses);
            }
            
            if (Math.abs(balance - 474.5) > 0.001) {
                throw new Exception("Incorrect balance calculation. Expected 474.5, got " + balance);
            }
            
            // Test saving and loading transactions
            System.out.println("\nTesting saving transactions...");
            boolean saved = manager.saveTransactions();
            
            if (!saved) {
                throw new Exception("Failed to save transactions.");
            }
            
            System.out.println("Transactions saved successfully.");
            
            // Create a new manager to test loading
            System.out.println("\nTesting loading transactions...");
            TransactionManager newManager = new TransactionManager(filePath);
            
            if (newManager.getAllTransactions().size() != 4) {
                throw new Exception("Failed to load all transactions. Expected 4, got " + 
                                     newManager.getAllTransactions().size());
            }
            
            System.out.println("Loaded " + newManager.getAllTransactions().size() + " transactions successfully.");
            
            // Test editing a transaction
            System.out.println("\nTesting editing transaction...");
            Transaction transaction = newManager.getTransactionById(income1.getId());
            
            if (transaction == null) {
                throw new Exception("Failed to find transaction by ID: " + income1.getId());
            }
            
            double newAmount = 1200.0;
            boolean edited = newManager.editTransaction(transaction.getId(), transaction.getDate(), 
                                                       newAmount, "Updated Salary");
            
            if (!edited) {
                throw new Exception("Failed to edit transaction.");
            }
            
            transaction = newManager.getTransactionById(income1.getId());
            
            if (Math.abs(transaction.getAmount() - newAmount) > 0.001) {
                throw new Exception("Edit transaction failed. Expected amount: " + newAmount + 
                                    ", got: " + transaction.getAmount());
            }
            
            System.out.println("Transaction edited successfully.");
            
            // Test deleting a transaction
            System.out.println("\nTesting deleting transaction...");
            boolean deleted = newManager.deleteTransaction(expense2.getId());
            
            if (!deleted) {
                throw new Exception("Failed to delete transaction.");
            }
            
            if (newManager.getAllTransactions().size() != 3) {
                throw new Exception("Delete failed. Expected 3 transactions, got " + 
                                     newManager.getAllTransactions().size());
            }
            
            System.out.println("Transaction deleted successfully.");
            
            System.out.println("\nTransactionManager tests passed successfully!");
            
        } catch (InvalidTransactionException e) {
            System.out.println("Validation error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Test validation functionality
     */
    private static void testValidation() {
        System.out.println("\n=== Testing Validation ===");
        
        // Test date validation
        System.out.println("Testing date validation...");
        
        try {
            // Valid date
            String validDate = "2023-04-15";
            Date date = DateValidator.validateAndParse(validDate);
            System.out.println("Valid date parsed successfully: " + dateFormat.format(date));
            
            // Invalid date format
            try {
                String invalidDate = "15/04/2023";  // Wrong format
                DateValidator.validateAndParse(invalidDate);
                throw new Exception("Failed to catch invalid date format");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught invalid date format.");
            }
            
            // Invalid date value
            try {
                String invalidDate = "2023-13-45";  // Invalid month and day
                DateValidator.validateAndParse(invalidDate);
                throw new Exception("Failed to catch invalid date value");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught invalid date value.");
            }
            
        } catch (Exception e) {
            System.out.println("Date validation test failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        // Test transaction validation
        System.out.println("\nTesting transaction validation...");
        
        try {
            // Create a test transaction manager
            TransactionManager manager = new TransactionManager("validation_test.txt");
            
            // Test invalid amount (negative)
            try {
                manager.addIncome(new Date(), -100.0, "Test Income", "Test", true);
                throw new Exception("Failed to catch negative amount");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught negative amount.");
            }
            
            // Test invalid amount (zero)
            try {
                manager.addExpense(new Date(), 0.0, "Test Expense", "Test", false);
                throw new Exception("Failed to catch zero amount");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught zero amount.");
            }
            
            // Test empty description
            try {
                manager.addIncome(new Date(), 100.0, "", "Test", true);
                throw new Exception("Failed to catch empty description");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught empty description.");
            }
            
            // Test whitespace-only description
            try {
                manager.addExpense(new Date(), 50.0, "   ", "Test", false);
                throw new Exception("Failed to catch whitespace-only description");
            } catch (InvalidTransactionException e) {
                System.out.println("Correctly caught whitespace-only description.");
            }
            
        } catch (Exception e) {
            if (!(e instanceof InvalidTransactionException)) {
                System.out.println("Transaction validation test failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        
        System.out.println("\nValidation tests passed successfully!");
    }
}