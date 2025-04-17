package exceptions;

/**
 * Custom exception for invalid transaction operations
 */
public class InvalidTransactionException extends Exception {
    
    /**
     * Constructor with error message
     * 
     * @param message The error message
     */
    public InvalidTransactionException(String message) {
        super(message);
    }
    
    /**
     * Constructor with error message and cause
     * 
     * @param message The error message
     * @param cause The original exception
     */
    public InvalidTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Get a formatted error message for invalid amount
     * 
     * @param amount The invalid amount
     * @return Formatted error message
     */
    public static String getInvalidAmountMessage(double amount) {
        return "Invalid transaction amount: " + amount + ". Amount must be positive.";
    }
    
    /**
     * Get a formatted error message for missing description
     * 
     * @return Error message for missing description
     */
    public static String getMissingDescriptionMessage() {
        return "Transaction description cannot be empty.";
    }
    
    /**
     * Get a formatted error message for invalid date
     * 
     * @param dateStr The invalid date string
     * @return Formatted error message
     */
    public static String getInvalidDateMessage(String dateStr) {
        return "Invalid date format: " + dateStr + ". Please use YYYY-MM-DD format.";
    }
    
    /**
     * Get a formatted error message for transaction not found
     * 
     * @param id The transaction ID that wasn't found
     * @return Formatted error message
     */
    public static String getTransactionNotFoundMessage(int id) {
        return "Transaction with ID " + id + " not found.";
    }
}