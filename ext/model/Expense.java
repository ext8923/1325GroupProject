package model;

import java.util.Date;

/**
 * Represents an expense transaction
 */
public class Expense extends Transaction {
    private String category;
    private boolean isTaxDeductible;
    
    // Tax rate for deductible expenses (e.g., 15%)
    private static final double TAX_RATE = 0.15;
    
    /**
     * Constructor for Expense
     */
    public Expense(Date date, double amount, String description, String category, boolean isTaxDeductible) {
        super(date, amount, description);
        this.category = category;
        this.isTaxDeductible = isTaxDeductible;
    }
    
    // Getters and setters
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public boolean isTaxDeductible() {
        return isTaxDeductible;
    }
    
    public void setTaxDeductible(boolean isTaxDeductible) {
        this.isTaxDeductible = isTaxDeductible;
    }
    
    // Implementing abstract methods from Transaction
    @Override
    public double calculateTax() {
        if (isTaxDeductible) {
            return getAmount() * TAX_RATE;
        } else {
            return 0.0;
        }
    }
    
    @Override
    public String getTransactionType() {
        return "EXPENSE";
    }
    
    // Override printDetails to add expense-specific info
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Type: Expense");
        System.out.println("Category: " + category);
        System.out.println("Tax Deductible: " + (isTaxDeductible ? "Yes" : "No"));
        if (isTaxDeductible) {
            System.out.println("Tax Deduction: $" + calculateTax());
        }
        System.out.println("------------------------");
    }
    
    // Override toFileString to include expense-specific fields
    @Override
    public String toFileString() {
        return super.toFileString() + "," + category + "," + isTaxDeductible;
    }
}