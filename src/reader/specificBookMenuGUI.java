package reader;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import common.*;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * menu of options of a specific book
 * @author  Tomer
 *
 */
public class specificBookMenuGUI {
	private PreparedStatement statement;

	public JFrame frmSpecificBookMenu;
	public  String name;
	public  float price;
	private initConnection con;
	private ResultSet result;
	private ArrayList <String> str;
	public dbInfo info;

/**
 * constructor
 * @param info
 * @param name=book name
 * @param price=book price
 */
	public specificBookMenuGUI(dbInfo info,String name,float price) {
		this.info=info;
		this.name=name;
		this.price=price;

		frmSpecificBookMenu = new JFrame();
		frmSpecificBookMenu.setTitle("Specific Book Menu");
		frmSpecificBookMenu.setBounds(100, 100, 450, 300);
		frmSpecificBookMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSpecificBookMenu.getContentPane().setLayout(null);
		JLabel label = new JLabel("Book chosen:" +name);
		label.setBounds(25, 21, 344, 34);
		frmSpecificBookMenu.getContentPane().add(label);

		JButton btnReadReviews = new JButton("Read reviews");
		btnReadReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSpecificBookMenu.dispose();
				readReviewGUI readReviews=new readReviewGUI(info,name,price);

			}
		});
		btnReadReviews.setBounds(45, 66, 141, 23);
		frmSpecificBookMenu.getContentPane().add(btnReadReviews);

		JButton btnAddAReview = new JButton("Add a review");
		btnAddAReview.setBounds(45, 123, 144, 23);
		frmSpecificBookMenu.getContentPane().add(btnAddAReview);


/**
 * user wants to add a review
 */
		btnAddAReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					con = new initConnection(info);
					//	insertIntoDB("UPDATE ibook.book SET numberOfSearches=numberOfSearches+1 WHERE title='"+titleField.getText().toString()+"'" );
					str=getUsersReadBookFromDB("SELECT * FROM ibook.bookorder WHERE bookorder.bookTitle='"+name+"'");

					//str=getUsersReadBookFromDB("SELECT * FROM ibook.bookorder WHERE bookorder.bookTitle='"+name+"AND bookorder.userName="+"Tomer Barak"+"'");

					if(str.size()!=0)
					{
						frmSpecificBookMenu.dispose();
						addReviewGUI addReview=new addReviewGUI(info,name,price);
					}
					else
						JOptionPane.showMessageDialog(frmSpecificBookMenu,  "Only user who read the book can add a review",  "Message",  JOptionPane.PLAIN_MESSAGE);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});


		JButton btnPurchase = new JButton("Add to cart");


		String[] formats={"doc","pdf"};
		JComboBox formatCombo = new JComboBox(formats);
		formatCombo.setBounds(45, 187, 141, 20);
		formatCombo.setRenderer(new MyComboBoxRenderer("Select format"));
		formatCombo.setSelectedIndex(-1);
		frmSpecificBookMenu.getContentPane().add(formatCombo);
		frmSpecificBookMenu.setVisible(true);
/**
 * user wants to purchase a book
 */
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ibookStatus=-1;
				try {
					con = new initConnection(info);
					ArrayList <String> ibook=getIBOOKFromDB("SELECT users.ibookstatus from ibook.users WHERE users.userName= '"+user.userName+"'");
					String str=ibook.get(0);
					ibookStatus = Integer.parseInt(str);
					System.out.println(ibookStatus);
				} catch (Exception e) {
					e.printStackTrace();

					// TODO: handle exception
				}
			//	if(!(ibookStatus==-1.0||ibookStatus==0.0||ibookStatus==4.0||ibookStatus==5.0))
				if(ibookStatus>=1&&ibookStatus<=3)
				{
					if(formatCombo.getSelectedItem()!=null)
					{
						findBookGUI.numOfBooks++;
						frmSpecificBookMenu.dispose();
						orderGUI order = new orderGUI(info, name, price, formatCombo.getSelectedItem().toString());
					}
					else
						JOptionPane.showMessageDialog(frmSpecificBookMenu,  "You must choose a format",  "Message",  JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(frmSpecificBookMenu,  "You must open ibook first",  "Message",  JOptionPane.PLAIN_MESSAGE);
				}
			}});
		btnPurchase.setBounds(225, 186, 144, 23);
		frmSpecificBookMenu.getContentPane().add(btnPurchase);
		JButton btnBack = new JButton("Back");
		/**
		 * back button
		 */
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findBookGUI find=new findBookGUI(info);
				find.frmfindBook.setVisible(true);
				frmSpecificBookMenu.dispose();

			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		frmSpecificBookMenu.getContentPane().add(btnBack);
/**
 * user wans to read summary
 */
		JButton btnReadSummary = new JButton("Read summary");
		btnReadSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readSummaryGUI sum=new readSummaryGUI(name);
				sum.frame.setVisible(true);
				frmSpecificBookMenu.dispose();
			}
		});
		btnReadSummary.setBounds(225, 66, 144, 23);
		frmSpecificBookMenu.getContentPane().add(btnReadSummary);

		}
	/**
	 * checks only if user has read a book so he can add a review to this book 
	 * @param s=query
	 * @return
	 */
		private  ArrayList <String> getUsersReadBookFromDB(String s){

			ArrayList <String> users= new ArrayList <String> ();
			try {
				statement=con.connect.prepareStatement(s);
				result = statement.executeQuery();
				while(result.next())
				{
					users.add(result.getString(3));
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
		/**
		 * check if users has iBook so he will get a discount
		 * @param s=query
		 * @return
		 */
		private  ArrayList <String> getIBOOKFromDB(String s){

			ArrayList <String> data= new ArrayList <String>();
			try {
				statement=con.connect.prepareStatement(s);
				result = statement.executeQuery();
				while(result.next())
					data.add(result.getString(1));
				result.close();
				statement.close();
				con.connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return data;
		}
	}
