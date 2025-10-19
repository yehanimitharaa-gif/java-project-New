import java.sql.*;

public class AuthService {
    public static boolean authenticate(String username, String password) {
        String sql = "SELECT PasswordHash FROM Users WHERE Username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String stored = rs.getString("PasswordHash");
                return password.equals(stored); // demo: plain compare
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



