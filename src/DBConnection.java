import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Layout",
                    "postgres",
                    "postgres"
                );

                System.out.println("Database connected successfully");
            }
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        return connection;
    }
}
