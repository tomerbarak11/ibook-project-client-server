package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;

/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addPlotToDB runs a query to add new plot to the book to database
 */
public class addPlotToDB {

	private static initConnection con;
	    /**
	     * The method addPlotToDB runs a query to add new plot to the book to database
	     * @param plot - The plot of the book
	     * @param info - client connection
	     * 
	     */
		public static void addPlotToDB(String plot,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try{
		    	String insertSql = "INSERT INTO ibook.book(plot) values(?)";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
				statement.setString(5, plot); 
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		
}
	  

