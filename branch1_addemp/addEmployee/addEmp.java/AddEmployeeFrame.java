
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEmployeeFrame extends JFrame {
    JTextField idField, nameField, ageField, nicField, contactField, addressField, bonusField, leavesField;
    JComboBox<String> designationCombo;
    JButton saveButton;

    public AddEmployeeFrame() {
        setTitle("Add Employee");
        setSize(400, 400);
        setLayout(new GridLayout(11, 2, 5, 5));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        nicField = new JTextField();
        contactField = new JTextField();
        addressField = new JTextField();
        bonusField = new JTextField();
        leavesField = new JTextField();
        designationCombo = new JComboBox<>(new String[]{"Driver", "Officer", "Helper"});
        saveButton = new JButton("Save");

        // Add labels and fields
        add(new JLabel("Employee ID:")); add(idField);
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Age:")); add(ageField);
        add(new JLabel("NIC:")); add(nicField);
        add(new JLabel("Contact:")); add(contactField);
        add(new JLabel("Address:")); add(addressField);
        add(new JLabel("Designation:")); add(designationCombo);
        add(new JLabel("Bonus:")); add(bonusField);
        add(new JLabel("Leaves Taken:")); add(leavesField);
        add(saveButton);

        // Save button action
        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String nic = nicField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                String designation = (String) designationCombo.getSelectedItem();
                double bonus = Double.parseDouble(bonusField.getText());
                int leaves = Integer.parseInt(leavesField.getText());

                // Set basic salary automatically
                double basicSalary;
                switch (designation) {
                    case "Driver" -> basicSalary = 57000;
                    case "Officer" -> basicSalary = 55000;
                    default -> basicSalary = 35000; // Helper
                }

                // Create Employee object
                Employee emp = new Employee(id, name, age, nic, contact, address, basicSalary, designation, leaves, bonus);
                double calculatedSalary = emp.calculateSalary();

                // Insert into database
                insertEmployeeIntoDB(emp);

                JOptionPane.showMessageDialog(this, "Employee Added!\nSalary: " + calculatedSalary + "\nDetails:\n" + emp);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please fill all fields correctly.\nError: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void insertEmployeeIntoDB(Employee emp) {
        String sql = "INSERT INTO Employee (EmployeeID, Name, Age, NIC, Contact, Address, Designation, Salary, LeavesTaken, Bonus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emp.employeeID);
            stmt.setString(2, emp.name);
            stmt.setInt(3, emp.age);
            stmt.setString(4, emp.NIC);
            stmt.setString(5, emp.contact);
            stmt.setString(6, emp.address);
            stmt.setString(7, emp.designation);
            stmt.setDouble(8, emp.salary);
            stmt.setInt(9, emp.leavesTaken);
            stmt.setDouble(10, emp.bonus);

            stmt.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
