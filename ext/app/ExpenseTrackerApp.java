package app;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import exceptions.InvalidTransactionException;
import manager.TransactionManager;
import model.Expense;
import model.Income;
import model.Transaction;
import util.DateValidator;

/**
 * Main application class for the Expense Tracker
 */
public class ExpenseTrackerApp {
    
    private TransactionManager transactionManager;
    private Scanner scanner;
    private boolean running;
    private DecimalFormat currencyFormat;
    
    /**
     * Constructor
     */
    public ExpenseTrackerApp() {
        // Initialize with the file path for storing transactions
        transactionManager = new TransactionManager("transactions.txt");
        scanner = new Scanner(System.in);
        running = false;
        currencyFormat = new DecimalFormat("$#,##0.00");
    }
    
    /**
     * Start the application
     */
    public void start() {
        running = true;
        showWelcomeMessage();
        
        while (running) {
            showMainMenu();
            int choice = getMenuChoice(1, 9);
            
            try {
                processMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
        System.out.println("Thank you for using the Expense Tracker. Goodbye!");
    }
    
    /**
     * Show welcome message
     */
    private void showWelcomeMessage() {
        System.out.println("==================================");
        System.out.println("=== PERSONAL EXPENSE TRACKER ====");
        System.out.println("==================================");
        System.out.println("Welcome to your personal finance manager!");
        System.out.println();
    }
    
    /**
     * Show main menu
     */
    private void showMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add New Income");
        System.out.println("2. Add New Expense");
        System.out.println("3. View All Transactions");
        System.out.println("4. View Recent Transactions");
        System.out.println("5. View Financial Summary");
        System.out.println("6. Edit Transaction");
        System.out.println("7. Delete Transaction");
        System.out.println("8. Save Transactions");
        System.out.println("9. Exit");
        System.out.print("Enter your choice (1-9): ");
    }
    
    /**
     * Get menu choice from user
     * 
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @return User's choice
     */
    private int getMenuChoice(int min, int max) {
        int choice = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + 
                                     min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return choice;
    }
    
    /**
     * Process user's menu choice
     * 
     * @param choice User's menu choice
     */
    private void processMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addIncome();
                break;
            case 2:
                addExpense();
                break;
            case 3:
                viewAllTransactions();
                break;
            case 4:
                viewRecentTransactions();
                break;
            case 5:
                viewFinancialSummary();
                break;
            case 6:
                editTransaction();
                break;
            case 7:
                deleteTransaction();
                break;
            case 8:
                saveTransactions();
                break;
            case 9:
                exit();
                break;
        }
    }
    
    /**
     * Add a new income transaction
     */
    private void addIncome() {
        System.out.println("\n===== ADD NEW INCOME =====");
        
        // Get date
        Date date = getDateInput("Enter date (YYYY-MM-DD) or press Enter for today: ");
        
        // Get amount
        double amount = getDoubleInput("Enter amount: $", 0.01);
        
        // Get description
        String description = getStringInput("Enter description: ");
        
        // Get source
        String source = getStringInput("Enter source (e.g., Salary, Gift): ");
        
        // Get taxable status
        boolean isTaxable = getBooleanInput("Is this income taxable? (Y/N): ");
        
        try {
            Income income = transactionManager.addIncome(date, amount, description, source, isTaxable);
            System.out.println("\nIncome added successfully!");
            System.out.println("Transaction ID: " + income.getId());
        } catch (InvalidTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Add a new expense transaction
     */
    private void addExpense() {
        System.out.println("\n===== ADD NEW EXPENSE =====");
        
        // Get date
        Date date = getDateInput("Enter date (YYYY-MM-DD) or press Enter for today: ");
        
        // Get amount
        double amount = getDoubleInput("Enter amount: $", 0.01);
        
        // Get description
        String description = getStringInput("Enter description: ");
        
        // Get category
        String category = getStringInput("Enter category (e.g., Food, Transportation): ");
        
        // Get tax deductible status
        boolean isTaxDeductible = getBooleanInput("Is this expense tax deductible? (Y/N): ");
        
        try {
            Expense expense = transactionManager.addExpense(date, amount, description, category, isTaxDeductible);
            System.out.println("\nExpense added successfully!");
            System.out.println("Transaction ID: " + expense.getId());
        } catch (InvalidTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * View all transactions
     */
    private void viewAllTransactions() {
        System.out.println("\n===== ALL TRANSACTIONS =====");
        
        if (transactionManager.getAllTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactionManager.getAllTransactions()) {
                transaction.printDetails();
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * View recent transactions
     */
    private void viewRecentTransactions() {
        System.out.println("\n===== RECENT TRANSACTIONS =====");
        
        LinkedList<Transaction> recentTransactions = transactionManager.getRecentTransactions();
        
        if (recentTransactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : recentTransactions) {
                transaction.printDetails();
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * View financial summary
     */
    private void viewFinancialSummary() {
        System.out.println("\n===== FINANCIAL SUMMARY =====");
        
        double totalIncome = transactionManager.calculateTotalIncome();
        double totalExpenses = transactionManager.calculateTotalExpenses();
        double balance = transactionManager.calculateBalance();
        double taxDeductions = transactionManager.calculateTaxDeductions();
        double incomeTax = transactionManager.calculateIncomeTax();
        
        System.out.println("Total Income: " + currencyFormat.format(totalIncome));
        System.out.println("Total Expenses: " + currencyFormat.format(totalExpenses));
        System.out.println("Current Balance: " + currencyFormat.format(balance));
        System.out.println("Potential Tax Deductions: " + currencyFormat.format(taxDeductions));
        System.out.println("Estimated Income Tax: " + currencyFormat.format(incomeTax));
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Edit a transaction
     */
    private void editTransaction() {
        System.out.println("\n===== EDIT TRANSACTION =====");
        
        // Get transaction ID
        int id = getIntInput("Enter transaction ID to edit: ", 1);
        
        Transaction transaction = transactionManager.getTransactionById(id);
        
        if (transaction == null) {
            System.out.println("Transaction not found.");
        } else {
            System.out.println("\nCurrent transaction details:");
            transaction.printDetails();
            
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            // Get new date
            Date newDate = getOptionalDateInput("Enter new date (YYYY-MM-DD): ", transaction.getDate());
            
            // Get new amount
            double newAmount = getOptionalDoubleInput("Enter new amount: $", transaction.getAmount(), 0.01);
            
            // Get new description
            String newDescription = getOptionalStringInput("Enter new description: ", transaction.getDescription());
            
            try {
                boolean success = transactionManager.editTransaction(id, newDate, newAmount, newDescription);
                
                if (success) {
                    System.out.println("\nTransaction updated successfully!");
                } else {
                    System.out.println("\nFailed to update transaction.");
                }
            } catch (InvalidTransactionException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Delete a transaction
     */
    private void deleteTransaction() {
        System.out.println("\n===== DELETE TRANSACTION =====");
        
        // Get transaction ID
        int id = getIntInput("Enter transaction ID to delete: ", 1);
        
        Transaction transaction = transactionManager.getTransactionById(id);
        
        if (transaction == null) {
            System.out.println("Transaction not found.");
        } else {
            System.out.println("\nTransaction details:");
            transaction.printDetails();
            
            boolean confirm = getBooleanInput("\nAre you sure you want to delete this transaction? (Y/N): ");
            
            if (confirm) {
                boolean success = transactionManager.deleteTransaction(id);
                
                if (success) {
                    System.out.println("\nTransaction deleted successfully!");
                } else {
                    System.out.println("\nFailed to delete transaction.");
                }
            } else {
                System.out.println("\nDeletion cancelled.");
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Save transactions to file
     */
    private void saveTransactions() {
        System.out.println("\n===== SAVE TRANSACTIONS =====");
        
        boolean success = transactionManager.saveTransactions();
        
        if (success) {
            System.out.println("Transactions saved successfully!");
        } else {
            System.out.println("Failed to save transactions.");
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Exit the application
     */
    private void exit() {
        System.out.println("\n===== EXIT =====");
        
        boolean saveChanges = getBooleanInput("Do you want to save changes before exiting? (Y/N): ");
        
        if (saveChanges) {
            boolean success = transactionManager.saveTransactions();
            
            if (success) {
                System.out.println("Transactions saved successfully!");
            } else {
                System.out.println("Failed to save transactions.");
                
                boolean continueExit = getBooleanInput("Continue with exit anyway? (Y/N): ");
                
                if (!continueExit) {
                    return;
                }
            }
        }
        
        running = false;
    }
    
    /**
     * Get date input from user
     * 
     * @param prompt Prompt message
     * @return Date object
     */
    private Date getDateInput(String prompt) {
        Date date = null;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                // Use today's date if empty
                date = new Date();
                valid = true;
            } else {
                try {
                    date = DateValidator.validateAndParse(input);
                    valid = true;
                } catch (InvalidTransactionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        
        return date;
    }
    
    /**
     * Get optional date input from user
     * 
     * @param prompt Prompt message
     * @param defaultValue Default value if input is empty
     * @return Date object
     */
    private Date getOptionalDateInput(String prompt, Date defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            return defaultValue;
        } else {
            try {
                return DateValidator.validateAndParse(input);
            } catch (InvalidTransactionException e) {
                System.out.println("Error: " + e.getMessage() + " Using original value.");
                return defaultValue;
            }
        }
    }
    
    /**
     * Get double input from user
     * 
     * @param prompt Prompt message
     * @param min Minimum valid value
     * @return Double value
     */
    private double getDoubleInput(String prompt, double min) {
        double value = 0;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                
                if (value >= min) {
                    valid = true;
                } else {
                    System.out.println("Value must be at least " + min + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        return value;
    }
    
    /**
     * Get optional double input from user
     * 
     * @param prompt Prompt message
     * @param defaultValue Default value if input is empty
     * @param min Minimum valid value
     * @return Double value
     */
    private double getOptionalDoubleInput(String prompt, double defaultValue, double min) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            return defaultValue;
        } else {
            try {
                double value = Double.parseDouble(input);
                
                if (value >= min) {
                    return value;
                } else {
                    System.out.println("Value must be at least " + min + ". Using original value.");
                    return defaultValue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using original value.");
                return defaultValue;
            }
        }
    }
    
    /**
     * Get integer input from user
     * 
     * @param prompt Prompt message
     * @param min Minimum valid value
     * @return Integer value
     */
    private int getIntInput(String prompt, int min) {
        int value = 0;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                
                if (value >= min) {
                    valid = true;
                } else {
                    System.out.println("Value must be at least " + min + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        return value;
    }
    
    /**
     * Get string input from user
     * 
     * @param prompt Prompt message
     * @return String value (non-empty)
     */
    private String getStringInput(String prompt) {
        String value = "";
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            
            if (!value.isEmpty()) {
                valid = true;
            } else {
                System.out.println("Input cannot be empty.");
            }
        }
        
        return value;
    }
    
    /**
     * Get optional string input from user
     * 
     * @param prompt Prompt message
     * @param defaultValue Default value if input is empty
     * @return String value
     */
    private String getOptionalStringInput(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        
        return input.isEmpty() ? defaultValue : input;
    }
    
    /**
     * Get boolean input from user (Y/N)
     * 
     * @param prompt Prompt message
     * @return Boolean value
     */
    private boolean getBooleanInput(String prompt) {
        boolean value = false;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.equals("Y") || input.equals("YES")) {
                value = true;
                valid = true;
            } else if (input.equals("N") || input.equals("NO")) {
                value = false;
                valid = true;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
        
        return value;
    }
    
    /**
     * Main method
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        ExpenseTrackerApp app = new ExpenseTrackerApp();
        app.start();
    }
}