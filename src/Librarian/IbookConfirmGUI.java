package Librarian;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mysql.cj.jdbc.StatementImpl;

import common.dbInfo;
import common.initConnection;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class shows the Ibook - the part that shows the orders that can be confirmed or unconfirmed by the librarian
 *
 */
public class IbookConfirmGUI extends JFrame{

	public JFrame frmIbookConfirmation;
	public initConnection con;
	private PreparedStatement statement;
	private ResultSet result;
	private JScrollPane scrollPane;
	private JTextArea fromServer;
    private dbInfo info;
    private librarianGUI librariangui;
	/**
	 * @param info - client connection
	 * The method IbookConfirmGUI creates the GUI window of confirmed or unconfirmed the orders by the librarian
	 */
	public IbookConfirmGUI(dbInfo info) {
		
		this.info = info;
		frmIbookConfirmation = new JFrame();
		frmIbookConfirmation.setTitle("Ibook Confirmation");
		frmIbookConfirmation.setBounds(100, 100, 592, 320);
		frmIbookConfirmation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmIbookConfirmation.getContentPane().setLayout(null);
	
		JLabel lblPleaseChooseUser = new JLabel("Please choose user:");
		lblPleaseChooseUser.setForeground(Color.BLACK);
		lblPleaseChooseUser.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPleaseChooseUser.setBounds(10, 15, 166, 26);
		frmIbookConfirmation.getContentPane().add(lblPleaseChooseUser);
		
		JComboBox comboBoxTitleBook = new JComboBox();
		comboBoxTitleBook.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxTitleBook.setBounds(10, 52, 166, 26);
		frmIbookConfirmation.getContentPane().add(comboBoxTitleBook);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(186, 11, 393, 241);
		frmIbookConfirmation.getContentPane().add(scrollPane);

		fromServer = new JTextArea();
		fromServer.setEnabled(false);
		fromServer.setEditable(false);
		fromServer.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		scrollPane.setViewportView(fromServer);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		scrollPane.setColumnHeaderView(menuBar);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Times New Roman", Font.BOLD, 13));
		menuBar.add(lblUsers);
		
		
		
		try {
			con = new initConnection(info);
			ArrayList <String> str=getDataFromDB
					 ("SELECT * from users WHERE ibookStatus=4");
			 String[] order = new String[str.size()];
			for (int i = 0; i < str.size(); i++){
					order[i] = str.get(i);
					fromServer.append(i+1+". "+ order[i] + " \n");
				}
			con = new initConnection(info);
			ArrayList <String> str2=getUserNameFromDB
					 ("SELECT userName from users WHERE ibookStatus=4");
			String[] order2 = new String[str.size()];
			for (int i = 0; i < str2.size(); i++){
					order2[i] = str2.get(i);
					comboBoxTitleBook.addItem(order2[i]);
			}
			comboBoxTitleBook.setSelectedIndex(-1);
			
			JButton btnConfirm = new JButton("Confirm");
			btnConfirm.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
			/**
			 * librarian press the button confirm
			 */
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					try {
						if( comboBoxTitleBook.getSelectedItem()==null )
							throw new Exception();
						con = new initConnection(info);
					    String name=  comboBoxTitleBook.getSelectedItem().toString();
						Statement myStmt=(Statement) con.connect.createStatement();
						ResultSet myRs= myStmt.executeQuery("SELECT * FROM users WHERE userName='"+name+"'");
						myRs.next();
						myStmt.executeUpdate("UPDATE `users` SET `ibookStatus` = '"+myRs.getInt("ibookconfirm")+"',`ibookconfirm`='4' WHERE `users`.`userName` ='"+name+"';");
						JOptionPane.showMessageDialog(null, "Confirmation message sent to "+name);
						frmIbookConfirmation.dispose();
						IbookConfirmGUI ibookConfirmGUI= new IbookConfirmGUI(info);
						ibookConfirmGUI.frmIbookConfirmation.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error, user is not selected");
					}
					
				}
			});
			btnConfirm.setBounds(10, 86, 166, 23);
			frmIbookConfirmation.getContentPane().add(btnConfirm);
				
			JButton btnUnconfirm = new JButton("Unconfirm");
			btnUnconfirm.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
			/**
			 * librarian press the buttom unconfirm
			 */
			btnUnconfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if( comboBoxTitleBook.getSelectedItem()==null )
							throw new Exception();
						con = new initConnection(info);
						String name=  comboBoxTitleBook.getSelectedItem().toString();
						Statement myStmt=(Statement) con.connect.createStatement();
						myStmt.executeUpdate("UPDATE `users` SET `ibookStatus` = '0', `ibookconfirm` = '5' WHERE userName='"+name+"'");
						JOptionPane.showMessageDialog(null, "Unconfirmation message sent to "+name);
						frmIbookConfirmation.dispose();
						IbookConfirmGUI ibookConfirmGUI= new IbookConfirmGUI(info);
						ibookConfirmGUI.frmIbookConfirmation.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error, user is not selected");
					}
				    
				}
			});
			btnUnconfirm.setBounds(10, 120, 166, 23);
			frmIbookConfirmation.getContentPane().add(btnUnconfirm);
			
			JButton btnExit = new JButton("Exit");
			/**
			 * librarian press the button Exit
			 */
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frmIbookConfirmation.dispose();
				}
			});
			btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
			btnExit.setBounds(10, 229, 166, 23);
			frmIbookConfirmation.getContentPane().add(btnExit);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 if (fromServer.getText().equals("")){
			 fromServer.append("No new Ibook accounts");
		     }
		
	}
	private  ArrayList <String> getDataFromDB(String s){

	    ArrayList <String> users= new ArrayList <String> ();
	    try {
			statement=con.connect.prepareStatement(s);
			result = statement.executeQuery();
		while(result.next())
				users.add("Name: "+result.getString(2)+" Card number: "+result.getString(7)+" Ibook type: "+IbookType(result.getInt(8)));
			result.close();
			statement.close();
			con.connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return users;
	}
	private  ArrayList <String> getUserNameFromDB(String s){
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
	
	private String IbookType(int type){
		if(type==1)
			return "Regular";
		if(type==2)
			return "Monthly Ibook";
		if(type==3)
			return "Yearly Ibook";
		else
			return "Error";
	}
	
	public static void main(String[] args){
	}
}
