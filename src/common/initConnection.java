package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.*;
/**
 * 
 * @author Hadi
 *initConnection method provide to us the  "com.mysql.jdbc.Driver" connection.
 */
public class initConnection {
	public static Connection connect;

/**
 * 
 * @param info db connection details
 * @throws SQLException if failed to connect
 */
	public initConnection(dbInfo info) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://"+info.ip+"/"+info.dbname+"?autoReconnect=true&" +
					"useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
					"serverTimezone=UTC", info.usrname, info.pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * close the connection with the data base
 * @throws SQLException for errors
 */
	public void closeConnection() throws SQLException{
		connect.close();
	}

}
