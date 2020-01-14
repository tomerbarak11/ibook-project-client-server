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
import server.serverGUI;

import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JTextField;
/**
 * 
 * @author Hadi
 * GUI class that show book orders report 
 * between date selected  
 * 
 */
public class bookPopularityReportGUI extends JFrame {

	public JFrame frame;
	private JTextArea fromServer;
	private JTextArea numOfSearches;

	private initConnection con; 	/** this initialize the connection to the data Base */
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
	public bookPopularityReportGUI(dbInfo info) {
		this.info=info;
		try {
			this.con = new initConnection(info);
			//this.con = new initConnection();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("Book Popularity Report");
		frame.setBounds(100, 100, 849, 451);
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
		comboBox.setBounds(12, 44, 284, 20);
		comboBox.setRenderer(new MyComboBoxRenderer("Select book"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);

		JButton btnOk;
		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		/**
		 * user press ok button 
		 * addActionListener checks if user select book name from comboBox
		 * and insert correct date with correct format
		 * then it send request to DB
		 * frame show results in the same frame
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
								//con = new initConnection();

								con = new initConnection(info);
								ArrayList<String> list = getBookFromDB("SELECT * from book");
								String[] books= new String [list.size()];
								for (int i = 0; i < str.size(); i++) 
									books[i] = str.get(i);
								//con = new initConnection();
								con = new initConnection(info);
								ArrayList<String> str = getOrderFromDB("SELECT * from ibook.bookorder", From, To, books);
								if(!str.isEmpty()){
									str.sort(null);
									String space;
											ArrayList<String> str2;
											str2=(ArrayList<String>) str.stream().distinct().collect(Collectors.toList());
											int[] count=new int[str2.size()];
											str.add(" ");
											for(int i=0;i<count.length;i++)
												count[i]=1;
											String[] order = new String[str2.size()];
											int z=0;
										for (int i = 0; i < str.size()-1; i++) {
											order[z]=str2.get(z);
											while(str.get(i).equals(str.get(i+1))){
													count[z]++;
													i++;
											}
											String len=" ";
											for(int k1=0;k1<40-order[z].length();k1++)
												len+=" ";
											fromServer.append(order[z] + len +count[z]+" \n");
											z++;

								}
										String len=" ";
										for (int q = 0; q < list.size(); q++) {
											if(!str.contains(list.get(q).toString())){
												len=" ";
												for(int k1=0;k1<40-list.get(q).length();k1++)
													len+=" ";
												fromServer.append(list.get(q).toString() + len +'0'+" \n");
											}
										}
								}
								numOfSearches.setText(" ");
								con = new initConnection(info);
								//con = new initConnection();
								 str = getSearchesFromDB("SELECT * from ibook.book WHERE title= '"+ comboBox.getSelectedItem().toString() + "'");
								 String s;
								 s=str.get(0);
								 numOfSearches.append(s);
								}
							 catch (SQLException e1) {
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
		scrollPane.setBounds(467, 13, 352, 337);
		frame.getContentPane().add(scrollPane);

		fromServer = new JTextArea();
		fromServer.setFont(new Font("Courier New", Font.PLAIN, 12));
		fromServer.setTabSize(10);
		fromServer.setEditable(false);
		scrollPane.setViewportView(fromServer);

		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);

		JLabel lblUserid = new JLabel("BookName                                                   ");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblUserid);

		JLabel lblOrderdate = new JLabel("Popularity");
		lblOrderdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblOrderdate);

		JLabel lblDate = new JLabel("insert Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		lblTo.setBounds(178, 177, 56, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYyyymmdd.setBounds(185, 241, 142, 16);
		frame.getContentPane().add(lblYyyymmdd);

		from = new JTextField();
		from.setFont(new Font("Tahoma", Font.PLAIN, 11));
		from.setBounds(12, 206, 147, 22);
		frame.getContentPane().add(from);
		from.setColumns(10);

		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 11));
		to.setBounds(180, 206, 147, 22);
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
		
		JLabel lblNumofsearches = new JLabel("num Of Searches");
		lblNumofsearches.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setColumnHeaderView(lblNumofsearches);

	}

	/**
	 * 
	 * @param s is a SQL query
	 * @return the books list from DataBase
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
	 * search data (orders) between from to dates
	 * @return data founded in dataBase
	 */
	private ArrayList<String> getOrderFromDB(String s, String from, String to, String[] book) {

		ArrayList<String> books = new ArrayList<String>();
		ArrayList<String> book2 = new ArrayList<String>();

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
				if (i >= 0 && j <= 0)
					books.add(result.getString(2));
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
