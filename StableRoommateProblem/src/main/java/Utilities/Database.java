package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "STUDENT";
    private static final String PASSWORD = "STUDENT";
    private static Connection connection = null;
    private Database() {
        //createConnection();
    }
    public static java.sql.Connection getConnection() throws SQLException {
        if(connection==null)
            createConnection();
        return connection;
    }
    private static void createConnection() {
        try {
            //Connection = TODO
            // connection.
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
