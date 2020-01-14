package manager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import common.MyComboBoxRenderer;
import common.dbInfo;
import common.initConnection;

import javax.swing.JOptionPane;
import java.awt.Font;
/**
 * @author Hadi
 * GUI class that disbale book temporary without remove it from DB
 */
public class disableEnableBookTemporaryGUI extends JFrame {

	public JFrame frame;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	private dbInfo info;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	/**
	 * 
	 * @param the constructor get info parameter from dbInfo class that include the connection details
	 * and initialize the disableEnableBookTemporaryGUI frame 
	 */
	public disableEnableBookTemporaryGUI(dbInfo info) {
		this.info=info;

		try {
			con = new initConnection(info);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("Disable/Enable Book Temporary");
		frame.setBounds(100, 100, 598, 276);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblBookName = new JLabel("Disable:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookName.setBounds(12, 23, 94, 20);
		frame.getContentPane().add(lblBookName);

		ArrayList<String> str = getBookFromDB("SELECT * FROM book WHERE status=1");
		String[] book = new String[str.size()];
		for (int i = 0; i < str.size(); i++)
			book[i] = str.get(i);

		comboBox = new JComboBox(book);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(12, 44, 255, 23);
		comboBox.setRenderer(new MyComboBoxRenderer("Select Book"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);

		JLabel lblEnable = new JLabel("Enable:");
		lblEnable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnable.setBounds(307, 23, 94, 20);
		frame.getContentPane().add(lblEnable);
		
			try {
				con = new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
		ArrayList<String> str1 = getBookFromDB("SELECT * FROM book WHERE status=0");
		String[] book1 = new String[str1.size()];
		for (int i = 0; i < str1.size(); i++)
			book1[i] = str1.get(i);
		comboBox_1 = new JComboBox(book1);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	
		comboBox_1.setBounds(307, 44, 261, 23);
		comboBox_1.setRenderer(new MyComboBoxRenderer("Select Book"));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setToolTipText("");
		frame.getContentPane().add(comboBox_1);	

		JButton btnOk;
		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		/**
		 * user press ok button 
		 * addActionListener checks if user  at least select book name from comboBox (disable/enable)
		 * then it send request to DB
		 * the pop-up message if successes
		 */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					try {
						con = new initConnection(info);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						insertIntoDB("UPDATE book SET status= '" + 0 + "'WHERE title= '"+ comboBox.getSelectedItem().toString() + "'");
						JOptionPane.showMessageDialog(null, comboBox.getSelectedItem() + " Disabled Temporary");
						comboBox.removeAllItems();
						comboBox_1.removeAllItems();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (comboBox_1.getSelectedItem() != null){
					try {
						con = new initConnection(info);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						insertIntoDB("UPDATE book SET status= '" + 1 + "'WHERE title= '"+ comboBox_1.getSelectedItem().toString() + "'");
						JOptionPane.showMessageDialog(null, comboBox_1.getSelectedItem() + " Enabled");
						comboBox_1.removeAllItems();
						comboBox.removeAllItems();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "please select Book");
				
				
				try {
					con = new initConnection(info);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				ArrayList<String> str = getBookFromDB("SELECT * FROM book WHERE status=1");
				String[] book = new String[str.size()];
				for (int i = 0; i < str.size(); i++)
					comboBox.addItem( str.get(i));
				
				comboBox.setRenderer(new MyComboBoxRenderer("Select Book"));
				comboBox.setSelectedIndex(-1);
				comboBox.setToolTipText("");
				frame.getContentPane().add(comboBox);					
				
				
				try {
					con = new initConnection(info);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				str = getBookFromDB("SELECT * FROM book WHERE status=0");
				book = new String[str.size()];
				for (int i = 0; i < str.size(); i++)
					comboBox_1.addItem( str.get(i));
				
				comboBox_1.setRenderer(new MyComboBoxRenderer("Select Book"));
				comboBox_1.setSelectedIndex(-1);
				comboBox_1.setToolTipText("");
				frame.getContentPane().add(comboBox_1);	
			}
		});
		btnOk.setBounds(243, 180, 89, 23);
		frame.getContentPane().add(btnOk);
	}
/**
 * 
 * @param s SQL Query
 * @return books list
 */
	private ArrayList<String> getBookFromDB(String s) {

		ArrayList<String> data = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while (result.next())
				data.add(result.getString(3));
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}

	/**
	 * method that insert relevant data into DB (disable or enable book
	 * @param SQL Query  
	 * @throws SQLException if the query failed throw relevant exception 
	 */
	public void insertIntoDB(String Query) throws SQLException {
		PreparedStatement statement = (PreparedStatement) con.connect.prepareStatement(Query);
		statement.executeUpdate();
		statement.close();
		con.connect.close();
	}
}
