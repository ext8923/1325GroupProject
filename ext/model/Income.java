package model;

import java.util.Date;

/**
 * Represents an income transaction
 */
public class Income extends Transaction {
    private String source;
    private boolean isTaxable;
    
    // Income tax rate (e.g., 20%)
    private static final double INCOME_TAX_RATE = 0.20;
    
    /**
     * Constructor for Income
     */
    public Income(Date date, double amount, String description, String source, boolean isTaxable) {
        super(date, amount, description);
        this.source = source;
        this.isTaxable = isTaxable;
    }
    
    // Getters and setters
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public boolean isTaxable() {
        return isTaxable;
    }
    
    public void setTaxable(boolean isTaxable) {
        this.isTaxable = isTaxable;
    }
    
    // Implementing abstract methods from Transaction
    @Override
    public double calculateTax() {
        if (isTaxable) {
            return getAmount() * INCOME_TAX_RATE;
        } else {
            return 0.0;
        }
    }
    
    @Override
    public String getTransactionType() {
        return "INCOME";
    }
    
    // Override printDetails to add income-specific info
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Type: Income");
        System.out.println("Source: " + source);
        System.out.println("Taxable: " + (isTaxable ? "Yes" : "No"));
        if (isTaxable) {
            System.out.println("Estimated Tax: $" + calculateTax());
        }
        System.out.println("------------------------");
    }
    
    // Override toFileString to include income-specific fields
    @Override
    public String toFileString() {
        return super.toFileString() + "," + source + "," + isTaxable;
    }
}