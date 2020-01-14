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
import java.util.Date;

import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JTextField;


/**
 * manager menu
 * @author Hadi
 * GUI class that show report for book selected by user
 * between date selected  
 * 
 */
public class bookOrderReportGUI extends JFrame {

	public JFrame frame;
	private JTextArea fromServer;
	private JTextArea numOfSearches;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	public dbInfo info;
	private JTextField from;
	private JTextField to;
	
	
	/**
	 * 
	 * @param the constructor get info parameter from dbInfo class that include the connection details
	 * and initialize the bookOrderReportGUI frame 
	 */
	@SuppressWarnings("unchecked")
	public bookOrderReportGUI(dbInfo info) {
		this.info=info;

		try {
			this.con = new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("Book Order Report");
		frame.setBounds(100, 100, 795, 451);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCostumerName = new JLabel("book Name:");
		lblCostumerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCostumerName.setBounds(12, 23, 134, 20);
		frame.getContentPane().add(lblCostumerName);

		ArrayList<String> str = getBookFromDB("SELECT * FROM ibook.book");
		String[] user = new String[str.size()];
		for (int i = 0; i < str.size(); i++)
			user[i] = str.get(i);

		JComboBox comboBox = new JComboBox(user);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(12, 44, 284, 23);
		comboBox.setRenderer(new MyComboBoxRenderer("Select book"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);
		JButton btnOk;
		btnOk = new JButton("OK");
		
	/**
	 * user press ok button 
	 * addActionListener checks if user select book name from comboBox
	 * and insert correct date with correct format
	 * then it send request to DB
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
								ArrayList<String> str = getOrderFromDB("SELECT * from ibook.bookorder WHERE bookTitle= '"
										+ comboBox.getSelectedItem().toString() + "'", From, To);
								if(!str.isEmpty()){
								String[] order = new String[str.size()];
								for (int i = 0; i < str.size(); i++) {
									order[i] = str.get(i);
									fromServer.append(order[i] + " \n");
								}
								numOfSearches.setText(" ");
								con = new initConnection(info);
								 str = getSearchesFromDB("SELECT * from ibook.book WHERE title= '"+ comboBox.getSelectedItem().toString() + "'");
								 order[0]=str.get(0);
								 numOfSearches.append(order[0]);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "please insert correct Date");
						}
					} else {
						JOptionPane.showMessageDialog(null, "please insert correct Date");
					}
				} else {
					JOptionPane.showMessageDialog(null, "please select book OR insert correct Date");
				}

			}
		});
		btnOk.setBounds(12, 306, 89, 23);
		frame.getContentPane().add(btnOk);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(467, 13, 290, 337);
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

		JLabel lblUserid = new JLabel("ReaderName                     ");
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
		lblDdmmyy.setBounds(22, 241, 137, 16);
		frame.getContentPane().add(lblDdmmyy);

		JLabel lblFrom = new JLabel("from:");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFrom.setBounds(12, 177, 56, 16);
		frame.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to:");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTo.setBounds(200, 177, 56, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYyyymmdd.setBounds(200, 243, 154, 16);
		frame.getContentPane().add(lblYyyymmdd);

		from = new JTextField();
		from.setFont(new Font("Tahoma", Font.PLAIN, 11));
		from.setBounds(12, 206, 147, 22);
		frame.getContentPane().add(from);
		from.setColumns(10);

		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 11));
		to.setBounds(199, 206, 155, 22);
		frame.getContentPane().add(to);
		to.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(308, 13, 147, 70);
		frame.getContentPane().add(scrollPane_1);
		
		numOfSearches = new JTextArea();
		numOfSearches.setTabSize(10);
		numOfSearches.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numOfSearches.setEditable(false);
		scrollPane_1.setViewportView(numOfSearches);
		
		JLabel lblNumofsearches = new JLabel("numOfSearches");
		lblNumofsearches.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setColumnHeaderView(lblNumofsearches);

	}
	/**
	 * 
	 * @param s is a SQL query
	 * @return the book list from DataBase
	 */
	private ArrayList<String> getBookFromDB(String s) {

		ArrayList<String> book = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while (result.next())
				book.add(result.getString(3));
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return book;
	}

	/**
	 * 
	 * @param s SQL query
	 * @param from 
	 * @param to  
	 * search data between from to dates
	 * @return data founded in dataBase
	 */
	private ArrayList<String> getOrderFromDB(String s, String from, String to) {

		ArrayList<String> books = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			String str="";
			String date;
			int i, j;
			while (result.next()) {
				str="";
				date = result.getString(4);
				i = date.compareTo(from);
				j = date.compareTo(to);
				if (i >= 0 && j <= 0){

				for(int k=0;k<20-result.getString(3).length();k++)
					str+=" ";
				books.add(result.getString(1) + "       " + result.getString(3) + str + result.getString(4));
			}
			}
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return books;
	}
	
	/**
	 * 
	 * @param s dataBase query
	 * @return number of searches for book selected
	 */
	private ArrayList<String> getSearchesFromDB(String s) {

		ArrayList<String> num = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while (result.next())
				num.add(result.getString(7));
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return num;
	}
}
