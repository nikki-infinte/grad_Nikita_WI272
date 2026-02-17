package com.maintenance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Layout",
                    "postgres",
                    "postgres"
                );
                // System.out.println("Database connected successfully"); 
                // Commented out to avoid cluttering output
            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}
