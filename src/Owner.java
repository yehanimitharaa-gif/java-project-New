import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Owner {
    private int ownerID;
    private String name, phone, address, email;

    public Owner(int ownerID, String name, String phone, String address, String email) {
        this.ownerID = ownerID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public void registration() {
        // Insert data into SQL Server
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Owner (OwnerID, Name, Phone, Address, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ownerID);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Owner registered successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void exitManagement() { System.out.println("Exited management."); }
    public void exitAgreement() { System.out.println("Exit agreement done."); }
    public void feedback() { System.out.println("Feedback collected."); }

    // Static method to show input form and create Owner object
    public static void showOwnerForm() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "Owner ID:", idField,
                "Name:", nameField,
                "Phone:", phoneField,
                "Address:", addressField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Owner", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int ownerID = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String email = emailField.getText();

                Owner owner = new Owner(ownerID, name, phone, address, email);
                owner.registration(); // save to DB
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Owner ID must be a number.");
            }
        }
    }
}
