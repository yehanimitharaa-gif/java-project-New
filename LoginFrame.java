import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    public LoginFrame() {
        setTitle("Transport System Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userField = new JTextField();
        passField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Username:"));
        add(userField);
        add(new JLabel("Password:"));
        add(passField);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (user.equals("admin") && pass.equals("admin")) { // simple login
                new ActivityFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}

class ActivityFrame extends JFrame {
    JComboBox<String> activityCombo;
    JButton selectButton;

    public ActivityFrame() {
        setTitle("Select Activity");
        setSize(400, 150);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Only include "Add Employee" for all employee-related entries
        activityCombo = new JComboBox<>(new String[]{
                "Add Owner", "Add Vehicle", "Add Employee", "Add Order", "Add Fuel"
        });

        selectButton = new JButton("Select");

        add(activityCombo);
        add(selectButton);

        selectButton.addActionListener(e -> {
            String activity = (String) activityCombo.getSelectedItem();

            switch (activity) {
                case "Add Owner" -> Owner.showOwnerForm();
                case "Add Vehicle" -> new AddVehicleFrame();
                case "Add Employee" -> new AddEmployeeFrame(); // handles all employees
                case "Add Order" -> new AddOrderFrame();
                case "Add Fuel" -> new AddFuelFrame();
                default -> JOptionPane.showMessageDialog(this, "Feature not implemented yet");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
