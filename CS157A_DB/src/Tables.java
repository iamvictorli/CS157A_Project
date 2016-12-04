import java.sql.*;
import java.util.*;


public class Tables {

	public static void add(Properties info, String ConnString, String Driver) {

		Connection conn;
		PreparedStatement stmt = null;

		try{
			Class.forName(Driver);

			conn = DriverManager.getConnection(ConnString, info);

			try{
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
				listOfAuthors.add(new Author("John", "Steinback"));
				listOfAuthors.add(new Author("Harper", "Lee"));
				listOfAuthors.add(new Author("J.D", "Salinger"));
				listOfAuthors.add(new Author("William", "Golding"));
				listOfAuthors.add(new Author("George", "Orwell"));
				listOfAuthors.add(new Author("Mark", "Twain"));
				listOfAuthors.add(new Author("Charles", "Dickens"));
				listOfAuthors.add(new Author("Mary", "Shelley"));
				listOfAuthors.add(new Author("Herman", "Melville"));
				listOfAuthors.add(new Author("Lewis", "Carroll"));
				listOfAuthors.add(new Author("Oscar", "Wilde"));
				listOfAuthors.add(new Author("Bram", "Stoker"));
				listOfAuthors.add(new Author("Khaled", "Hosseini"));

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
				listOfPublishers.add("Covici Friede");
				listOfPublishers.add("J. B. Lippincott & Co.");
				listOfPublishers.add("Little, Brown and Company");
				listOfPublishers.add("Faber and Faber");
				listOfPublishers.add("Secker and Warburg");
				listOfPublishers.add("American Publishing Company");
				listOfPublishers.add("Chapen and Hall");
				listOfPublishers.add("Lackington, Hughes, Harding, Mavor & Jones");
				listOfPublishers.add("Harper & Brothers");
				listOfPublishers.add("Macmilliam");
				listOfPublishers.add("Lippincott's Monthly Magazine");
				listOfPublishers.add("Archibald Constable and Company");
				listOfPublishers.add("Riverhead Books");


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
				ListOfTitles.add(new titles("764586769", "Of Mice and Men", 4, "1937", 5, 6.15f));
				ListOfTitles.add(new titles("978081038", "To Kill a MockingBird", 12, "1960", 6, 9.82f));
				ListOfTitles.add(new titles("185435437", "The Catcher in the Rye", 8, "1951", 7, 25.39f));
				ListOfTitles.add(new titles("571056862", "Lord of the Files", 34, "1954", 8, 18.30f));
				ListOfTitles.add(new titles("978014139", "Animal Farm", 51, "1945", 9, 13.49f));
				ListOfTitles.add(new titles("978142349", "The Adventure of Tom Sawyer", 6, "1876", 10, 4.79f));
				ListOfTitles.add(new titles("393960692", "Great Expectations", 4, "1861", 11, 9.99f));
				ListOfTitles.add(new titles("978185124", "Frankenstein", 23, "1818", 12, 7.99f));
				ListOfTitles.add(new titles("393096704", "Moby Dick", 5, "1851", 13, 4.95f));
				ListOfTitles.add(new titles("978041507", "Alice's Adventures in Wonderland", 20, "1865", 14, 4.95f));
				ListOfTitles.add(new titles("978067406", "The Picture of Dorian Gray", 19, "1890", 15, 21.99f));
				ListOfTitles.add(new titles("973577197", "Dracula", 90, "1897", 16, 12.97f));
				ListOfTitles.add(new titles("157322245", "The Kite Runner", 5, "2003", 17, 23.59f));

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
				ListOfAuthorISBN.add(new authorISBN(5, "764586769"));
				ListOfAuthorISBN.add(new authorISBN(6, "978081038"));
				ListOfAuthorISBN.add(new authorISBN(7, "185435437"));
				ListOfAuthorISBN.add(new authorISBN(8, "571056862"));
				ListOfAuthorISBN.add(new authorISBN(9, "978014139"));
				ListOfAuthorISBN.add(new authorISBN(10, "978142349"));
				ListOfAuthorISBN.add(new authorISBN(11, "393960692"));
				ListOfAuthorISBN.add(new authorISBN(12, "978185124"));
				ListOfAuthorISBN.add(new authorISBN(13, "393096704"));
				ListOfAuthorISBN.add(new authorISBN(14, "978041507"));
				ListOfAuthorISBN.add(new authorISBN(15, "978067406"));
				ListOfAuthorISBN.add(new authorISBN(16, "973577197"));
				ListOfAuthorISBN.add(new authorISBN(17, "157322245"));

				for(authorISBN a: ListOfAuthorISBN) {
					stmt.setInt(1, a.authorID);
					stmt.setString(2, a.isbn);
					stmt.execute();
				}


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

}
