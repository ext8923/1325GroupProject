import java.util.Scanner;

public class UserPage {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);  
    
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();  // Read user input for name
        
        //Makesure both name and dob are provided
        if (name.isEmpty()) {
            System.out.println("Error!!!!\nName  is required.");
        } else {
            
            System.out.println("\nUser Details:");
            System.out.println("Name: " + name);
            
            
            
            System.out.println("Let's Enter your income information now\n");
        }
        
        
        scanner.close();
    }
}
