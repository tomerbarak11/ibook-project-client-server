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
 * The Class getScopeFromDB runs a query to get the scope of the book from database
 * 
 */
public class getScopeFromDB {
	
	private static initConnection con;
     /**
      * The method getScopeFromDB runs a query to get the scope of the book from database
      * @param info - client connection
      * @return - return scope of the book
      * 
      */
	 public static Object getScopeFromDB(dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookScope = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.scope");
			ResultSet result = statement.executeQuery();
			while(result.next())
				bookScope.add(result.getString(1));
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
	    	return bookScope;
	 }
}
