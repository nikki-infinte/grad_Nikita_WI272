import java.sql.*;


public class Main {
    public static void main(String[] args) {
       String url = "jdbc:postgresql://db:5432/testdb";
        String user = "postgres";
        String password = "postgres";
        try {
            
             Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students(id SERIAL PRIMARY KEY, name VARCHAR(50))");

            stmt.executeUpdate("INSERT INTO students(name) VALUES('Nikita')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while(rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
            }
            conn.close();

        } catch (Exception e) {
            System.out.append("Error: " + e.getMessage());
        }

    }
}