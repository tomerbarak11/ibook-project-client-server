package DBcontroller;

import java.sql.Connection;

import common.dbInfo;
import common.initConnection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The Class addBook2ToDB runs a query to add book with 2 authors to database
 */
public class addBook2ToDB{

	    private static initConnection con;
       /**
        * The method addBook2ToDB runs a query to add book with 2 authors to database
        * @param author - author of the book
        * @param Title - title of the book
        * @param language - language of the book
        * @param plot - plot of the book
        * @param subject - subject of the book
        * @param scope - scope of the book
        * @param price - price of the book
        * @param Table_of_Contents - table of contents of the book
        * @param author2 - second author of the book
        * @param info - client connection
        */
		public static void addBook2ToDB(String author,String Title,String language,String plot,String subject,String scope,String price,String Table_of_Contents,String author2,String urlPDF,String urlDOC,dbInfo info) throws Exception{
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try{	    	
		    	String insertSql1 = "INSERT INTO ibook.book(author,title,plot,price,Table_of_Contents,author2,url_pdf,url_doc,language) values(?,?,?,?,?,?,?,?,?)";
		    	String insertSql2 = "INSERT INTO ibook.bookscope(ScopeName,subjectName,bookTitle) values(?,?,?)";

		    	
				PreparedStatement statement1 = (PreparedStatement)con.connect.prepareStatement(insertSql1);
				PreparedStatement statement2 = (PreparedStatement)con.connect.prepareStatement(insertSql2);

				
				statement1.setString(1, author);
		    	statement1.setString(2, Title);
		    	statement1.setString(3, plot);
		    	statement1.setString(4, price);
		    	statement1.setString(5, Table_of_Contents);
		    	statement1.setString(6, author2);
		    	statement1.setString(7, urlPDF);
		    	statement1.setString(8, urlDOC);
		    	statement1.setString(9, language);
		    	
		    	statement2.setString(1, scope);
		    	statement2.setString(2, subject);
		    	statement2.setString(3, Title);

		    	
		        statement1.executeUpdate();
		        statement2.executeUpdate();

		    	statement1.close();
		    	statement2.close();

		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}
		

}



	  

