package manager;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.StatementImpl;

import common.*;
import reader.*;
import Librarian.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.sql.Statement;

/**
 * 
 * @author Hadi
 *manager gui have all manager functions that user manager can use
 */
public class managerGUI extends JFrame{
	public static JFrame frame;
	public dbInfo info;
	public initConnection con;
	public int userID= user.userID;
	
	public managerGUI(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setBounds(100, 100, 631, 430);
		frame.setTitle("Manager Menu");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUserOrder = new JButton("User Order Report");
		btnUserOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUserOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			userOrderReportGUI userReport=new userOrderReportGUI(info);
			userReport.frame.setVisible(true);
			}
		});
		btnUserOrder.setBounds(10, 24, 295, 23);
		frame.getContentPane().add(btnUserOrder);
		
		JButton btnBookPopularity = new JButton("Book Popularity Report");
		btnBookPopularity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookPopularity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookPopularityReportGUI bookPopularityReportGUI=new bookPopularityReportGUI(info);
				bookPopularityReportGUI.frame.setVisible(true);
			}
		});
		btnBookPopularity.setBounds(317, 24, 289, 23);
		frame.getContentPane().add(btnBookPopularity);
		
		JButton btnUpdateUserAccess = new JButton("Update User Access Level");
		btnUpdateUserAccess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateUserAccess.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				accessLevelGUI accessLevel=new accessLevelGUI(info);
				//accessLevelGUI accessLevel=new accessLevelGUI();
				accessLevel.frame.setVisible(true);
				
				
			}
		});
		btnUpdateUserAccess.setBounds(317, 256, 289, 23);
		frame.getContentPane().add(btnUpdateUserAccess);
		
		JButton btnBookSearch = new JButton("Book Order Report");
		btnBookSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookOrderReportGUI bookOrderReportGUI=new bookOrderReportGUI(info);
				//bookOrderReportGUI bookDateReportGUI=new bookOrderReportGUI();
				bookOrderReportGUI.frame.setVisible(true);
			}
		});
		btnBookSearch.setBounds(317, 77, 289, 23);
		frame.getContentPane().add(btnBookSearch);
		
		JButton btnBookStatus = new JButton("Change Book Status");
		btnBookStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableEnableBookTemporaryGUI disableBook=new disableEnableBookTemporaryGUI(info);
				disableBook.frame.setVisible(true);
			}
		});
		btnBookStatus.setBounds(317, 195, 289, 23);
		frame.getContentPane().add(btnBookStatus);
		
		JButton btnLibreran = new JButton("Librarian Menu");
		btnLibreran.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLibreran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				librarianGUI window = new librarianGUI(info);
				window.frame.setVisible(true);
			}
		});
		btnLibreran.setBounds(10, 194, 295, 24);
		frame.getContentPane().add(btnLibreran);
		
		JButton btnReaderMenu = new JButton("Reader Menu");
		btnReaderMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReaderMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				readerGUI readerGUI = new readerGUI(info);
				readerGUI.frame.setVisible(true);
			}
		});
		btnReaderMenu.setBounds(10, 255, 295, 25);
		frame.getContentPane().add(btnReaderMenu);
		
		JButton button = new JButton("Book Popularity Report By Scope");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookPopularityReportByScopeGUI bookPopularityReportByScopeGUI = new bookPopularityReportByScopeGUI(info);
				bookPopularityReportByScopeGUI.frame.setVisible(true);
			}
		});
		button.setBounds(10, 76, 295, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("LOG OUT");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				frame.dispose();
				con=new initConnection(info);
				Statement myStmt=(Statement) con.connect.createStatement();
				myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '0' WHERE `users`.`userID` = '"+userID+"';");
				LogInGui Logingui = new LogInGui(info);
				Logingui.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Data base error");
				}
			}
		});
		button_1.setForeground(Color.RED);
		button_1.setBounds(187, 332, 247, 24);
		frame.getContentPane().add(button_1);
		
		JButton btnNewButton = new JButton("Manage Users");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageUsersGUI manageUsersGUI=new manageUsersGUI(info);
				manageUsersGUI.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 130, 295, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBookSearchReport = new JButton("Book Search Report");
		btnBookSearchReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookSearchReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookSearchReportGUI bookSearchReportGUI=new bookSearchReportGUI(info);
				bookSearchReportGUI.frmBookSearchReport.setVisible(true);
			}
		});
		btnBookSearchReport.setBounds(317, 131, 289, 23);
		frame.getContentPane().add(btnBookSearchReport);
	}
}

