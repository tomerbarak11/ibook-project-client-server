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
 * The Class setReviewsStatus runs a query to set the review of the book from database
 */
public class setReviewsStatus {
	 
	private static initConnection con;
	private static int num = 1;
	 /**
	  * The method setReviewsStatus runs a query to set the review of the book from database
	  * @param reviewNEW - new review of the book
	  * @param reviewOLD - old review of the book
	  * @param info - client connection
	  * @return - return reviews of the book
	  * 
	  */
	 public static Object setReviewsStatus(String reviewNEW,String reviewOLD,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookName = new ArrayList <String> ();
		    
		    try{
		    	
		    PreparedStatement statement = con.connect.prepareStatement("UPDATE ibook.review SET status = 1 , review = '"+reviewNEW+"' WHERE review = '"+reviewOLD+"' ");
			statement.executeUpdate();
			statement.close();
			con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
	    	return bookName;
	 }
}
