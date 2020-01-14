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
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
	import java.awt.Component;
	import javax.swing.JMenuBar;
	import java.awt.Font;
	import javax.swing.JTextField;
	/**
	 * 
	 * @author Hadi
	 * GUI class that show report for book selected by user in scope 
	 * between date selected  
	 * 
	 */
public class bookPopularityReportByScopeGUI extends JFrame {
	public initConnection con;
	public PreparedStatement statement;
	public ResultSet result;
		public JFrame frame;
		private JTextArea fromServer;
		private JTextArea numOfSearches;
		private JTextArea textAreaScope;
		public dbInfo info;
		private JTextField from;
		private JTextField to;
		private JLabel ScopeLbl;
		private int flag;

		
		/**
		 * 
		 * @param the constructor get info parameter from dbInfo class that include the connection details
		 * and initialize the bookPopularityReportByScopeGUI frame 
		 */
		@SuppressWarnings("unchecked")
		public bookPopularityReportByScopeGUI(dbInfo info) {
			this.info=info;
			try {
				con = new initConnection(info);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame = new JFrame();
			frame.setTitle("Book Popularity Report By Scope");
			frame.setBounds(100, 100, 780, 448);
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
			comboBox.setRenderer(new MyComboBoxRenderer("Select Book"));
			comboBox.setSelectedIndex(-1);
			comboBox.setToolTipText("");
			frame.getContentPane().add(comboBox);

			JButton btnOk;
			btnOk = new JButton("OK");
			btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
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
									con = new initConnection(info);
									flag=1;
									ArrayList<String> str = getScopeFromDB("SELECT * FROM bookscope WHERE bookTitle='"+comboBox.getSelectedItem().toString()+"'");
									String scope = str.get(0);
									con = new initConnection(info);
										str = getDataFromDB("SELECT * FROM bookscope, bookorder WHERE bookscope.scopeName='"+scope.toString()+"' AND bookscope.bookTitle=bookorder.bookTitle", From, To);									
										 textAreaScope.setText(" ");
										 textAreaScope.append(scope);
										if(!str.isEmpty()){
											flag=0;
										ArrayList<String> str2;
										str.sort(null);
										str.add(" ");
										str2=(ArrayList<String>) str.stream().distinct().collect(Collectors.toList());
										int[] count=new int[str2.size()];
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
										for(int k=0;k<35-order[z].length();k++)
											len+=" ";
										fromServer.append(order[z] + len +count[z]+" \n");
										z++;
									}
									con = new initConnection(info);
									ArrayList<String> list = getBookScopeFromDB("SELECT * from bookscope WHERE ScopeName='"+scope.toString()+"'");									
									String len=" ";
									for (int q = 0; q < list.size(); q++) {
										if(!str.contains(list.get(q).toString())){
											len=" ";
											for(int k1=0;k1<35-list.get(q).length();k1++)
												len+=" ";
											fromServer.append(list.get(q).toString() + len +'0'+" \n");
										}
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
					if (flag==1)
						JOptionPane.showMessageDialog(null, "There is no orders in the selected book scope");

 				}
			});
			btnOk.setBounds(12, 367, 89, 23);
			frame.getContentPane().add(btnOk);

			JScrollPane scrollPane = new JScrollPane((Component) null);
			scrollPane.setBounds(467, 13, 295, 333);
			frame.getContentPane().add(scrollPane);

			fromServer = new JTextArea();
			fromServer.setFont(new Font("Courier New", Font.PLAIN, 12));
			fromServer.setTabSize(10);
			fromServer.setEditable(false);
			scrollPane.setViewportView(fromServer);

			JMenuBar menuBar = new JMenuBar();
			scrollPane.setColumnHeaderView(menuBar);

			JLabel lblUserid = new JLabel("BookName                                                            ");
			lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 11));
			menuBar.add(lblUserid);

			JLabel lblOrderdate = new JLabel("Popularity");
			lblOrderdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
			menuBar.add(lblOrderdate);

			JLabel lblDate = new JLabel("insert Date");
			lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDate.setBounds(12, 233, 97, 20);
			frame.getContentPane().add(lblDate);

			JLabel lblDdmmyy = new JLabel("YYYY-MM-DD");
			lblDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDdmmyy.setBounds(12, 330, 134, 16);
			frame.getContentPane().add(lblDdmmyy);

			JLabel lblFrom = new JLabel("from:");
			lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFrom.setBounds(12, 266, 56, 16);
			frame.getContentPane().add(lblFrom);

			JLabel lblTo = new JLabel("to:");
			lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTo.setBounds(175, 268, 56, 16);
			frame.getContentPane().add(lblTo);

			JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
			lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblYyyymmdd.setBounds(176, 330, 134, 16);
			frame.getContentPane().add(lblYyyymmdd);

			from = new JTextField();
			from.setFont(new Font("Tahoma", Font.PLAIN, 11));
			from.setBounds(12, 295, 134, 22);
			frame.getContentPane().add(from);
			from.setColumns(10);

			to = new JTextField();
			to.setFont(new Font("Tahoma", Font.PLAIN, 11));
			to.setBounds(176, 295, 134, 22);
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
			
			JLabel lblNumOfSearches = new JLabel("num Of Searches");
			lblNumOfSearches.setFont(new Font("Tahoma", Font.PLAIN, 11));
			scrollPane_1.setColumnHeaderView(lblNumOfSearches);
			
			JLabel lblBookScope = new JLabel("Book Scope:");
			lblBookScope.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblBookScope.setBounds(12, 141, 111, 19);
			frame.getContentPane().add(lblBookScope);
			
			textAreaScope = new JTextArea();
			textAreaScope.setTabSize(10);
			textAreaScope.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textAreaScope.setEditable(false);
			textAreaScope.setBounds(112, 141, 170, 33);
			frame.getContentPane().add(textAreaScope);
		}
		
		
		/**
		 * 
		 * @param s is a SQL query
		 * @return the scope book selected from DataBase
		 */
		public ArrayList<String> getScopeFromDB(String s) {

			ArrayList<String> book = new ArrayList<String>();
			try {
				statement = con.connect.prepareStatement(s);
				result = statement.executeQuery();
				while (result.next())
					book.add(result.getString(1));
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
		 * @param s is a SQL query
		 * @return the scope book selected from DataBase
		 */
		public ArrayList<String> getBookScopeFromDB(String s) {

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
		 * @param s is a SQL query
		 * @return the books from dataBase
		 */
		public ArrayList<String> getBookFromDB(String s) {

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
		public ArrayList<String> getDataFromDB(String s, String from, String to) {

			ArrayList<String> books = new ArrayList<String>();
			try {
				statement = con.connect.prepareStatement(s);
				result = statement.executeQuery();
				String date;
				int i, j;
				while (result.next()) {
					date = result.getString(7);
					i = date.compareTo(from);
					j = date.compareTo(to);
					if (i >= 0 && j <= 0)
						books.add(result.getString(5));
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
		public ArrayList<String> getSearchesFromDB(String s) {

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

