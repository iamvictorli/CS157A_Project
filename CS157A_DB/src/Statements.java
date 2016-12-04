import java.sql.*;
import java.util.Properties;

public class Statements {

	public static void query(Properties info, String ConnString, String Driver) {

		Connection conn;
		Statement stmt;
		ResultSet rs;

		try{
			Class.forName(Driver);

			conn = DriverManager.getConnection(ConnString, info);

			try {
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



				//edit author first name
				stmt.executeUpdate(UpdateAuthor("Ahmed", "Ezzat", "Dr. Ahmed"));
				System.out.println("Updating author Ahmed Ezzat to Dr. Ahmed Ezzat");
				System.out.println("Table authors after updating author");

				rs = stmt.executeQuery("SELECT * " +
										"FROM authors");

				while(rs.next()) {
					int authorId = rs.getInt("authorId");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					System.out.println("authorID: " + authorId + " firstName: " + firstName + " lastName: " + lastName);
				}

				System.out.println();



				//add a new title for an author
				System.out.println("Adding new title for author");
				System.out.println("For example: Harry Potter and the chamber of secrets by J.K Rowling ISBN: 747538492");
				stmt.executeUpdate(InsertTitle("747538492", "Harry Potter and the Chamber of Secrets", 4, "1998", 1, 21.70));
				stmt.executeUpdate(AddNewTitleAuthor("747538492", "J.K", "Rowling"));

				System.out.println("Table titles");
				rs = stmt.executeQuery("SELECT * " +
										"FROM titles");

				while(rs.next()) {
					System.out.println("ISBN: " + rs.getString("isbn") + " title: " + rs.getString("title")  + " editionNumber: " + rs.getInt("editionNumber") + " copyright: " +
							rs.getString("copyright") + " publisherID: " + rs.getInt("publisherID") + " price: " + rs.getFloat("price"));
				}

				System.out.println();
				System.out.println("Table authorISBN");
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
				System.out.println("Table publishers after updating author");

				rs = stmt.executeQuery("SELECT * " +
										"FROM publishers");

				while(rs.next()) {
					int publisherID = rs.getInt("publisherID");
					String publisherName = rs.getString("publisherName");
					System.out.println("publisherID: " + publisherID + " publisherName: " + publisherName);
				}

				rs = stmt.executeQuery("SELECT title, copyright, isbn, publisherID " +
										"FROM titles");

				System.out.println();
				System.out.println("Table titles after updating publisher");

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
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
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

	//edit/update existing information about an author
	//updates authorID when given firstName and lastName
	private static String UpdateAuthor(String firstName, String lastName, String EditFirstName) {
		return "UPDATE authors " +
				"SET firstName='" + EditFirstName + "' " +
				" WHERE firstName='" + firstName + "' AND " + "lastName='" + lastName + "'";
	}

	//add a new title for an author
	private static String InsertTitle(String isbn, String title, int editionNumber,
			String copyright, int publisherID, double price) {
		return "INSERT INTO titles " +
				"(isbn, title, editionNumber, copyright, publisherID, price) " +
				"VALUES (" + isbn + ", '" + title + "', " + editionNumber + ", " + copyright + ", " + publisherID + ", " + price + ")";
	}

	//new entry for authorISBN table
	private static String AddNewTitleAuthor(String isbn, String firstName, String lastName) {
		return "INSERT INTO authorISBN " +
				"(isbn, authorID) " +
				"VALUES (" + isbn + ", " +
						"(SELECT authorID " +
						"FROM authors " +
						"WHERE firstName='" + firstName + "' AND lastName='" + lastName + "'))";

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
