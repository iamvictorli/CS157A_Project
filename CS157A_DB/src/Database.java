import java.sql.*;
import java.util.Properties;

public class Database {

	//creating database Books
	public static void CreateDB(Properties info, String ConnString, String Driver) {
		Connection conn;
		Statement stmt;

		try {
			Class.forName(Driver);

			System.out.println("Connecting to database");
			conn = DriverManager.getConnection(ConnString, info);

			try {
				System.out.println("Creating database Books...");
				stmt = conn.createStatement();

				//drop database books if it already exists
				stmt.execute("DROP DATABASE Books");

				String sql = "CREATE DATABASE Books";
				stmt.execute(sql);
				System.out.println("Database created successfully...");

				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	//Populate database with tables
	public static void PopulateDB(Properties info, String ConnString, String Driver) {
		Connection conn;
		Statement stmt;

		try {
			Class.forName(Driver);

			System.out.println("Connecting to a Books database");
			conn = DriverManager.getConnection(ConnString, info);

			try {
				System.out.println("Connected database Books successfully...");

				stmt = conn.createStatement();

			    String authors = "CREATE TABLE authors " +
			                   "(authorID INTEGER NOT NULL AUTO_INCREMENT, " +
			                   "firstName CHAR(20), " +
			                   "lastName CHAR(20), " +
			                   "PRIMARY KEY ( authorId ))";

			    String authorISBN = "CREATE TABLE authorISBN " +
		                   "(authorId INTEGER, " +
		                   "isbn CHAR(10), " +
		                   "FOREIGN KEY ( authorID ) REFERENCES authors ( authorID ) " +
		                   		"ON UPDATE CASCADE " +
		                   		"ON DELETE CASCADE, " +
		                   "FOREIGN KEY ( isbn ) REFERENCES titles ( isbn ) " +
		                   		"ON UPDATE CASCADE " +
		                   		"ON DELETE CASCADE)";

			    String titles = "CREATE TABLE titles " +
		                   "(isbn CHAR(10) NOT NULL, " +
		                   "title VARCHAR(2500), " +
		                   "editionNumber INTEGER, " +
		                   "copyright CHAR(4), " +
		                   "publisherID INTEGER, " +
		                   "price FLOAT, " +
		                   "PRIMARY KEY ( isbn ), " +
		                   "FOREIGN KEY ( publisherID ) REFERENCES publishers ( publisherID ) " +
	                  			"ON UPDATE CASCADE)";

			    String publishers = "CREATE TABLE publishers " +
		                   "(publisherID INTEGER NOT NULL AUTO_INCREMENT, " +
		                   "publisherName CHAR(100), " +
		                   "PRIMARY KEY ( publisherID ))";

			    stmt.executeUpdate(authors);
			    stmt.executeUpdate(publishers);
			    stmt.executeUpdate(titles);
			    stmt.executeUpdate(authorISBN);


			    stmt.close();

			    System.out.println("Created author, authorISBN, titles, publishers tables");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
