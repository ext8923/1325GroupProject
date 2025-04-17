package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.InvalidTransactionException;
import model.Expense;
import model.Income;
import model.Transaction;

/**
 * Handles reading from and writing to the transactions file
 */
public class FileHandler {
    // File path for storing transactions
    private String filePath;
    
    /**
     * Constructor that takes a file path
     * 
     * @param filePath Path to the transactions file
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
        
        // Create file if it doesn't exist
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    
    /**
     * Loads transactions from the file
     * 
     * @return List of transactions
     */
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        Transaction transaction = parseTransaction(line);
                        transactions.add(transaction);
                    } catch (InvalidTransactionException e) {
                        System.out.println("Error parsing transaction: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return transactions;
    }
    
    /**
     * Saves transactions to the file
     * 
     * @param transactions List of transactions to save
     * @return true if successful, false otherwise
     */
    public boolean saveTransactions(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toFileString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Parses a line from the file into a Transaction object
     * 
     * @param line Line from the file
     * @return Transaction object
     * @throws InvalidTransactionException If parsing fails
     */
    private Transaction parseTransaction(String line) throws InvalidTransactionException {
        String[] parts = line.split(",");
        
        if (parts.length < 5) {
            throw new InvalidTransactionException("Invalid transaction format: " + line);
        }
        
        String type = parts[0];
        int id = Integer.parseInt(parts[1]);
        Date date = DateValidator.validateAndParse(parts[2]);
        double amount = Double.parseDouble(parts[3]);
        String description = parts[4];
        
        if (type.equals("EXPENSE") && parts.length >= 7) {
            String category = parts[5];
            boolean isTaxDeductible = Boolean.parseBoolean(parts[6]);
            return new Expense(date, amount, description, category, isTaxDeductible);
        } else if (type.equals("INCOME") && parts.length >= 7) {
            String source = parts[5];
            boolean isTaxable = Boolean.parseBoolean(parts[6]);
            return new Income(date, amount, description, source, isTaxable);
        } else {
            throw new InvalidTransactionException("Unknown transaction type: " + type);
        }
    }
}