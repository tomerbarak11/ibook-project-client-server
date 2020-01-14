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
 * The Class getLevelAccess runs a query to get the level access of the user from database
 */
public class getLevelAccess{
	
	private static initConnection con;
     /**
      * The method getLevelAccess runs a query to get the level access of the user from database
      * @param info - client connection
      * @return - return level access of the user
      *
      */
	 public static int getLevelAccess(dbInfo info) throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    int bookScope = 0;
		    
		    try{

		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.users WHERE loginStatus = 1");
			ResultSet result = statement.executeQuery();
			System.out.println();
			
			while(result.next()){
				bookScope = result.getInt(4);
			}
			
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
	    	return bookScope;
	 }
}
