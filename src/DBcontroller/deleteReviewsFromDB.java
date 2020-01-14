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
 * The Class deleteReviewsFromDB runs a query to delete a review from database
 */
public class deleteReviewsFromDB{

	private static initConnection con;
        /**
         * The method deleteReviewsFromDB runs a query to delete a review from database
         * @param review - review of the book
         * @param info - client connection
         * 
         */
		public static void deleteReviewsFromDB(String review,dbInfo info) throws Exception{
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try{
		    	String insertSql1 = "DELETE FROM ibook.review WHERE review = '" + review + "'";		    	
				PreparedStatement statement1 = (PreparedStatement)con.connect.prepareStatement(insertSql1);
				statement1.executeUpdate();
				statement1.close();
		    	con.connect.close();
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
}



	  

