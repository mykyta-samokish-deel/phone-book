package phonebook;

import java.sql.*;

public class DBStorage {
    // JDBC driver name and database url
    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/phonebook";
    // Database credentials
    public static final String USER = "123";
    public static final String PASS = "123";

    public void insertNameIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO pb_contacts "
                + "(first_name, last_name) VALUES "
                + "(?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, "Mykyta");
            preparedStatement.setString(2, "Samokish");

            // execute insert SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into pb_contacts table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void insertPhoneIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO pb_phones "
                + "(phone, user_id) VALUES "
                + "(?,  "
                + "(SELECT user_id FROM pb_contacts WHERE last_name = ?) )";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, "+530 7230 6494");
            preparedStatement.setString(2, "Samokish");

            // execute insert SQL statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void updateRecordToTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE pb_contacts SET first_name = ? "
                + " WHERE first_name = ?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, "Mykytka");
            preparedStatement.setString(2, "Mykyta");

            // execute update SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Record is updated to pb_contacts Table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void deleteRecordFromTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM pb_contacts WHERE last_name = ?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, "Samokish");

            // execute detele SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Record is deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void selectRecordsFromTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM pb_contacts "
                + "INNER JOIN pb_phones "
                + "ON pb_contacts.user_id = pb_phones.user_id";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            // execute select SQL Statement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");

                System.out.printf("%-2s. ", userId);
                System.out.printf("%-20s ", firstName);
                System.out.printf("%-20s ", lastName);
                System.out.println(phone);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }
}
