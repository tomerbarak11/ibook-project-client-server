package reader;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.StatementImpl;

import common.LogInGui;
import common.dbInfo;
import common.initConnection;
import common.user;
import manager.*;
import Librarian.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.Statement;

/**
 * users menu
 * @author Tomer
 *
 */
public class readerGUI extends JFrame {

	public JFrame frame;
	private initConnection con;
	public int userID= user.userID;
	public dbInfo info;
/**
 * users menu constructor
 * @param info
 */
	
	public readerGUI(dbInfo info) {
		this.info=info;
		try {
			this.con=new initConnection(info);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Reader Menu");
		frame.setBounds(100, 100, 591, 372);
		frame.getContentPane().setLayout(null);

		JButton btnFindBook=new JButton();
		btnFindBook.setText("Find a book");
		btnFindBook.setBounds(48, 89, 150, 23);
		frame.getContentPane().add(btnFindBook);
		JLabel lblChooseOption = new JLabel("Choose an option");
		lblChooseOption.setBounds(96, 22, 150, 23);
		frame.getContentPane().add(lblChooseOption);

		btnFindBook.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				findBookGUI find=new findBookGUI(info);
			}
		}
	);
		
		

		/**
		 * user wants to sign up for iBook
		 */
		JButton btnIBook = new JButton("IBook details");
		btnIBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement myStmt=(Statement) con.connect.createStatement();
					ResultSet myRs= myStmt.executeQuery("SELECT ibookStatus,ibookconfirm FROM users WHERE userID='"+userID+"';");
					myRs.next();
					//ibookStatus: 0-no Ibook 1-regular Ibook 2-monthly Ibook 3- yearly Ibook 4-wait for confirmation
					//ibookconfirm: 0-done 1-regular Ibook 2-monthly Ibook 3- yearly Ibook 4-get confirm message 5- get unconfirm message
					if(myRs.getInt("ibookconfirm")==4){
						JOptionPane.showMessageDialog(null, "Your Ibook has been confirmed!");
						Statement myStmt2=(Statement) con.connect.createStatement();
						myStmt2.executeUpdate("UPDATE `users` SET `ibookconfirm` = '0' WHERE `users`.`userID` = '"+userID+"';");
					}
					if(myRs.getInt("ibookStatus")==0){
						if(myRs.getInt("ibookconfirm")==5)
							JOptionPane.showMessageDialog(null, "Your credit card was denied, please open Ibook again!");
						openIbookGUI openIbookGUI= new openIbookGUI(info);
						openIbookGUI.frame.setVisible(true);
					}
					else if(myRs.getInt("ibookStatus")==4)
						JOptionPane.showMessageDialog(null, "Please wait for confirmation");
					else{
						IbookDetailsGUI IbookDetailsGUI= new IbookDetailsGUI();
						IbookDetailsGUI.frame.setVisible(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					//JOptionPane.showMessageDialog(null, "Data base error");
				}
			}
		});
		btnIBook.setBounds(237, 89, 113, 23);
		frame.getContentPane().add(btnIBook);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setForeground(Color.RED);
		/**
		 * user wants to log out
		 */
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					con=new initConnection(info);
					Statement myStmt=(Statement) con.connect.createStatement();
					myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '0' WHERE `users`.`userID` = '"+userID+"';");
					LogInGui LogInGui= new LogInGui(info);
					frame.dispose();
					LogInGui.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error log out");
				}
				//
			}
		});
		btnLogOut.setBounds(399, 271, 140, 23);
		frame.getContentPane().add(btnLogOut);
		/**
		 * back button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res = 0;
				try {
					res=getLevelAccess();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(res==3){
				managerGUI managerGUI=new managerGUI(info);
				managerGUI.frame.setVisible(true);
				frame.dispose();
			}
			else if(res==2){
				librarianGUI librarianGUI=new librarianGUI(info);
				librarianGUI.frame.setVisible(true);
				frame.dispose();
				}
			else
				JOptionPane.showMessageDialog(null, "Access Deniend");

			}
		});
		btnBack.setBounds(29, 271, 97, 25);
		frame.getContentPane().add(btnBack);
	}
	
	/**
	 * get the access level of a user
	 * @return level
	 * @throws Exception
	 */
	 public int getLevelAccess() throws Exception{ 
			try {
				con=new initConnection(info);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    int bookScope = 0;
		    
		    try{

		    PreparedStatement statement = con.connect.prepareStatement("SELECT * FROM ibook.users WHERE loginStatus = 1");
			ResultSet result = statement.executeQuery();
			System.out.println();
			
			while(result.next()){
				bookScope = result.getInt(4);
			}
			
			result.close();
			statement.close();
	    	con.connect.close();
	    	
		    }catch (SQLException e) {
				e.printStackTrace();
			}
	    	return bookScope;
	 }
}
