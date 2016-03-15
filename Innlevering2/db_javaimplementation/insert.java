package db_javaimplementation;

import java.sql.*;

public class insertDelete {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	static final String username = "username";
	static final String password = "password";
	
	public boolean insert(String tableName,ArrayList<String> array){
		
		Connection  connection = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			
			connection = DriverManager.getConnection(DB_URL, username, password);
			
			statement = connection.createStatement();
			String sql = "INSERT INTO " + tableName + " VALUES(";
			for(String attribute : array){
				sql.concat(attribute);
				sql.concat(", ");
			}
			sql = sql.substring(0, sql.length()-2);
			sql.concat(")");
			statement.executeUpdate(sql);
			System.out.println("Insert into " + tableName + " complete. ")
			return true;
		} catch(SQLException se){
			se.printStackTrace();
			return false;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			try{
		         if(statement!=null)
		            connection.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(connection!=null)
		            connection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		}
	
}