import java.util.Scanner;

public class UserPage {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);  
    
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();  // Read user input for name
        
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();  // Read user input for date of birth
        
        //Makesure both name and dob are provided
        if (name.isEmpty() || dob.isEmpty()) {
            System.out.println("Error!!!!\nName and Date of Birth are required.");
        } else {
            
            System.out.println("\nUser Details:");
            System.out.println("Name: " + name);
            System.out.println("Date of Birth: " + dob);
            
            
            System.out.println("Let's Enter your income information now\n");
        }
        
        
        scanner.close();
    }
}