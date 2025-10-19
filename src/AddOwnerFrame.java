import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddOwnerFrame extends JFrame {
    JTextField idField, nameField, phoneField, addressField, emailField;
    JButton saveButton;

    public AddOwnerFrame() {
        setTitle("Add Owner");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();
        saveButton = new JButton("Save");

        add(new JLabel("Owner ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Email:"));
        add(emailField);
        add(saveButton);

        saveButton.addActionListener(e -> saveOwner());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveOwner() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Owner (OwnerID, Name, Phone, Address, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, nameField.getText());
            ps.setString(3, phoneField.getText());
            ps.setString(4, addressField.getText());
            ps.setString(5, emailField.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Owner saved successfully!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

