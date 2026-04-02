package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connn {

    Connection connection;
    Statement statement;

    public Connn() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db", "", "");
            statement = connection.createStatement();

            // Initialize tables if not exist
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS login (card_number TEXT PRIMARY KEY, pin TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS signup (formno TEXT PRIMARY KEY, name TEXT, fname TEXT, dob TEXT, gender TEXT, email TEXT, marital TEXT, address TEXT, city TEXT, pincode TEXT, state TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Signuptwo (formno TEXT PRIMARY KEY, religion TEXT, category TEXT, income TEXT, education TEXT, occupation TEXT, pan TEXT, aadhar TEXT, senior_citizen TEXT, existing_account TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS signupthree (formno TEXT PRIMARY KEY, account_type TEXT, card_number TEXT, pin TEXT, services TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS bank (pin TEXT, date TEXT, type TEXT, amount TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
