import java.sql.*;


public class Statements {

	public static void query(String Username, String Password, String ConnString, String Driver) {
		
		Connection conn;
		Statement stmt;
		ResultSet rs;
		try{
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(ConnString, Username, Password);
			stmt = conn.createStatement();
			
			//select authors and order by author's last name and first name
			rs = stmt.executeQuery(SelectAuthors());
			
			System.out.println();
			System.out.println("Select all authors from authors table and order by last and first name");
			
			while(rs.next()) {
				int authorId = rs.getInt("authorId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.println("authorID: " + authorId + " firstName: " + firstName + " lastName: " + lastName);
			}
			
			System.out.println();
			
			
			
			//select all publishers from the publishers table
			rs = stmt.executeQuery(SelectPublishers());
			System.out.println("Select all publishers from publishers table");
			
			while(rs.next()) {
				int publisherID = rs.getInt("publisherID");
				String publisherName = rs.getString("publisherName");
				System.out.println("publisherID: " + publisherID + " publisherName: " + publisherName);
			}
			System.out.println();
			
			
			
			//select specific publisher 
			rs = stmt.executeQuery(SelectSpecificPublisher("Bloomsbury"));
			System.out.println("Select publisher named Bloomsbury and book information published by that publisher ordered by title");
			
			while(rs.next()) {
				String publisherName = rs.getString("publishers.publisherName");
				String title = rs.getString("titles.title");
				String copyright = rs.getString("titles.copyright");
				String isbn = rs.getString("titles.isbn");
				
				System.out.println("publisherName: " + publisherName + " title: " + title + 
						" year: " + copyright + " isbn: " + isbn);
				
			}
			System.out.println();
			
			
			
			//adds new author
			stmt.executeUpdate(AddAuthor("Ahmed", "Ezzat"));
			System.out.println("Adding author Ahmed Ezzat");
			System.out.println("Table authors after adding new author");
			
			rs = stmt.executeQuery("SELECT * " +
									"FROM authors");
			
			while(rs.next()) {
				int authorId = rs.getInt("authorId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.println("authorID: " + authorId + " firstName: " + firstName + " lastName: " + lastName);
			}
			
			System.out.println();
			
			
			
			//edit author id
			stmt.executeUpdate(UpdateAuthor("J.K", "Rowling", 100));
			System.out.println("Updating author J.K Rowling authorID");
			System.out.println("Table authors and authorISBN after updating author");
			
			rs = stmt.executeQuery("SELECT * " +
									"FROM authors");
			
			while(rs.next()) {
				int authorId = rs.getInt("authorId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.println("authorID: " + authorId + " firstName: " + firstName + " lastName: " + lastName);
			}
			rs = stmt.executeQuery("SELECT * " +
									"FROM authorISBN");
			while(rs.next()) {
				int authorID = rs.getInt("authorID");
				String isbn = rs.getString("isbn");
				System.out.println("authorID: " + authorID + " isbn: " + isbn);
			}
	
			System.out.println();
			
			
			
			//add a new title for an author
			stmt.executeUpdate(InsertTitle(567589, "More Harry Potter", 4, 4, 1, 35.99));
			stmt.executeUpdate(AddNewTitleAuthor(567589, "J.K", "Rowling"));
			rs = stmt.executeQuery("SELECT * " +
									"FROM titles");
			
			while(rs.next()) {
				System.out.println(rs.getLong("isbn") + " " + rs.getInt("editionNumber") + " " +
						rs.getInt("copyright") + " " + rs.getInt("publisherID") + " " + rs.getDouble("price"));
			}
			
			rs = stmt.executeQuery("SELECT * " +
						"FROM authorISBN");
			while(rs.next()) {
				System.out.println(rs.getInt("authorID") + " " + rs.getLong("isbn"));
			}
			
			System.out.println();
			
			//add new publisher
			stmt.executeUpdate(AddPublisher("SJSU"));
			System.out.println("Adding publisher SJSU");
			System.out.println("Table publishers after adding new publisher");
			
			rs = stmt.executeQuery("SELECT * " +
									"FROM publishers");
			
			while(rs.next()) {
				int publisherID = rs.getInt("publisherID");
				String publisherName = rs.getString("publisherName");
				System.out.println("publisherID: " + publisherID + " publisherName: " + publisherName);
			}
			System.out.println();
			
			
			
			//update publisher
			stmt.executeUpdate(UpdatePublisher("Bloomsbury", 5000));
			System.out.println("Updating publisher Bloomsbury publisherID");
			System.out.println("Table publishers and titles after updating author");
			
			rs = stmt.executeQuery("SELECT * " +
									"FROM publishers");
			
			while(rs.next()) {
				int publisherID = rs.getInt("publisherID");
				String publisherName = rs.getString("publisherName");
				System.out.println("publisherID: " + publisherID + " publisherName: " + publisherName);
			}
			
			rs = stmt.executeQuery("SELECT title, copyright, isbn, publisherID " +
									"FROM titles");
			
			while(rs.next()) {
				String title = rs.getString("title");
				String copyright = rs.getString("copyright");
				String isbn = rs.getString("isbn");
				int publisherID = rs.getInt("publisherID");
				System.out.println("title: " + title + " year: " + copyright + " isbn: " + isbn + " publisherID: " + publisherID);
			}
			System.out.println();
			
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//Select all authors from the authors table and order information
	//alphabetically by the author's last name and first name
	private static String SelectAuthors() {
		return "SELECT * " +
				"FROM authors " +
				"ORDER BY lastName ASC, firstName ASC";
	}

	//Select all publishers from the publishers table
	private static String SelectPublishers() {
		return "SELECT * " +
				"FROM publishers";
	}
	
	//Select a specific publisher and list all books published by that publisher.
	//Include title, year and ISBN number.
	//Order the information alphabetically by title
	private static String SelectSpecificPublisher(String s) {
		return "SELECT publishers.publisherName, titles.title, titles.copyright, titles.isbn " +
				"FROM publishers, titles " +
				"WHERE publishers.publisherName='" + s + "' AND " + "publishers.publisherID=titles.publisherID " +
				"ORDER BY titles.title";
	}
	
	//adds new author
	private static String AddAuthor(String firstName, String lastName) {
		return "INSERT INTO authors " +
				"(firstName, lastName) " +
				"VALUES ('" + firstName + "','" + lastName + "')";
	}
	
	private static String InsertTitle(int isbn, String title, int editionNumber,
			int copyright, int publisherID, double price) {
		String s = "INSERT INTO titles " +
				"(isbn, title, editionNumber, copyright, publisherID, price) " +
				"VALUES (" + isbn + ", '" + title + "', " + editionNumber + ", " + copyright + ", " + publisherID + ", " + price + ")";
		System.out.println(s);
		return s;
	}
	
	//edit/update existing information about an author
	//updates authorID when given firstName and lastName
	private static String UpdateAuthor(String firstName, String lastName, int NewAuthorID) {
		return "UPDATE authors " +
				"SET authorID=" + NewAuthorID +
				" WHERE firstName='" + firstName + "' AND " + "lastName='" + lastName + "'";
	}
	
	//add a new title for an author
	private static String AddNewTitleAuthor(int isbn, String firstName, String lastName) {
		String s = "INSERT INTO authorISBN " +
				"(isbn, authorID) " +
				"VALUES (" + isbn + ", " +
						"(SELECT authorID " +
						"FROM authors " +
						"WHERE firstName='" + firstName + "' AND lastName='" + lastName + "'))";
		
		System.out.println(s);
		return s;
	}
	
	//add new publisher
	private static String AddPublisher(String publisherName) {
		return "INSERT INTO publishers " +
				"(publisherName) " +
				"VALUES ('" + publisherName + "')";
	}
	
	//edit/update existing information about a publisher
	//updates publisherID when given publisherName
	private static String UpdatePublisher(String publisherName, int NewPublisherID) {
		return "UPDATE publishers " +
				"SET publisherID=" + NewPublisherID +
				" WHERE publisherName='" + publisherName + "'";
	}
}
