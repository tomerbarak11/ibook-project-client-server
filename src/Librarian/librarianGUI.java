package Librarian;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.StatementImpl;

import DBcontroller.getLevelAccess;
import common.LogInGui;
import common.dbInfo;
import common.initConnection;
import common.user;
import manager.managerGUI;
import reader.readerGUI;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class shows the options that librarian can make at the system
 *
 */
public class librarianGUI extends JFrame{

	public readerGUI readergui;
	private reviewsConfirmation reviewCon;
	public JFrame frame;
	private initConnection connection;
	public int userID= user.userID;
	private dbInfo info;
    private addBook addbook;
    private addNewScope addnewscope;
    private addNewSubject addnewsubject;
    private editInfo editinfo;
    private IbookConfirmGUI ibookconfirmgui;
    private int levelAccess = 3;
    private managerGUI managergui;
	/**
	 * 
	 * @param info - client connection
	 * The method librarianGUI creates the GUI window of the options that librarian can make
	 */
	public librarianGUI(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setTitle("Librarian Menu");
		frame.setBounds(100, 100, 395, 523);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton buttonAddBook = new JButton("Add Book");
		buttonAddBook.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI where librarian can add book 
		 */
		buttonAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addbook = new addBook(info);
				addbook.frmAddNewBook.setVisible(true);
			}
		});
		
		buttonAddBook.setBounds(15, 61, 343, 29);
		frame.getContentPane().add(buttonAddBook);
		
		JButton buttonAddScope = new JButton("Add Scope");
		buttonAddScope.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI where librarian can add scope 
		 */
		buttonAddScope.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addnewscope = new addNewScope(info);
				addnewscope.frmAddNewScope.setVisible(true);
			}
		});
		buttonAddScope.setBounds(15, 148, 343, 29);
		frame.getContentPane().add(buttonAddScope);
		
		JButton buttonAddSubject = new JButton("Add Subject");
		buttonAddSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI where librarian can add subject
		 */
		buttonAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addnewsubject = new addNewSubject(info);
				addnewsubject.frmAddNewSubject.setVisible(true);
			}
		});
		buttonAddSubject.setBounds(15, 193, 343, 29);
		frame.getContentPane().add(buttonAddSubject);
		
		JLabel lblLibrarianMenu = new JLabel("Librarian Menu");
		lblLibrarianMenu.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		lblLibrarianMenu.setForeground(Color.DARK_GRAY);
		Font font = lblLibrarianMenu.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblLibrarianMenu.setFont(font.deriveFont(attributes));
		lblLibrarianMenu.setBounds(122, 16, 156, 29);
		frame.getContentPane().add(lblLibrarianMenu);
		
		JButton buttonEditInformation = new JButton("Edit Book Info");
		buttonEditInformation.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI where librarian can edit information
		 */
		buttonEditInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editinfo = new editInfo(info);
				editinfo.frmEditInformation.setVisible(true);
			}
		});
		buttonEditInformation.setBounds(15, 238, 343, 29);
		frame.getContentPane().add(buttonEditInformation);
		
		JButton btnExit = new JButton("LOG OUT");
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnExit.setForeground(Color.RED);
		/**
		 * Exit the menu
		 */
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				connection = new initConnection(info);
				Statement myStmt=(Statement) connection.connect.createStatement();
				myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '0' WHERE `users`.`userID` = '"+userID+"';");
				LogInGui Logingui = new LogInGui(info);
				frame.dispose();
				Logingui.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Data base error");
				}
			}
		});
		btnExit.setBounds(15, 416, 343, 35);
		frame.getContentPane().add(btnExit);
		
		JButton btnIbookConfirmation = new JButton("iBook confirmation");
		btnIbookConfirmation.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI where librarian can confirm iBook
		 */
		btnIbookConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ibookconfirmgui = new IbookConfirmGUI(info);
				ibookconfirmgui.frmIbookConfirmation.setVisible(true);
			}
		});
		btnIbookConfirmation.setBounds(15, 103, 343, 29);
		frame.getContentPane().add(btnIbookConfirmation);
		
		JButton buttonManagerMenu = new JButton("Manager menu");
		buttonManagerMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * Open a new window GUI of manager menu
		 */
		buttonManagerMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					levelAccess = getLevelAccess.getLevelAccess(info);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(levelAccess == 3){
					managergui = new managerGUI(info);
					managergui.frame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Do not have manager access,can not return");
			}
		});
		buttonManagerMenu.setBounds(15, 371, 343, 29);
		frame.getContentPane().add(buttonManagerMenu);
		
		JButton btnConfirmReview = new JButton("Confirm review");
		/**
		 * Open a new window GUI where librarian can confirm reviews
		 */
		btnConfirmReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reviewCon = new reviewsConfirmation(info);
				reviewCon.frmConfirmReview.setVisible(true);
			}
		});
		btnConfirmReview.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnConfirmReview.setBounds(15, 281, 343, 29);
		frame.getContentPane().add(btnConfirmReview);
		
		JButton buttonReaderMenu = new JButton("Reader Menu");
		/**
		 * Open a new window GUI of reader menu
		 */
		buttonReaderMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				readergui = new readerGUI(info);
				readergui.frame.setVisible(true);
			}
		});
		buttonReaderMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		buttonReaderMenu.setBounds(15, 326, 343, 29);
		frame.getContentPane().add(buttonReaderMenu);
		
	}
}
