package Innlevering2;

import java.sql.*;
import java.util.ArrayList;

public class Queries{

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
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean insert(String tableName,ArrayList<String> array){

        Connection  connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, username, password);

            statement = connection.createStatement();
            String sql = "INSERT INTO " + tableName + " VALUES(";
            for(String attribute : array){
                sql.concat(attribute);
                sql.concat(", ");
            }
            sql = sql[:sql.length()];
            sql.concat(")");
            statement.executeUpdate(sql);
            System.out.println("Insert into " + tableName + " complete. ");
            return true;
        } catch(SQLException se){
            se.printStackTrace();
            return false;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }



}