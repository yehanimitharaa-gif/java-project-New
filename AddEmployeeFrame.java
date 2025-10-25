import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEmployeeFrame extends JFrame {
    private JTextField idField, nameField, ageField, nicField, contactField, addressField, emailField, accountField, bonusField, leavesField;
    private JComboBox<String> designationCombo;
    private JButton saveButton;

    public AddEmployeeFrame(String role) {
        setTitle("Add " + role);
        setSize(450, 500);
        setLayout(new GridLayout(13, 2, 5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        nicField = new JTextField();
        contactField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();
        accountField = new JTextField();
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
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Account Number:")); add(accountField);
        add(new JLabel("Designation:")); add(designationCombo);
        add(new JLabel("Bonus:")); add(bonusField);
        add(new JLabel("Leaves Taken:")); add(leavesField);
        add(new JLabel("")); add(saveButton);

        saveButton.addActionListener(e -> saveEmployee());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public AddEmployeeFrame() {
        this("Employee");
    }

    private void saveEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String nic = nicField.getText();
            String contact = contactField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String accountNumber = accountField.getText();
            String designation = (String) designationCombo.getSelectedItem();
            double bonus = Double.parseDouble(bonusField.getText());
            int leaves = Integer.parseInt(leavesField.getText());

            double basicSalary = 0;
            if ("Driver".equals(designation)) basicSalary = 57000;
            else if ("Officer".equals(designation)) basicSalary = 55000;
            else basicSalary = 35000;

            Employee emp = new Employee(id, name, age, nic, contact, address, email, accountNumber,
                    basicSalary, designation, leaves, bonus);

            insertEmployeeIntoDB(emp);

            JOptionPane.showMessageDialog(this, "Employee Added!\nSalary: " + emp.calculateSalary());
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please fill all fields correctly.\nError: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // --- Upgraded DB insertion method ---
    private void insertEmployeeIntoDB(Employee emp) {
        String sqlEmployee = "INSERT INTO Employee (EmployeeID, Name, Age, NIC, Contact, Address, Email, AccountNumber, Designation, Salary, LeavesTaken, Bonus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlRole = null;

        switch (emp.getDesignation()) {
            case "Driver":
                sqlRole = "INSERT INTO Driver (EmployeeID, Name, Age, NIC, Contact, Address, Email, AccountNumber, Salary, LeavesTaken, Bonus) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Officer":
                sqlRole = "INSERT INTO Officer (EmployeeID, Name, Age, NIC, Contact, Address, Email, AccountNumber, Salary, LeavesTaken, Bonus) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Helper":
                sqlRole = "INSERT INTO Helper (EmployeeID, Name, Age, NIC, Contact, Address, Email, AccountNumber, Salary, LeavesTaken, Bonus) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmtEmp = conn.prepareStatement(sqlEmployee);
             PreparedStatement stmtRole = conn.prepareStatement(sqlRole)) {

            // Insert into Employee table
            stmtEmp.setInt(1, emp.getEmployeeID());
            stmtEmp.setString(2, emp.getName());
            stmtEmp.setInt(3, emp.getAge());
            stmtEmp.setString(4, emp.getNIC());
            stmtEmp.setString(5, emp.getContact());
            stmtEmp.setString(6, emp.getAddress());
            stmtEmp.setString(7, emp.getEmail());
            stmtEmp.setString(8, emp.getAccountNumber());
            stmtEmp.setString(9, emp.getDesignation());
            stmtEmp.setDouble(10, emp.getSalary());
            stmtEmp.setInt(11, emp.getLeavesTaken());
            stmtEmp.setDouble(12, emp.getBonus());
            stmtEmp.executeUpdate();

            // Insert into Role table
            stmtRole.setInt(1, emp.getEmployeeID());
            stmtRole.setString(2, emp.getName());
            stmtRole.setInt(3, emp.getAge());
            stmtRole.setString(4, emp.getNIC());
            stmtRole.setString(5, emp.getContact());
            stmtRole.setString(6, emp.getAddress());
            stmtRole.setString(7, emp.getEmail());
            stmtRole.setString(8, emp.getAccountNumber());
            stmtRole.setDouble(9, emp.getSalary());
            stmtRole.setInt(10, emp.getLeavesTaken());
            stmtRole.setDouble(11, emp.getBonus());
            stmtRole.executeUpdate();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
