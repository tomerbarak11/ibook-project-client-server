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
 * The Class getSubjectFromDB runs a query to get the subject of the book from database
 */
public class getSubjectFromDB {

	private static initConnection con;
	 /**
	  * The method getSubjectFromDB runs a query to get the subject of the book from database
	  * @param info - client connection
	  * @return - return subject of the book
	  *
	  */
	 public static Object getSubjectFromDB(dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookSubject = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.subjectscope");
			ResultSet result = statement.executeQuery();
			while(result.next())
				bookSubject.add(result.getString(2));
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    return bookSubject;
	 }
	 
	 public static Object getSubjectFromDB(dbInfo info,String scopeName) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		    ArrayList <String> bookSubject = new ArrayList <String> ();
		    try{
		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.subjectscope WHERE ScopeName = '" + scopeName + "'");
			ResultSet result = statement.executeQuery();
			while(result.next()){
				bookSubject.add(result.getString(2));
			}
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    return bookSubject;
	 }
	 
	 
}
