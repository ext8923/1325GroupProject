import java.util.Scanner;

public class user {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = scanner.nextLine(); // Read user input for name

        // Make sure name is provided
        if (name.isEmpty()) {
            System.out.println("Error!!!!\nName  is required."); // if the user doesn't enter their name.
        } else {

            System.out.println("\nUser Details:");
            System.out.println("Name: " + name);

            System.out.println("Let's Enter your income information now\n");// starts asking for the income information
        }

        scanner.close();
    }
}
