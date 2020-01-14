package reader;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import common.dbInfo;
import common.initConnection;
import common.user;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class orderGUI extends JFrame{

	private JFrame frame;
	private JTextArea fromServer;
	public  float price;
	private String name;
	private static int numOfBooks=0;
	private static int totalPrice=0;
	private initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	private String date;
	private String format;
	public dbInfo info;
	
/**
 * order contructor
 * @param info
 * @param name=name of book
 * @param price=book price
 * @param format=format chosen by user
 */
	public orderGUI(dbInfo info,String name,float price,String format) {
		this.name=name;
		this.price=price;
		this.numOfBooks=numOfBooks;
		this.info=info;
		numOfBooks++;
		totalPrice+=price;
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 386);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 109, 479, 189);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		fromServer = new JTextArea();
		scrollPane.setViewportView(fromServer);
		fromServer.setEditable(false);
		
		/**
		 * user does not add anymore books to order and completes purchase
		 */
		JButton btnConfirmPurchase = new JButton("Complete purchase");
		btnConfirmPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con=new initConnection(info);
				      Date dNow = new Date( );
				      SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
					int maxID=countRows("SELECT MAX(orderID) from bookorder");
					String date = ft.format(dNow);
					if(format.equals("pdf")==true)
					{
						try {
							con = new initConnection(info);
							ArrayList <String> URL=getURLFromDB("SELECT book.url_pdf from ibook.book WHERE book.title= '"+name+"'");
							String URLString=URL.get(0);
							displayFile file=new displayFile(URLString);
						}
							catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					else if(format.equals("doc")==true)
					{
						try {
							con = new initConnection(info);
							ArrayList <String> URL=getURLFromDB("SELECT book.url_doc from ibook.book WHERE book.title= '"+name+"'");
							String URLString=URL.get(0);
							displayFile file=new displayFile(URLString);
						}
							catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}							
					}
					addOrderToDB(maxID+1,name,user.userName,date,price,format);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame,  "Order completed",  "Message",  JOptionPane.PLAIN_MESSAGE);
			}
		}
				);
		btnConfirmPurchase.setBounds(71, 45, 220, 23);
		frame.getContentPane().add(btnConfirmPurchase);

		JLabel lblCurrentOrder = new JLabel("Current order");
		lblCurrentOrder.setBounds(71, 79, 112, 19);
		frame.getContentPane().add(lblCurrentOrder);
/**
 * if user wants to buy another book to his order
 */
		JButton btnAddAnotherBook = new JButton("Purchase another book");
		btnAddAnotherBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					con=new initConnection(info);
				      Date dNow = new Date( );
				      SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
					int maxID=countRows("SELECT MAX(orderID) from bookorder");
					String date = ft.format(dNow);
					if(format.equals("pdf")==true)
					{
						try {
							con = new initConnection(info);
							ArrayList <String> URL=getURLFromDB("SELECT book.url_pdf from ibook.book WHERE book.title= '"+name+"'");
							String URLString=URL.get(0);
							displayFile file=new displayFile(URLString);
						}
							catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					else if(format.equals("doc")==true)
					{
						try {
							con = new initConnection(info);
							ArrayList <String> URL=getURLFromDB("SELECT book.url_doc from ibook.book WHERE book.title= '"+name+"'");
							String URLString=URL.get(0);
							displayFile file=new displayFile(URLString);
						}
							catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}							
					}
					addOrderToDB(maxID+1,name,user.userName,date,price,format);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				frame.dispose();
				findBookGUI findMore = new findBookGUI(info);
			}
		});
		try {
		for (int i = 0; i < findBookGUI.numOfBooks; i++) {
				fromServer.append(i+1+". "+findBookGUI.booksOrder[i]+" \n");
		}


		} catch (Exception e) {
			// TODO: handle exception
		}

		btnAddAnotherBook.setBounds(71, 11, 220, 23);
		frame.getContentPane().add(btnAddAnotherBook);

		JLabel lblTotalPrice = new JLabel("Total price: "+totalPrice);
		lblTotalPrice.setBounds(326, 81, 104, 14);
		frame.getContentPane().add(lblTotalPrice);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				specificBookMenuGUI spec=new specificBookMenuGUI(info,name,price);
				spec.frmSpecificBookMenu.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(10, 313, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

/**
 * count number of orders to crate a new order with new maximum id
 * @param s=query
 * @return number of rows
 */

	private  int countRows(String s){
		int rows=0;
		try {
			statement=con.connect.prepareStatement(s);
			result = statement.executeQuery();
			if(result.next())
			{
				rows=result.getInt("MAX(orderID)");
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
 * add new order to database
 * @param id =new order id
 * @param name=name of book
 * @param user=buyer
 * @param date=date of purchase
 * @param price=books price
 * @param format=format chosen by user
 */
	public void addOrderToDB(int id,String name,String user,String date,float price,String format){ 
		try{
			con=new initConnection(info);
			String insertSql = "INSERT INTO ibook.bookorder VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement)con.connect.prepareStatement(insertSql);
			statement.setInt(1, id); 
			statement.setString(2, name);
			statement.setString(3, user); 
			statement.setString(4, date); 
			statement.setFloat(5, price); 
			statement.setString(6, format); 

			statement.executeUpdate();
			statement.close();
			con.connect.close();

		}catch(SQLException e){
			e.printStackTrace();
		}			 
	}	
	/**
	 * after user buys each book he gets a link to download the book in the right format
	 * @param s query
	 * @return link for the download
	 */
	private  ArrayList <String> getURLFromDB(String s){

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
