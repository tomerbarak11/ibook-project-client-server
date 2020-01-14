package DBcontroller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;
/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addAuthorToDB runs a query to add author to database.
 */
public class addAuthorToDB {

	private static initConnection con;
	    /**
	     * The method addAuthorToDB runs a query to add author to database.
	     * @param newAuthor - The variable add the new author
	     * @param oldAuthor - The variable add the old author
	     * @param info - client connection
	     */
		public static void addAuthorToDB(String newAuthor,String oldAuthor,dbInfo info) throws Exception{ 
			
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try{
		    	String insertSql = "UPDATE ibook.book SET author = '" + newAuthor + "' WHERE author = '" + oldAuthor + "'";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql); 
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}

}
	  

