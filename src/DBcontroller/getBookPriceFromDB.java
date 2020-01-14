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
 * The Class getBookPriceFromDB runs a query to get the price of the book from database
 */
public class getBookPriceFromDB {
	 
	private static initConnection con;
	 /**
	  * The method getBookPriceFromDB runs a query to get the price of the book from database
	  * @param info - client connection
	  * @return - return price of the book
	  * 
	  */
	 public static Object getBookPriceFromDB(dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    ArrayList <String> bookName = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.book");
			ResultSet result = statement.executeQuery();
			while(result.next())
				bookName.add(result.getString(8));
			result.close();
			statement.close();
	    	con.connect.close();
		    }catch (SQLException e) {
				e.printStackTrace();
			}
	    	return bookName;
	 }
}
