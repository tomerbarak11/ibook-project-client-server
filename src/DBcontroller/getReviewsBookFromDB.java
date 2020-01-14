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
 * The Class getReviewsBookFromDB runs a query to get the reviews of the book from database
 */
public class getReviewsBookFromDB {
	 
	private static initConnection con;
	 /**
	  * The method getReviewsBookFromDB runs a query to get the reviews of the book from database
	  * @param titleName - title of the book
	  * @param info - client connection
	  * @return - return reviews of the book
	  * 
	  */
	 public static Object getReviewsBookFromDB(String titleName,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookName = new ArrayList <String> ();
		    
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.review WHERE status = 0 AND bookTitle = '" + titleName + "'");
			ResultSet result = statement.executeQuery();
			
			  while(result.next())
			    bookName.add(result.getString(4));
			
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
	    	return bookName;
	 }
}
