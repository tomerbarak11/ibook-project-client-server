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
 * The Class getDetailsOfBookFromDB runs a query to get the details of the book from database
 */
public class getDetailsOfBookFromDB{
	
	private static initConnection con;
     /**
      * The method getDetailsOfBookFromDB runs a query to get the details of the book from database
      * @param titleBook - name of the book
      * @param info - client connection
      * @return - return details of the book
      *
      */
	 public static Object getDetailsOfBookFromDB(String titleBook,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    ArrayList <String> bookScope = new ArrayList <String> ();
		    try{

		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.book WHERE title= '" + titleBook + "'");
			ResultSet result = statement.executeQuery();
			System.out.println();
			
			while(result.next()){
				bookScope.add(result.getString(2));
				bookScope.add(result.getString(3));
				bookScope.add(result.getString(4));
				bookScope.add(result.getString(5));
				bookScope.add(result.getString(6));
				bookScope.add(result.getString(10));
				bookScope.add(result.getString(11));
			}
			
			result.close();
			statement.close();
	    	con.connect.close();
		    }catch (SQLException e) {
				e.printStackTrace();
			}
	    	return bookScope;
	 }
}
