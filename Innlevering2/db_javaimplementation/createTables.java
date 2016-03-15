package db_javaimplementation;

import java.sql.*;

public class createTables {
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/WORKOUTLOG";
	
	//credentials
	static final String username = "username";
	static final String password = "password";
	
	public static void main(String[] args){
		Connection connection = null;
		Statement statement = null;
		try {
			//register JDBC driver
			Class.forName(JDBC_DRIVER);
			//open a connection
			System.out.println("Connection to database");
			connection = DriverManager.getConnection(DB_URL, username, password);
			System.out.println("Connected = OK");
			
			//execute queries
			System.out.println("Creating tables");
			
			statement = connection.createStatement();
			String okt = "CREATE TABLE Okt" +
					"(dato		DATE NOT NULL PRIMARY KEY," +
					"tidspunkt	TIME NOT NULL PRIMARY KEY," +
					"varighet	TIME NOT NULL," +
					"type		TEXT NOT NULL," +
					"vurdering	TEXT NOT NULL)";
			statement.executeUpdate(okt);
			System.out.println("Created Okt table");
			
			statement = connection.createStatement();
			String ovelse = "CREATE TABLE Ovelse" +
							"(navn STRING NOT NULL PRIMARY KEY" +
                            "dato DATE NOT NULL PRIMARY KEY" +
							"beskrivelse	TEXT NOT NULL," +
							"intensitet	INTEGER NOT NULL," +
							"maalId		INTEGER NOT NULL," +
							"lengde		INTEGER," +
							"marsj		BOOLEAN," +
							"repetisjoner 	INTEGER," + 
							"sett		INTEGER," +
							"belastning	INTEGER)";
			statement.executeUpdate(ovelse);
			
			statement = connection.createStatement();
			String utfort = "CREATE TABLE Utfort" +
							"(prestasjon INTEGER," +
							"navn STRING NOT NULL," +
							"dato STRING NOT NULL," +
							"PRIMARY KEY (navn, dato)," +
							"FOREIGN KEY (navn) REFERENCES Ovelse(navn)" +
							"ON UPDATE CASCADE" +
							"ON DELETE CASCADE," +
							"FOREIGN KEY (dato) REFERENCES Okt(dato)" +
							"ON UPDATE CASCADE" +
							"ON DELETE CASCADE)";
			statement.executeUpdate(utfort);
			
			statement = connection.createStatement();
			String kanerstatte = "CREATE TABLE KanErstatte" +
								 "(erstatter_navn STRING NOT NULL PRIMARY KEY," +
								 "FOREIGN KEY(erstatter_navn) REFERENCES Ovelse(navn)" +
								 "ON UPDATE CASCADE" +
								 "ON DELETE CASCADE)";
			statement.executeUpdate(kanerstatte);
			
			statement = connection.createStatement();
			String dagsform = "CREATE TABLE Dagsform" +
							  "(dato DATE NOT NULL PRIMARY KEY," + 
							  "dagsform TEXT NOT NULL," +
							  "hvilepuls INTEGER NOT NULL," +
							  "luftkvalitet INTEGER," +
							  "temperatur INTEGER," +
							  "vaer STRING," +
							  "FOREIGN KEY (dato) REFERENCES Okt(dato)" + 
							  "ON UPDATE NO ACTION" +
							  "ON DELETE NO ACTION)";
			statement.executeUpdate(dagsform);
			
			statement = connection.createStatement();
			String maal = "CREATE TABLE Maal" + 
						  "(maalId INTEGER NOT NULL PRIMARY KEY," +
						  "lengde INTEGER," +
						  "varighet TIME," +
						  "repetisjoner INTEGER," +
						  "sett INTEGER," +
						  "belastning INTEGER," +
						  "dato DATE," +
						  "navn STRING NOT NULL," +
						  "FOREIGN KEY (maalId) REFERENCES Ovelse(maalId)" +
						  "ON UPDATE CASCADE" +
						  "ON DELETE CASCADE)";
			statement.executeUpdate(maal);
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
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
		   }//end try
		   System.out.println("So long big hard guy boys!");
	}
	
	
}