package manager;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.*;
import java.awt.Font;
/**
 *  @author Hadi
 * this class responsible for update access level to worker or to customer
 * the the access level is between 1-3
 * 3 for manager
 * 2 for librarian
 * 1 for customer
 */
public class accessLevelGUI extends JFrame {

	public JFrame frame;  
	private JTextField textField;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	public dbInfo info;

/**
 * 
 * @param the constructor get info parameter from dbInfo class that include the connection details
 * and initialize the accessLevel GUI 
 */
	public accessLevelGUI(dbInfo info) {
		this.info=info;

		try {
			con=new initConnection(info);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("change Access Level");
		frame.setBounds(100, 100, 386, 262);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCostumerName = new JLabel("User Name:");
		lblCostumerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCostumerName.setBounds(12, 11, 134, 20);
		frame.getContentPane().add(lblCostumerName);

		/**
		 * str get the users list from dataBase
		 */
		ArrayList<String> str = getUserFromDB("SELECT * FROM ibook.users");
		String[] user = new String[str.size()];
		for (int i = 0; i < str.size(); i++)
			user[i] = str.get(i);

		JComboBox comboBox = new JComboBox(user);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(12, 44, 158, 23);
		comboBox.setRenderer(new MyComboBoxRenderer("Select User"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setFont(new Font("Courier New", Font.PLAIN, 9));
		textField.setBounds(238, 65, 110, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewAcessFrom = new JLabel(" New Acess from 1-3");
		lblNewAcessFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcessFrom.setBounds(238, 13, 209, 31);
		frame.getContentPane().add(lblNewAcessFrom);
		JButton btnOk;
	btnOk = new JButton("OK");
	btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
	
	
	/**
	 * addActionListener for OK button, it check first if the user insert
	 * user press ok button 
	 * addActionListener checks if user select the user to update 
	 * and insert correct number between 1-3.
	 * if true the database will update the user access level
	 */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null && !textField.getText().isEmpty()){
					if(Integer.parseInt(textField.getText()) > 0 && Integer.parseInt(textField.getText()) < 4) {
							try {
								con = new initConnection(info);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								insertIntoDB("UPDATE ibook.users SET accessLevel= '"+textField.getText()+"'WHERE userName= '"+comboBox.getSelectedItem().toString() + "'");
								JOptionPane.showMessageDialog(null,comboBox.getSelectedItem()+" AccessLevel Updated Successfuly" );
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					else {
					JOptionPane.showMessageDialog(null, "please insert correct Access Level");
				}

			}
				else {
					JOptionPane.showMessageDialog(null, "please select Customer and Access Level");
			}
			}
		});
		
		btnOk.setBounds(239, 143, 89, 23);
		frame.getContentPane().add(btnOk);

	}

	/**
	 * 
	 * @param s is a SQL query
	 * @return the user list from DataBase
	 */
	private ArrayList<String> getUserFromDB(String s) {

		ArrayList<String> users = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while (result.next())
				users.add(result.getString(2));
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return users;
	}
	
	
	/**
	 * 
	 * @param Query to insert into DataBase
	 * @throws SQLException if there is an issue the system will throw an exception
	 */
	public void insertIntoDB(String Query) throws SQLException {
		PreparedStatement statement = (PreparedStatement) con.connect.prepareStatement(Query);
			statement.executeUpdate();
			statement.close();
			con.connect.close();
	}

}
