// order frame
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddOrderFrame extends JFrame {
    JTextField orderIdField, orderStateField, loadedDateField, loadedTimeField,
            unloadedDateField, unloadedTimeField, loadFromField, loadToField;
    JButton saveButton;

    public AddOrderFrame() {
        setTitle("Add Order");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2, 5, 5));

        orderIdField = new JTextField();
        orderStateField = new JTextField();
        loadedDateField = new JTextField();
        loadedTimeField = new JTextField();
        unloadedDateField = new JTextField();
        unloadedTimeField = new JTextField();
        loadFromField = new JTextField();
        loadToField = new JTextField();
        saveButton = new JButton("Save");

        add(new JLabel("Order ID:")); add(orderIdField);
        add(new JLabel("Order State:")); add(orderStateField);
        add(new JLabel("Loaded Date:")); add(loadedDateField);
        add(new JLabel("Loaded Time:")); add(loadedTimeField);
        add(new JLabel("Unloaded Date:")); add(unloadedDateField);
        add(new JLabel("Unloaded Time:")); add(unloadedTimeField);
        add(new JLabel("Load From:")); add(loadFromField);
        add(new JLabel("Load To:")); add(loadToField);
        add(new JLabel()); add(saveButton);

        saveButton.addActionListener(e -> {
            String orderId = orderIdField.getText();
            String orderState = orderStateField.getText();
            String loadedDate = loadedDateField.getText();
            String loadedTime = loadedTimeField.getText();
            String unloadedDate = unloadedDateField.getText();
            String unloadedTime = unloadedTimeField.getText();
            String loadFrom = loadFromField.getText();
            String loadTo = loadToField.getText();

            if (orderId.isEmpty() || orderState.isEmpty() || loadedDate.isEmpty() ||
                    loadedTime.isEmpty() || unloadedDate.isEmpty() || unloadedTime.isEmpty() ||
                    loadFrom.isEmpty() || loadTo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO Orders (OrderID, OrderState, LoadedDate, LoadedTime, " +
                        "UnloadedDate, UnloadedTime, LoadFrom, LoadTo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, orderId);
                pst.setString(2, orderState);
                pst.setString(3, loadedDate);
                pst.setString(4, loadedTime);
                pst.setString(5, unloadedDate);
                pst.setString(6, unloadedTime);
                pst.setString(7, loadFrom);
                pst.setString(8, loadTo);

                pst.executeUpdate();
                pst.close();

                JOptionPane.showMessageDialog(this, "Order saved to database!");

                // Clear fields
                orderIdField.setText("");
                orderStateField.setText("");
                loadedDateField.setText("");
                loadedTimeField.setText("");
                unloadedDateField.setText("");
                unloadedTimeField.setText("");
                loadFromField.setText("");
                loadToField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddOrderFrame::new);
    }
}