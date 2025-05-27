package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {
    private static final String URL = "jdbc:sqlite:DataBase.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}