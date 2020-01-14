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
 * @author Hadi
 * GUI class that show report for book selected with number of searches statistic 
 * between date selected  
 */
public class bookSearchReportGUI extends JFrame {

	public JFrame frmBookSearchReport;
	private JTextArea fromServer;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	private dbInfo info;
	private JTextField from;
	private JTextField to;
	
	/**
	 * 
	 * @param the constructor get info parameter from dbInfo class that include the connection details
	 * and initialize the bookSearchReportGUI frame 
	 */
	@SuppressWarnings("unchecked")
	public bookSearchReportGUI(dbInfo info) {
		this.info=info;
		try {
			con = new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmBookSearchReport = new JFrame();
		frmBookSearchReport.setTitle("Book Search Report");
		frmBookSearchReport.setBounds(100, 100, 560, 428);
		frmBookSearchReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookSearchReport.getContentPane().setLayout(null);

		JLabel lblCostumerName = new JLabel("book Name:");
		lblCostumerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCostumerName.setBounds(12, 23, 134, 20);
		frmBookSearchReport.getContentPane().add(lblCostumerName);

		ArrayList<String> str = getBookFromDB("SELECT * FROM ibook.book");
		String[] user = new String[str.size()];
		for (int i = 0; i < str.size(); i++)
			user[i] = str.get(i);

		JComboBox comboBox = new JComboBox(user);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(12, 44, 244, 23);
		comboBox.setRenderer(new MyComboBoxRenderer("Select book"));
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("");
		frmBookSearchReport.getContentPane().add(comboBox);

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
								con = new initConnection(info);
								ArrayList<String> str = getSearchFromDB("SELECT * from ibook.search WHERE BookTitle= '"
										+ comboBox.getSelectedItem().toString() + "'", From, To);
									int size=str.size();
									fromServer.append(size + " \n");
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
					JOptionPane.showMessageDialog(null, "please select book OR insert correct Date");
				}

			}
		});
		btnOk.setBounds(12, 306, 89, 23);
		frmBookSearchReport.getContentPane().add(btnOk);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(322, 44, 147, 70);
		frmBookSearchReport.getContentPane().add(scrollPane);

		fromServer = new JTextArea();
		fromServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fromServer.setTabSize(10);
		fromServer.setEditable(false);
		scrollPane.setViewportView(fromServer);

		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);

		JLabel lblUserid = new JLabel("num Of Searches");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(lblUserid);

		JLabel lblDate = new JLabel("insert Date");
		lblDate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblDate.setBounds(12, 144, 97, 20);
		frmBookSearchReport.getContentPane().add(lblDate);

		JLabel lblDdmmyy = new JLabel("YYYY-MM-DD");
		lblDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDdmmyy.setBounds(22, 241, 137, 16);
		frmBookSearchReport.getContentPane().add(lblDdmmyy);

		JLabel lblFrom = new JLabel("from:");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFrom.setBounds(12, 177, 56, 16);
		frmBookSearchReport.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to:");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTo.setBounds(181, 179, 75, 16);
		frmBookSearchReport.getContentPane().add(lblTo);

		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYyyymmdd.setBounds(181, 243, 146, 16);
		frmBookSearchReport.getContentPane().add(lblYyyymmdd);

		from = new JTextField();
		from.setFont(new Font("Tahoma", Font.PLAIN, 11));
		from.setBounds(12, 206, 147, 22);
		frmBookSearchReport.getContentPane().add(from);
		from.setColumns(10);

		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 11));
		to.setBounds(181, 206, 156, 22);
		frmBookSearchReport.getContentPane().add(to);
		to.setColumns(10);

	}

	/**
	 * 
	 * @param s is a SQL query
	 * @return the books from dataBase
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
	 * @return dates founded in dataBase
	 */
	private ArrayList<String> getSearchFromDB(String s, String from, String to) {

		ArrayList<String> books = new ArrayList<String>();
		try {
			statement = con.connect.prepareStatement(s);
			result = statement.executeQuery();
			String date;
			int i, j;
			while (result.next()) {
				date = result.getString(2);
				i = date.compareTo(from);
				j = date.compareTo(to);
				if (i >= 0 && j <= 0)
					books.add(result.getString(1));
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
}
