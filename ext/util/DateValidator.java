package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.InvalidTransactionException;

/**
 * Utility class for validating and parsing dates
 */
public class DateValidator {
    
    // The expected date format
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    
    /**
     * Private constructor to prevent instantiation
     */
    private DateValidator() {
        // Utility class should not be instantiated
    }
    
    /**
     * Validates and parses a date string
     * 
     * @param dateStr The date string to validate and parse
     * @return The parsed Date object
     * @throws InvalidTransactionException If the date format is invalid
     */
    public static Date validateAndParse(String dateStr) throws InvalidTransactionException {
        try {
            // Set lenient to false for strict date validation
            formatter.setLenient(false);
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new InvalidTransactionException(
                InvalidTransactionException.getInvalidDateMessage(dateStr), e);
        }
    }
    
    /**
     * Checks if a date string has valid format
     * 
     * @param dateStr The date string to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String dateStr) {
        try {
            formatter.setLenient(false);
            formatter.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    /**
     * Formats a Date object to string
     * 
     * @param date The Date object to format
     * @return Formatted date string
     */
    public static String formatDate(Date date) {
        return formatter.format(date);
    }
    
    /**
     * Gets today's date as a Date object
     * 
     * @return Today's date
     */
    public static Date getToday() {
        return new Date();
    }
    
    /**
     * Gets today's date as a formatted string
     * 
     * @return Today's date as string
     */
    public static String getTodayString() {
        return formatDate(new Date());
    }
}