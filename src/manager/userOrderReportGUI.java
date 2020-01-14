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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import common.*;

import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JTextField;


/**
 * manager menu
 * @author Hadi
 * GUI class that show report for user orders 
 * between date selected
 */
public class userOrderReportGUI extends JFrame {

	public JFrame frame;
	private JTextArea fromServer;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	public dbInfo info;
	private JTextField from;
	private JTextField to;

	/**
	 * 
	 * @param the constructor get info parameter from dbInfo class that include the connection details
	 * and initialize the userOrderReportGUI frame 
	 */
	public userOrderReportGUI(dbInfo info) {
		this.info=info;
		try {
			this.con = new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("User Order Report");
		frame.setBounds(100, 100, 749, 407);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCostumerName = new JLabel("customer Name:");
		lblCostumerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCostumerName.setBounds(12, 23, 134, 20);
		frame.getContentPane().add(lblCostumerName);

		ArrayList<String> str = getUserFromDB("SELECT * FROM ibook.users");
		String[] user = new String[str.size()];
		for (int i = 0; i < str.size(); i++)
			user[i] = str.get(i);

		JComboBox comboBox = new JComboBox(user);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(12, 44, 189, 23);
		comboBox.setRenderer(new MyComboBoxRenderer("Select Customer"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);

		JButton btnOk;
		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		/**
		 * user press ok button 
		 * addActionListener checks if user select user name from comboBox
		 * and insert correct date with correct format
		 * then it send request to DB
		 * the user order report shows in the same frame
		 */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromServer.setText("");
				String From = from.getText().toString();
				String To = to.getText().toString();
				if (comboBox.getSelectedItem() != null && From != null && To != null) {
					if (From.length() == 10 && To.length() == 10) {
						if (From.charAt(0) >= '0' && From.charAt(1) >= '0' && From.charAt(2) >= '0'
								&& From.charAt(3) >= '0' && From.charAt(0) <= '9' && From.charAt(1) <= '9'
								&& From.charAt(2) <= '9' && From.charAt(3) <= '9' && From.charAt(4) == '-'
								&& From.charAt(5) >= '0' && From.charAt(6) >= '0' && From.charAt(5) <= '9'
								&& From.charAt(6) <= '9' && From.charAt(7) == '-' && From.charAt(8) >= '0'
								&& From.charAt(9) >= '0' && From.charAt(8) <= '9' && From.charAt(9) <= '9') {
							try {
								con = new initConnection(info);
								ArrayList<String> str = getOrderFromDB("SELECT * from ibook.bookorder WHERE userName= '"
										+ comboBox.getSelectedItem().toString() + "'", From, To);
								String[] order = new String[str.size()];
								for (int i = 0; i < str.size(); i++) {
									order[i] = str.get(i);
									fromServer.append(order[i] + "\n");
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "please insert correct Date");
						}
					} else {
						JOptionPane.showMessageDialog(null, "please insert correct Date");
					}
				} else {
					JOptionPane.showMessageDialog(null, "please select Customer OR insert correct Date");
				}

			}
		});
		btnOk.setBounds(12, 306, 89, 23);
		frame.getContentPane().add(btnOk);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(379, 13, 352, 340);
		frame.getContentPane().add(scrollPane);

		fromServer = new JTextArea();
		fromServer.setFont(new Font("Courier New", Font.PLAIN, 12));
		fromServer.setTabSize(10);
		fromServer.setEditable(false);
		scrollPane.setViewportView(fromServer);

		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);

		JLabel lblOrderid = new JLabel("orderID    ");
		lblOrderid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblOrderid);

		JLabel lblUserid = new JLabel("bookName                                         ");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblUserid);

		JLabel lblOrderdate = new JLabel("orderDate ");
		lblOrderdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblOrderdate);

		JLabel lblDate = new JLabel("insert Date");
		lblDate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblDate.setBounds(12, 144, 97, 20);
		frame.getContentPane().add(lblDate);

		JLabel lblDdmmyy = new JLabel("YYYY-MM-DD");
		lblDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDdmmyy.setBounds(22, 241, 152, 16);
		frame.getContentPane().add(lblDdmmyy);

		JLabel lblFrom = new JLabel("from:");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFrom.setBounds(12, 177, 56, 16);
		frame.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to:");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTo.setBounds(200, 178, 56, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYyyymmdd.setBounds(210, 241, 140, 16);
		frame.getContentPane().add(lblYyyymmdd);

		from = new JTextField();
		from.setFont(new Font("Tahoma", Font.PLAIN, 11));
		from.setBounds(12, 206, 162, 22);
		frame.getContentPane().add(from);
		from.setColumns(10);

		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 11));
		to.setBounds(200, 206, 150, 22);
		frame.getContentPane().add(to);
		to.setColumns(10);

	}

	/**
	 * 
	 * @param s SQL Query
	 * @return users list from dataBase
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
	 * @param s SQL query
	 * @param from 
	 * @param to  
	 * search data between from to dates
	 * @return data founded in dataBase
	 * the data is orders to user selected
	 */
	private ArrayList<String> getOrderFromDB(String s, String from, String to) {

		ArrayList<String> users = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			String date;
			String str="";

			int i, j;
			while (result.next()) {
				str="";
				str.length();
				date = result.getString(4);
				i = date.compareTo(from);
				j = date.compareTo(to);
				if (i >= 0 && j <= 0){
					for(int k=0;k<32-result.getString(2).length();k++)
						str+=" ";
					users.add(result.getString(1) + "      " + result.getString(2) + str + result.getString(4));
				}
				}
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return users;
	}
}
