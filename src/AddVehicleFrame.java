import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddVehicleFrame extends JFrame {
    JTextField idField, numberField, typeField, modelField;
    JButton saveButton;

    public AddVehicleFrame() {
        setTitle("Add Vehicle");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));

        idField = new JTextField();
        numberField = new JTextField();
        typeField = new JTextField();
        modelField = new JTextField();
        saveButton = new JButton("Save");

        add(new JLabel("Vehicle ID:"));
        add(idField);
        add(new JLabel("Vehicle Number:"));
        add(numberField);
        add(new JLabel("Vehicle Type:"));
        add(typeField);
        add(new JLabel("Model:"));
        add(modelField);
        add(saveButton);

        saveButton.addActionListener(e -> saveVehicle());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveVehicle() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Vehicle (VehicleID, VehicleNumber, VehicleType, Model) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, numberField.getText());
            ps.setString(3, typeField.getText());
            ps.setString(4, modelField.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Vehicle saved successfully!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

