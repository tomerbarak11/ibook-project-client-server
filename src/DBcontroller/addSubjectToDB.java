package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.dbInfo;
import common.initConnection;

/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addSubjectToDB runs a query to add new subject to the book to database
 */
public class addSubjectToDB {

	private static initConnection con;
	    /**
	     * The method addSubjectToDB runs a query to add new subject to the book to database
	     * @param scopeName - scope name of the book
	     * @param subjectName - subject name of the book
	     * @param info - client connection
	     */
		public static void addSubjectToDB(String scopeName,String subjectName,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		    try{
		    	String insertSql = "INSERT INTO ibook.subjectscope(ScopeName,subjectName) values(?,?)";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
				statement.setString(1, scopeName); 
				statement.setString(2, subjectName); 
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		/**
		 * The method editSubjectToDB runs a query to edit subject to the book to database
		 * @param newSubject - new subject of book
		 * @param oldSubject - old subject of book
		 * @param info - client connection
		 * 
		 */
		public static void editSubjectToDB(String newSubject,String oldSubject,dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		    try{
		    	String insertSql = "UPDATE ibook.subjectscope SET subjectName = '" + newSubject + "' WHERE subjectName = '" + oldSubject + "'";
				PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
		    	statement.executeUpdate();
		    	statement.close();
		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		
}
	  

