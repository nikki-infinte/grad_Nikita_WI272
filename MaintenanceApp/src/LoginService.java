import java.sql.*;

public class LoginService {

    public static ResultSet login(String username, String password) {

        String sql =
            "SELECT uid, role FROM userDetails " +
            "WHERE username = ? AND password = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            return ps.executeQuery();

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }
}
