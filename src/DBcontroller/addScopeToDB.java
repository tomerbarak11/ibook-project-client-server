package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;

/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addScopeToDB runs a query to add new scope to the book to database
 */
public class addScopeToDB {

	private static initConnection con;
	    /**
	     * The method addScopeToDB runs a query to add new scope to the book to database
	     * @param scopeName - name scope of the book
	     * @param info - client connection
	     *
	     */
		public static void addScopeToDB(String scopeName,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		    try{
		    	String insertSql = "INSERT INTO ibook.scope(scopeName) values(?)";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
				statement.setString(1, scopeName); 
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		/**
		 * The method editScopeToDB runs a query to edit scope to the book to database
		 * @param oldScope - old scope of book
		 * @param newScope - new scope of book
		 * @param info - client connection
		 * 
		 */
		public static void editScopeToDB(String oldScope,String newScope,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		    try{
		    	String insertSql = "UPDATE ibook.scope SET scopeName = '" + oldScope + "' WHERE scopeName = '" + newScope + "'";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		
}
	  

