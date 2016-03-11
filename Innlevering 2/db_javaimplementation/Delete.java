package db_javaimplementation

import java.sql.*;

public class Delete{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/WORKOUTLOG";

    static final String username = "username";
    static final String password = "password";

    public void deleteRow(String table, String row) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, username, password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM" + table + "WHERE name = ?");
            statement.setString(1, row);
            statement.executeUpdate()
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}