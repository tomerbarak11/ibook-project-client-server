package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class deleteBookFromDB runs a query to delete a book from database
 */
public class deleteBookFromDB{

	private static initConnection con;
        /**
         * The method deleteBookFromDB runs a query to delete a book from database
         * @param TitleUser - title of the book
         * @param info - client connection
         * 
         */
		public static void deleteBookFromDB(String TitleUser,dbInfo info) throws Exception{
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try{
		    	String insertSql1 = "DELETE FROM ibook.book WHERE Title = '" + TitleUser + "'";		    	
				PreparedStatement statement1 = (PreparedStatement)con.connect.prepareStatement(insertSql1);
				statement1.executeUpdate();
				statement1.close();
		    	con.connect.close();
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
}



	  

