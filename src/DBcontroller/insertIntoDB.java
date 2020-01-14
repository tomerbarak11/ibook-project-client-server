package DBcontroller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.initConnection;

import common.*;

public class insertIntoDB {

	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;

	public boolean insertIntoDB(String Query,dbInfo info) throws SQLException {
		try {
			this.con = new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PreparedStatement statement = (PreparedStatement) con.connect.prepareStatement(Query);
			statement.executeUpdate();
			statement.close();
			con.connect.close();

		return false;
	}
}
