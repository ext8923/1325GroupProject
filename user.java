import javax.swing.*;  // Importing Swing for GUI components
import java.awt.*;     // Importing AWT for layout management
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends JFrame {
    // Declare GUI components
    private JTextField nameField;  // Text field for entering the user's name
    private JTextField dobField;   // Text field for entering date of birth
    private JButton nextButton;    // Button to proceed to the next step

    // Constructor for the UserPage class
    public UserPage() {
        // Set the title of the window
        setTitle("User Details");

        // Set the window size (width x height)
        setSize(400, 300);

        // Close the application when the user closes the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout: GridLayout(rows, columns, horizontal spacing, vertical spacing)
        setLayout(new GridLayout(3, 2, 10, 10));

        // Add label and text field for Name input
        add(new JLabel("Enter Name:"));  // Label
        nameField = new JTextField();    // Text field for name input
        add(nameField);                  // Add text field to the layout

        // Add label and text field for Date of Birth input
        add(new JLabel("Enter Date of Birth (YYYY-MM-DD):"));  // Label
        dobField = new JTextField();  // Text field for DOB input
        add(dobField);                // Add text field to the layout

        // Add button to proceed
        nextButton = new JButton("Next");  // Button creation
        add(nextButton);  // Add button to the layout

        // Action Listener for the button click event
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve user input from text fields
                String name = nameField.getText();
                String dob = dobField.getText();

                // Simple validation: Ensure both fields are filled
                if (name.isEmpty() || dob.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Name and Date of Birth!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Stop further execution
                }

                // Display user input for now (Later, you can pass this data to another page)
                JOptionPane.showMessageDialog(null, "Name: " + name + "\nDOB: " + dob, "User Details", JOptionPane.INFORMATION_MESSAGE);

                // (Later) Open next page here if needed
                // new IncomePage(name, dob);
                // dispose(); // Close current window
            }
        });

        // Make the window visible
        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        new UserPage();  // Create and display the UserPage window
    }
}
