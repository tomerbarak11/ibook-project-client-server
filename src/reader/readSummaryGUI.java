package reader;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.*;
import javax.swing.JMenuBar;
import java.awt.Font;
/**
 * read books summary
 * @author Tomer
 *
 */
public class readSummaryGUI extends JFrame {

	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	public JFrame frame;
	private JTextArea fromServer;
	public static String name;
	public dbInfo info;
	public int price;


/**
 * consructor
 * @param name=book name
 */
	public readSummaryGUI(String name) {

		this.price=price;
		this.name=name;
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Read Review");
		frame.setBounds(100, 100, 605, 229);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblBookName = new JLabel("Book Name:"+name);
		lblBookName.setBounds(12, 23, 246, 20);
		frame.getContentPane().add(lblBookName);			

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 54, 569, 91);
		frame.getContentPane().add(scrollPane);

		fromServer = new JTextArea();
		fromServer.setEditable(false);
		scrollPane.setViewportView(fromServer);


		try {
			con = new initConnection(info);
			ArrayList <String> str=getSummaryFromDB("SELECT plot from ibook.book WHERE Title= '"+name+"'");
		
			String[] order = new String[str.size()];
			for (int i = 0; i < str.size(); i++){
				order[i] = str.get(i);
				if(i==0)
					fromServer.append("\n");
				fromServer.append(order[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception

		}	

		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);

		JLabel lblNewLabel = new JLabel("Summary");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		menuBar.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 156, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specificBookMenuGUI spec=new specificBookMenuGUI(info,name,price);
				spec.frmSpecificBookMenu.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnBack);

	}
/**
 * display book summary in window
 * @param s
 */
	public void displayFromServer(String s)
	{
		fromServer.append(s + " \n");
	}
/**
 * get book details from database
 * @param s=query
 * @return details of book
 */

	private  ArrayList <String> getBookFromDB(String s){

		ArrayList <String> data= new ArrayList <String>();
		try {
			statement=con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while(result.next())
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
	 * get summary of book from database
	 * @param s=query
	 * @return summary
	 */
	private  ArrayList <String> getSummaryFromDB(String s){

		ArrayList <String> users= new ArrayList <String> ();
		try {
			statement=con.connect.prepareStatement(s);
			result = statement.executeQuery();
			while(result.next())
				users.add(result.getString(1));
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
