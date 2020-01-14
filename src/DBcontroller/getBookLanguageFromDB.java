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
 * The Class getBookLanguageFromDB runs a query to get the language of the book from database
 */
public class getBookLanguageFromDB {
	 
	private static initConnection con;
	 /**
	  * The method getBookLanguageFromDB runs a query to get the language of the book from database
	  * @param titleBook - title of the book
	  * @param info - client connection
	  * @return - return language of the book
	  * 
	  */
	 public static Object getBookLanguageFromDB(String titleBook,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    ArrayList <String> bookName = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.bookcopies WHERE bookTitle= '" + titleBook + "'");
			ResultSet result = statement.executeQuery();
			while(result.next())
				bookName.add(result.getString(3));
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
	    	return bookName;
	 }
}
