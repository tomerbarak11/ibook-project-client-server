package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;

/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addNameToDB runs a query to add new title to the book to database
 */
public class addNameToDB {

	private static initConnection con;
	
	/**
	 * The method addNameToDB runs a query to add new title to the book to database
	 * @param newName - new title of the book
	 * @param oldName - old title of the book
	 * @param info - client connection
	 */
		public static void addNameToDB(String newName,String oldName,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try{
		    	String insertSql = "UPDATE ibook.book SET title = '" + newName + "' WHERE title = '" + oldName + "'";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		
}
	  

