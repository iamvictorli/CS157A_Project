import java.util.Properties;

public class Main {

	//change these for credentials
	private static Properties INFO = new Properties();

	//driver and connection string
	private static String CONN_STRING = "jdbc:mysql://localhost/";
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {
		INFO.setProperty("user", "JackDubil");
		INFO.setProperty("password", "password");
		INFO.setProperty("useSSL", "false");

		//create database Books
		Database.CreateDB(INFO, CONN_STRING, JDBC_DRIVER);

		//populating Books with tables
		//after creating a database, use Books database by String BookConnection
		String BookConnection = "jdbc:mysql://localhost/Books";
		Database.PopulateDB(INFO, BookConnection, JDBC_DRIVER);

		//add data into tables
		Tables.add(INFO, BookConnection, JDBC_DRIVER);

		//issues sql statements. For queries print the results
		Statements.query(INFO, BookConnection, JDBC_DRIVER);
	}
}
