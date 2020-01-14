package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.dbInfo;
import common.initConnection;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class getBookAuthorFromDB runs a query to get the author of the book from database
 */
public class getBookAuthorFromDB {

	private static initConnection con;
	 /**
	  * The method getBookAuthorFromDB runs a query to get the author of the book from database
	  * @param info - client connection
	  * @return - return author of the book
	  *
	  */
	 public static Object getBookAuthorFromDB(dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookAuthor = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.book");
			ResultSet result = statement.executeQuery();
			
				if (!result.isBeforeFirst()){
					bookAuthor = null;
				}
				else
					while(result.next())
			   	        bookAuthor.add(result.getString(2));
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
	    	return bookAuthor;
	 }
}
