package manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.StatementImpl;

import DBcontroller.getBookTitleFromDB;
import common.dbInfo;
import common.initConnection;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/**
 * insert reader class
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 *
 */
public class InsertReaderGUI extends JFrame {

	public JFrame frmInsertNewReader;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldpass;
	private initConnection con;
	public dbInfo info;

	

	
	/**
	 * 
	 * @param info-  data from server
	 */
	public InsertReaderGUI(dbInfo info)  {
		this.info=info;
		frmInsertNewReader = new JFrame();
		frmInsertNewReader.setTitle("Insert new reader");
		frmInsertNewReader.setBounds(100, 100, 450, 300);
		frmInsertNewReader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInsertNewReader.getContentPane().setLayout(null);
		/**
		 * label for fill new reader
		 */
		JLabel lblPleaseFillNew = new JLabel("Please fill new reader details:");
		lblPleaseFillNew.setForeground(Color.BLACK);
		lblPleaseFillNew.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblPleaseFillNew.setBounds(26, 22, 238, 26);
		frmInsertNewReader.getContentPane().add(lblPleaseFillNew);
		
		JLabel lblid = new JLabel("User ID:");
		lblid.setForeground(Color.BLACK);
		lblid.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblid.setBounds(26, 59, 91, 26);
		frmInsertNewReader.getContentPane().add(lblid);
		
		textFieldID = new JTextField();
		/**
		 * KeyAdapter for checking only digits input
		 */
		textFieldID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE){
					arg0.consume();
				}
			}
		});
		textFieldID.setForeground(Color.BLACK);
		textFieldID.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldID.setBounds(128, 65, 124, 20);
		frmInsertNewReader.getContentPane().add(textFieldID);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblName.setBounds(26, 96, 91, 26);
		frmInsertNewReader.getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			/**
			 * key listener for entering only letters
			 */
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE){
					arg0.consume();
				}
			}
		});
		textFieldName.setForeground(Color.BLACK);
		textFieldName.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldName.setBounds(128, 102, 124, 20);
		frmInsertNewReader.getContentPane().add(textFieldName);
		/**
		 * label for password
		 */
		JLabel lblpass = new JLabel("Password: ");
		lblpass.setForeground(Color.BLACK);
		lblpass.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblpass.setBounds(26, 133, 80, 26);
		frmInsertNewReader.getContentPane().add(lblpass);
		/**
		 * textfield for password
		 */
		textFieldpass = new JTextField();
		textFieldpass.setForeground(Color.BLACK);
		textFieldpass.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldpass.setBounds(128, 139, 124, 20);
		frmInsertNewReader.getContentPane().add(textFieldpass);
		//making array for id of the users
		/**
		 * array for getting user ID from data base
		 */
		ArrayList<String> userIDDatabase = new ArrayList<String>();
		try {
			con = new initConnection(info);
			Statement myStmt=(Statement) con.connect.createStatement();
			ResultSet myRs= myStmt.executeQuery("SELECT * from users");
			while(myRs.next())
				userIDDatabase.add(myRs.getString(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * String array for user ID from data base
		 */
		String[] userID = new String[userIDDatabase.size()];
		for (int i = 0; i < userIDDatabase.size(); i++) {
			userID[i] = userIDDatabase.get(i);
		}
		//making id for the names of the users
		/**
		 * array for getting user name from data base
		 */
		ArrayList<String> userNameDatabase = new ArrayList<String>();
		try {
			con = new initConnection(info);
			Statement myStmt=(Statement) con.connect.createStatement();
			ResultSet myRs= myStmt.executeQuery("SELECT * from users");
			while(myRs.next())
				userNameDatabase.add(myRs.getString(2));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * String array for user name from data base
		 */
		String[] userName = new String[userNameDatabase.size()];
		for (int i = 0; i < userNameDatabase.size(); i++) {
			userName[i] = userNameDatabase.get(i);
		}
		//making array for passwords
		/**
		 * array for getting user password from data base
		 */
		ArrayList<String> passwordDatabase = new ArrayList<String>();
		try {
			con = new initConnection(info);
			Statement myStmt=(Statement) con.connect.createStatement();
			ResultSet myRs= myStmt.executeQuery("SELECT * from users");
			while(myRs.next())
				passwordDatabase.add(myRs.getString(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * String array for user password from data base
		 */
		String[] pass = new String[passwordDatabase.size()];
		for (int i = 0; i < passwordDatabase.size(); i++) {
			pass[i] = passwordDatabase.get(i);
			
		}
		
		JButton btnOk = new JButton("OK");
		/**
		 * action listener for ok button, with validation of id,name,password and inserting into data base new reader
		 */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameText = textFieldName.getText();
				String userIDtext = textFieldID.getText();
				String password= textFieldpass.getText();
				try {
					for (int i = 0; i < userID.length; i++) 
						if (userID[i].equals(userIDtext))
							throw new Exception("ID already exists in data base");
					for (int i = 0; i < userName.length; i++)
						if (userName[i].equals(userNameText))
							throw new Exception("User already exists in data base");
					for (int i = 0; i < pass.length; i++)
						if (pass[i].equals(password))
							throw new Exception("Please choose another password");
					if (textFieldName.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter name\nPlease enter name");
					else if (textFieldID.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter ID\nPlease enter ID");
					else if (textFieldpass.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter password\nPlease enter password");
					else{
						con = new initConnection(info);
						Statement myStmt=(Statement) con.connect.createStatement();
						String sql= "INSERT INTO `users` (`userID`, `userName`, `password`, `accessLevel`, `status`, `ibookStatus`, `cardNumber`, `ibookconfirm`, `loginStatus`) VALUES ('"+userIDtext+"', '"+userNameText+"', '"+password+"', '1', '1', '0', NULL, NULL, '0');";
						 myStmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "New user: "+ userNameText+" - inserted inside the data base");
						frmInsertNewReader.dispose();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
					
				}
			}
		});
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		btnOk.setBounds(26, 192, 80, 20);
		frmInsertNewReader.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		/**
		 * action listener for cancel button 
		 */
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmInsertNewReader.dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		btnCancel.setBounds(137, 193, 80, 20);
		frmInsertNewReader.getContentPane().add(btnCancel);
		
		
	}
}
