import java.sql.*;
import java.util.*;


public class Tables {

	public static void add(String Username, String Password,
			String ConnString, String Driver) {

		Connection conn;
		PreparedStatement stmt = null;
		
		try{
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(ConnString, Username, Password);
			System.out.println("Inserting entries into tables");
						
			
			//populate authors table
			String SQLAddAuthor = "INSERT INTO authors " +
					"(firstName, lastName) " +
					"VALUES(?, ?)";
			stmt = conn.prepareStatement(SQLAddAuthor);
			
			class Author {
				String firstName, lastName;
				public Author(String firstName, String lastName) {
					this.firstName = firstName; this.lastName = lastName;
				}
			}
			List<Author> listOfAuthors = new ArrayList<Author>();
			listOfAuthors.add(new Author("J.K", "Rowling")); 
			listOfAuthors.add(new Author("F. Scott", "Fitzgerald"));
			listOfAuthors.add(new Author("Stephen", "Hawking"));
			listOfAuthors.add(new Author("Stephen", "King"));
			
			for(Author a: listOfAuthors) {
				stmt.setString(1, a.firstName);
				stmt.setString(2, a.lastName);
				stmt.execute();
			}
			
			
			
			//populate publishers table
			String SQLAddPublishers = "INSERT INTO publishers " +
										"(publisherName) " +
										"VALUES(?)";
			
			stmt = conn.prepareStatement(SQLAddPublishers);
			
			List<String> listOfPublishers = new ArrayList<String>();
			listOfPublishers.add("Bloomsbury");
			listOfPublishers.add("Bantam Spectra");
			listOfPublishers.add("Charles Scribners Sons");
			listOfPublishers.add("Viking");
			
			for(String s: listOfPublishers) {
				stmt.setString(1, s);
				stmt.execute();
			}
			
			
			
			//populate titles table
			String SQLAddTitles = "INSERT INTO titles " +
									"(isbn, title, editionNumber, copyright, publisherID, price) " +
									"VALUES(?, ?, ?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(SQLAddTitles);
			
			class titles {
				String isbn, title, copyright;
				int editionNumber, publisherID;
				float price;
			
				public titles(String isbn, String title, int editionNumber, 
						String copyright, int publisherID, float price) {
					this.isbn = isbn; this.title = title; this.editionNumber = editionNumber;
					this.copyright = copyright; this.publisherID = publisherID; this.price = price;
				}
			}
			
			List<titles> ListOfTitles = new ArrayList<titles>();
			ListOfTitles.add(new titles("978140883", "Fantastic Beasts & Where to Find Them", 1, "2001", 1, 12.50f));
			ListOfTitles.add(new titles("978159777", "The Universe in a Nutshell", 2, "2001", 2, 35.00f));
			ListOfTitles.add(new titles("978081608", "The Great Gatsby", 5, "1925", 3, 20.99f));
			ListOfTitles.add(new titles("747532745", "Harry Potter and the Philosophers Stone", 3, "1997", 1, 30.00f));
			ListOfTitles.add(new titles("978067081", "Misery", 1, "1987", 4, 50.99f));
			
			for(titles t: ListOfTitles) {
				stmt.setString(1, t.isbn);
				stmt.setString(2, t.title);
				stmt.setInt(3, t.editionNumber);
				stmt.setString(4, t.copyright);
				stmt.setInt(5, t.publisherID);
				stmt.setFloat(6, t.price);
				
				stmt.execute();
			}
			System.out.println("Finished inserting entries");
			
			
			
			
			//populate authorISBN table
			String SQLAddAuthorISBN = "INSERT INTO authorISBN " +
									"(authorID, isbn) " +
									"VALUES(?, ?)";
			
			stmt = conn.prepareStatement(SQLAddAuthorISBN);
			
			class authorISBN {
				int authorID;
				String isbn;
				public authorISBN(int authorID, String isbn) {
					this.authorID = authorID; this.isbn = isbn;
				}
			}
			
			List<authorISBN> ListOfAuthorISBN = new ArrayList<authorISBN>();
			ListOfAuthorISBN.add(new authorISBN(1, "978140883"));
			ListOfAuthorISBN.add(new authorISBN(2, "978159777"));
			ListOfAuthorISBN.add(new authorISBN(3, "978081608"));
			ListOfAuthorISBN.add(new authorISBN(1, "747532745"));
			ListOfAuthorISBN.add(new authorISBN(4, "978067081"));
			
			for(authorISBN a: ListOfAuthorISBN) {
				stmt.setInt(1, a.authorID);
				stmt.setString(2, a.isbn);
				stmt.execute();
			}
			
			
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
