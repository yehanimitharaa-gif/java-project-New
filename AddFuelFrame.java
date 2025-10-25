
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFuelFrame extends JFrame {
    private JTextField dateField, vehicleNumberField, tankLitersField,
            fromField, toField, distanceField, fuelNeededField, receiverField;
    private JButton calculateButton, saveButton;

    public AddFuelFrame() {
        setTitle("Add Fuel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 5, 5));

        // Labels and Fields
        add(new JLabel("Date (yyyy-mm-dd):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Vehicle Number:"));
        vehicleNumberField = new JTextField();
        add(vehicleNumberField);

        add(new JLabel("Full Liters in Tank:"));
        tankLitersField = new JTextField();
        add(tankLitersField);

        add(new JLabel("From Location:"));
        fromField = new JTextField();
        add(fromField);

        add(new JLabel("To Location:"));
        toField = new JTextField();
        add(toField);

        add(new JLabel("Distance (km):"));
        distanceField = new JTextField();
        add(distanceField);

        add(new JLabel("Fuel Needed (liters):"));
        fuelNeededField = new JTextField();
        fuelNeededField.setEditable(false);
        add(fuelNeededField);

        add(new JLabel("Fuel Receiver Name:"));
        receiverField = new JTextField();
        add(receiverField);

        // Buttons
        calculateButton = new JButton("Calculate Fuel Needed");
        add(calculateButton);
        saveButton = new JButton("Save to Database");
        add(saveButton);

        // Action Listeners
        calculateButton.addActionListener(e -> calculateFuel());
        saveButton.addActionListener(e -> saveFuelData());

        setVisible(true);
    }

    // --- Calculate how much fuel is needed ---
    private void calculateFuel() {
        try {
            if (distanceField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter distance first.");
                return;
            }

            double distance = Double.parseDouble(distanceField.getText());
            double fuelNeeded = distance / 20.0; // 1 liter = 20 km
            fuelNeededField.setText(String.format("%.2f", fuelNeeded));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "❌ Invalid number for distance. Please enter a valid number.");
        }
    }

    // --- Save data to SQL Server ---
    private void saveFuelData() {
        String date = dateField.getText().trim();
        String vehicleNumber = vehicleNumberField.getText().trim();
        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        String receiver = receiverField.getText().trim();

        // Check for empty required fields
        if (date.isEmpty() || vehicleNumber.isEmpty() || from.isEmpty() || to.isEmpty() || receiver.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Please fill in all text fields.");
            return;
        }

        try {
            // Validate numeric fields
            if (tankLitersField.getText().trim().isEmpty() || distanceField.getText().trim().isEmpty() || fuelNeededField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Please fill in valid numbers for tank liters, distance, and calculate fuel needed first.");
                return;
            }

            BigDecimal tankLiters = new BigDecimal(tankLitersField.getText().trim());
            BigDecimal distance = new BigDecimal(distanceField.getText().trim());
            BigDecimal fuelNeeded = new BigDecimal(fuelNeededField.getText().trim());

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO fuel_log (date, vehicle_number, tank_liters, location_from, location_to, distance, fuel_needed, receiver_name) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, date);
                stmt.setString(2, vehicleNumber);
                stmt.setBigDecimal(3, tankLiters);
                stmt.setString(4, from);
                stmt.setString(5, to);
                stmt.setBigDecimal(6, distance);
                stmt.setBigDecimal(7, fuelNeeded);
                stmt.setString(8, receiver);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "✅ Fuel data saved successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "⚠️ No data was saved. Please check your input.");
                }
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "❌ Please enter valid numeric values for tank liters, distance, and fuel needed.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Database error: " + ex.getMessage());
        }
    }

    // --- Clear all input fields after saving ---
    private void clearFields() {
        dateField.setText("");
        vehicleNumberField.setText("");
        tankLitersField.setText("");
        fromField.setText("");
        toField.setText("");
        distanceField.setText("");
        fuelNeededField.setText("");
        receiverField.setText("");
    }

    public static void main(String[] args) {
        new AddFuelFrame();
    }
}
