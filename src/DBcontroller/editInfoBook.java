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
 * The Class editInfoBook runs a query to edit the information of the book to database
 */
public class editInfoBook{

	private static initConnection con;
    private static int i;   
        /**
         * The method editInfoBook runs a query to edit the information to the book to database
         * @param author - author of the book
         * @param TitleUser  - title of the book
         * @param plot  - plot of the book
         * @param price  - price of the book
         * @param TableOfContents  - table of contents of the book
         * @param TitleRow  - title of the book
         * @param info - client connection
         * 
         */
		public static void editInfoBook(String author,String TitleUser,String plot,String price,String TableOfContents,String urlPDF,String urlDOC,String TitleRow,dbInfo info) throws Exception{
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try{
		    	String insertSql1 = "UPDATE ibook.book SET author = ?, title = ?, plot = ?, price = ?, Table_of_Contents = ?, url_pdf = ?, url_doc = ? WHERE title = '"+TitleRow+"'";
		    	
				PreparedStatement statement1 = (PreparedStatement)con.connect.prepareStatement(insertSql1);
				statement1.setString(1, author);
				statement1.setString(2, TitleUser);
				statement1.setString(3, plot);
				statement1.setString(4, price);
				statement1.setString(5, TableOfContents);
				statement1.setString(6, urlPDF);
				statement1.setString(7, urlDOC);
				statement1.executeUpdate();
				statement1.close();

		    	con.connect.close();
		    	
			}catch(SQLException e){
			    	e.printStackTrace();
		    }			 
		}


}



	  

