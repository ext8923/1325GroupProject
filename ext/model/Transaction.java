package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract class that represents a financial transaction
 */
public abstract class Transaction {
    // Private fields for encapsulation
    private Date date;
    private double amount;
    private String description;
    private int id;
    
    // Static counter to generate unique IDs
    private static int counter = 1;
    
    // Constructor
    public Transaction(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.id = counter++;
    }
    
    // Getters and setters
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    /**
     * Set the transaction ID and update the counter if necessary
     * 
     * @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
        // Update counter if this ID is higher
        if (id >= counter) {
            counter = id + 1;
        }
    }
    
    // Abstract methods to be implemented by child classes
    public abstract double calculateTax();
    
    public abstract String getTransactionType();
    
    // Common methods for all transactions
    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    public String toFileString() {
        // Format: type,id,date,amount,description
        return getTransactionType() + "," + 
               id + "," + 
               formatDate() + "," + 
               amount + "," + 
               description;
    }
    
    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Date: " + formatDate());
        System.out.println("Amount: $" + amount);
        System.out.println("Description: " + description);
    }
}