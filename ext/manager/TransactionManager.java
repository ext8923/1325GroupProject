package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import exceptions.InvalidTransactionException;
import model.Expense;
import model.Income;
import model.Transaction;
import util.DateValidator;
import util.FileHandler;

/**
 * Manages transactions and handles business logic
 */
public class TransactionManager {
    private ArrayList<Transaction> transactions;
    private LinkedList<Transaction> recentTransactions;
    private FileHandler fileHandler;
    
    /**
     * Constructor
     * 
     * @param filePath Path to the transactions file
     */
    public TransactionManager(String filePath) {
        fileHandler = new FileHandler(filePath);
        transactions = new ArrayList<>();
        recentTransactions = new LinkedList<>();
        
        // Load existing transactions
        loadTransactions();
    }
    
    /**
     * Load transactions from file
     */
    public void loadTransactions() {
        List<Transaction> loadedTransactions = fileHandler.loadTransactions();
        transactions.clear();
        transactions.addAll(loadedTransactions);
        
        // Also update recent transactions (keep last 10)
        updateRecentTransactions();
    }
    
    /**
     * Save transactions to file
     * 
     * @return true if successful, false otherwise
     */
    public boolean saveTransactions() {
        return fileHandler.saveTransactions(transactions);
    }
    
    /**
     * Add a new expense transaction
     * 
     * @param date Transaction date
     * @param amount Transaction amount
     * @param description Transaction description
     * @param category Expense category
     * @param isTaxDeductible Whether expense is tax deductible
     * @return The created expense transaction
     * @throws InvalidTransactionException If transaction data is invalid
     */
    public Expense addExpense(Date date, double amount, String description, 
                              String category, boolean isTaxDeductible) 
                              throws InvalidTransactionException {
        // Validate inputs
        validateTransactionData(amount, description);
        
        Expense expense = new Expense(date, amount, description, category, isTaxDeductible);
        transactions.add(expense);
        updateRecentTransactions();
        return expense;
    }
    
    /**
     * Add a new income transaction
     * 
     * @param date Transaction date
     * @param amount Transaction amount
     * @param description Transaction description
     * @param source Income source
     * @param isTaxable Whether income is taxable
     * @return The created income transaction
     * @throws InvalidTransactionException If transaction data is invalid
     */
    public Income addIncome(Date date, double amount, String description,
                           String source, boolean isTaxable)
                           throws InvalidTransactionException {
        // Validate inputs
        validateTransactionData(amount, description);
        
        Income income = new Income(date, amount, description, source, isTaxable);
        transactions.add(income);
        updateRecentTransactions();
        return income;
    }
    
    /**
     * Delete a transaction by ID
     * 
     * @param id ID of transaction to delete
     * @return true if successful, false if not found
     */
    public boolean deleteTransaction(int id) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                transactions.remove(i);
                updateRecentTransactions();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get a transaction by ID
     * 
     * @param id Transaction ID
     * @return Transaction or null if not found
     */
    public Transaction getTransactionById(int id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                return transaction;
            }
        }
        return null;
    }
    
    /**
     * Edit an existing transaction
     * 
     * @param id Transaction ID
     * @param date New date
     * @param amount New amount
     * @param description New description
     * @return true if successful, false if not found
     * @throws InvalidTransactionException If new data is invalid
     */
    public boolean editTransaction(int id, Date date, double amount, String description) 
            throws InvalidTransactionException {
        // Validate inputs
        validateTransactionData(amount, description);
        
        Transaction transaction = getTransactionById(id);
        if (transaction != null) {
            transaction.setDate(date);
            transaction.setAmount(amount);
            transaction.setDescription(description);
            
            // For Expense
            if (transaction instanceof Expense) {
                return true;
            }
            // For Income
            else if (transaction instanceof Income) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get all transactions
     * 
     * @return List of all transactions
     */
    public ArrayList<Transaction> getAllTransactions() {
        return transactions;
    }
    
    /**
     * Get recent transactions
     * 
     * @return LinkedList of recent transactions
     */
    public LinkedList<Transaction> getRecentTransactions() {
        return recentTransactions;
    }
    
    /**
     * Calculate total income
     * 
     * @return Total income amount
     */
    public double calculateTotalIncome() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Income) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Calculate total expenses
     * 
     * @return Total expense amount
     */
    public double calculateTotalExpenses() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Calculate current balance
     * 
     * @return Current balance (income - expenses)
     */
    public double calculateBalance() {
        return calculateTotalIncome() - calculateTotalExpenses();
    }
    
    /**
     * Calculate total tax deductions from expenses
     * 
     * @return Total tax deduction amount
     */
    public double calculateTaxDeductions() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                total += transaction.calculateTax();
            }
        }
        return total;
    }
    
    /**
     * Calculate total tax on income
     * 
     * @return Total income tax amount
     */
    public double calculateIncomeTax() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Income) {
                total += transaction.calculateTax();
            }
        }
        return total;
    }
    
    /**
     * Inner class for transaction validation
     */
    private class TransactionValidator {
        public void validateAmount(double amount) throws InvalidTransactionException {
            if (amount <= 0) {
                throw new InvalidTransactionException(
                    InvalidTransactionException.getInvalidAmountMessage(amount));
            }
        }
        
        public void validateDescription(String description) throws InvalidTransactionException {
            if (description == null || description.trim().isEmpty()) {
                throw new InvalidTransactionException(
                    InvalidTransactionException.getMissingDescriptionMessage());
            }
        }
    }
    
    /**
     * Validate transaction data
     * 
     * @param amount Transaction amount
     * @param description Transaction description
     * @throws InvalidTransactionException If data is invalid
     */
    private void validateTransactionData(double amount, String description) 
            throws InvalidTransactionException {
        TransactionValidator validator = new TransactionValidator();
        validator.validateAmount(amount);
        validator.validateDescription(description);
    }
    
    /**
     * Update the recent transactions list
     */
    private void updateRecentTransactions() {
        recentTransactions.clear();
        
        // Add up to last 10 transactions in reverse order (newest first)
        int count = 0;
        int size = transactions.size();
        
        for (int i = size - 1; i >= 0 && count < 10; i--) {
            recentTransactions.add(transactions.get(i));
            count++;
        }
    }
}