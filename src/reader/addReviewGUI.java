package reader;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.*;

import javax.swing.JTextField;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * add review to database
 * @author  Tomer
 *
 */

public class addReviewGUI extends JFrame{
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	private JFrame frame;
	private JTextField reviewField;
	private String sign;
	private String review;
	private static String name;
	private JTextField textSign;
	public dbInfo info;
	public float price;
/**
 * user adds a review
 * @param info
 * @param name
 * @param price
 */
	public addReviewGUI(dbInfo info,String name,float price) {
		this.name=name;
		this.info=info;
		this.price=price;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);		
		frame.setTitle("Add Review");

		JLabel lblBookName = new JLabel("Book Name:"+name);
		lblBookName.setBounds(12, 23, 195, 20);
		frame.getContentPane().add(lblBookName);
		try {
			this.con=new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList <String> str=getBookFromDB("SELECT * FROM book");
		String[] book = new String[str.size()];
		for (int i = 0; i < str.size(); i++) 
			book[i] = str.get(i);



		reviewField = new JTextField();
		reviewField.setBounds(12, 54, 395, 39);
		frame.getContentPane().add(reviewField);
		reviewField.setColumns(10);

		textSign = new JTextField();
		textSign.setBounds(12, 134, 86, 20);
		frame.getContentPane().add(textSign);
		textSign.setColumns(10);

		JLabel lblAddYourSignature = new JLabel("Add your signature");
		lblAddYourSignature.setBounds(12, 109, 125, 14);
		frame.getContentPane().add(lblAddYourSignature);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 227, 89, 23);
		frame.getContentPane().add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				specificBookMenuGUI spec=new specificBookMenuGUI(info, name,price);
				spec.frmSpecificBookMenu.setVisible(true);
				frame.dispose();
			}
		});
		JButton btnSendReview=new JButton("Send the review");;
		btnSendReview = new JButton("Send review");
		btnSendReview.setBounds(12, 165, 125, 23);
		frame.getContentPane().add(btnSendReview);
		/**
		 * launching the review to librarians confirmation
		 */
		btnSendReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				review=reviewField.getText();
				sign=textSign.getText().toString();

				if(reviewField.getText().toString().isEmpty()==false&&textSign.getText().toString().isEmpty()==false)
				{
				try {
					con=new initConnection(info);
					int maxID=countRows("SELECT MAX(reviewID) from review");
					addReviewToDB(maxID+1,user.userName,name,review,sign,0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				JOptionPane.showMessageDialog(frame,  "Your review was send to confirmation.",  "Message",  JOptionPane.PLAIN_MESSAGE);

				}
				else
					JOptionPane.showMessageDialog(frame,  "Fill all fields",  "Message",  JOptionPane.PLAIN_MESSAGE);

			}
		});
	}
	/**
	 * counting number of rows in chart to give new id with max number
	 * @param s
	 * @return
	 */
	private  int countRows(String s){
		int rows=0;
		try {
			statement=con.connect.prepareStatement(s);
			result = statement.executeQuery();
			if(result.next())
			{
				rows=result.getInt("MAX(reviewID)");
			}
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rows;
	}
	/**
	 * get book details from database
	 * @param s
	 * @return
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
	 * launching the review to librarians confirmation
	 * @param id of user
	 * @param username
	 * @param title=book name
	 * @param review
	 * @param sign
	 * @param status=0 before confirmation and 1 after
	 */

	public void addReviewToDB(int id,String username,String title,String review,String sign,int status){ 
		try{
			con=new initConnection(info);
			String insertSql = "INSERT INTO ibook.review VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
			statement.setInt(1, id); 
			statement.setString(2, username); 
			statement.setString(3, title); 
			statement.setString(4, review); 
			statement.setString(5, sign); 
			statement.setInt(6, 0); 

			statement.executeUpdate();
			statement.close();
			con.connect.close();

		}catch(SQLException e){
			e.printStackTrace();
		}			 
	}	

}
