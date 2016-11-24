
public class Main {
	
	//change these for credentials
	private static String USERNAME = "VictorLi";
	private static String PASSWORD = "password";
	
	//driver and connection string
	private static String CONN_STRING = "jdbc:mysql://localhost/";
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args) {
		
		//create database Books
		Database.CreateDB(USERNAME, PASSWORD, CONN_STRING, JDBC_DRIVER);
		
		//populating Books with tables
		//Books connection string
		String BookConnection = "jdbc:mysql://localhost/Books";
		Database.PopulateDB(USERNAME, PASSWORD, BookConnection, JDBC_DRIVER);
		
	}
}
