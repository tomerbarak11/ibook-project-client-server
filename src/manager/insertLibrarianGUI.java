package manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.StatementImpl;

import common.dbInfo;
import common.initConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/**
 * class for inserting new librarian
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 *
 */
public class insertLibrarianGUI {

	public JFrame frmInsertNewLibrarian;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldpass;
	private JTextField textFieldEmail;
	private initConnection con;
	public dbInfo info;

	/**
	 * 
	 * @param info from server
	 */
	public insertLibrarianGUI(dbInfo info) {
		this.info=info;
		frmInsertNewLibrarian = new JFrame();
		frmInsertNewLibrarian.setTitle("Insert new Librarian");
		frmInsertNewLibrarian.setBounds(100, 100, 450, 300);
		frmInsertNewLibrarian.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInsertNewLibrarian.getContentPane().setLayout(null);
		
		JLabel lblPleaseFillNew = new JLabel("Please fill new librarian details:");
		lblPleaseFillNew.setForeground(Color.BLACK);
		lblPleaseFillNew.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblPleaseFillNew.setBounds(10, 26, 238, 26);
		frmInsertNewLibrarian.getContentPane().add(lblPleaseFillNew);
		
		JLabel labelID = new JLabel("User ID:");
		labelID.setForeground(Color.BLACK);
		labelID.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelID.setBounds(10, 66, 91, 26);
		frmInsertNewLibrarian.getContentPane().add(labelID);
		
		textFieldID = new JTextField();
		/**
		 * entering only numbers for ID
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
		textFieldID.setBounds(112, 72, 124, 20);
		frmInsertNewLibrarian.getContentPane().add(textFieldID);
		
		JLabel label_Name = new JLabel("Name:");
		label_Name.setForeground(Color.BLACK);
		label_Name.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		label_Name.setBounds(10, 103, 91, 26);
		frmInsertNewLibrarian.getContentPane().add(label_Name);
		
		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * entering only letters
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
		textFieldName.setBounds(112, 109, 124, 20);
		frmInsertNewLibrarian.getContentPane().add(textFieldName);
		
		JLabel label_pass = new JLabel("Password: ");
		label_pass.setForeground(Color.BLACK);
		label_pass.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		label_pass.setBounds(10, 140, 80, 26);
		frmInsertNewLibrarian.getContentPane().add(label_pass);
		
		textFieldpass = new JTextField();
		textFieldpass.setForeground(Color.BLACK);
		textFieldpass.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldpass.setBounds(112, 146, 124, 20);
		frmInsertNewLibrarian.getContentPane().add(textFieldpass);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblEmail.setBounds(10, 177, 80, 26);
		frmInsertNewLibrarian.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setForeground(Color.BLACK);
		textFieldEmail.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldEmail.setBounds(112, 183, 124, 20);
		frmInsertNewLibrarian.getContentPane().add(textFieldEmail);
		
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
				//making array for emails
				/**
				 * array for getting user email from data base
				 */
				ArrayList<String> emailDatabase = new ArrayList<String>();
				try {
					con = new initConnection(info);
					Statement myStmt=(Statement) con.connect.createStatement();
					ResultSet myRs= myStmt.executeQuery("SELECT * from workers");
					while(myRs.next())
						emailDatabase.add(myRs.getString(3));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				/**
				 * String array for user email from data base
				 */
				String[] email_arr = new String[emailDatabase.size()];
				for (int i = 0; i < emailDatabase.size(); i++) {
					email_arr[i] = emailDatabase.get(i);
				}
		
		JButton button = new JButton("OK");
		/**
		 * action listener for ok button, with validation of id,name,password,email and inserting into data base new librarian
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameText = textFieldName.getText();
				String userIDtext = textFieldID.getText();
				String password= textFieldpass.getText();
				String email= textFieldEmail.getText();
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
					for (int i = 0; i < email_arr.length; i++)
						if (email_arr[i].equals(email))
							throw new Exception("Email already exists in the system");
					if (textFieldName.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter name\nPlease enter name");
					else if (textFieldID.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter ID\nPlease enter ID");
					else if (textFieldpass.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "You did not enter password\nPlease enter password");
					else if(textFieldEmail.getText().equals(""))
						JOptionPane.showMessageDialog(null, "You did not enter email\nPlease enter email");
					else if(!(isValidEmailAddress(email)))
							JOptionPane.showMessageDialog(null, "Please insert valid email address");	
					else{
						con = new initConnection(info);
						Statement myStmt=(Statement) con.connect.createStatement();
						String sql= "INSERT INTO `users` (`userID`, `userName`, `password`, `accessLevel`, `status`, `ibookStatus`, `cardNumber`, `ibookconfirm`, `loginStatus`) VALUES ('"+userIDtext+"', '"+userNameText+"', '"+password+"', '2', '1', '0', NULL, NULL, '0');";
						myStmt.executeUpdate(sql);
						String sql2="INSERT INTO `workers` (`worker ID`, `Worker name`, `Email`, `Rank`) VALUES ('"+userIDtext+"', '"+userNameText+"', '"+email+"', 'librarian');";
						myStmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "New user: "+ userNameText+" - inserted inside the data base");
						frmInsertNewLibrarian.dispose();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
					
				}
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		button.setBounds(10, 229, 80, 20);
		frmInsertNewLibrarian.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		/**
		 * cancel button
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmInsertNewLibrarian.dispose();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		button_1.setBounds(121, 230, 80, 20);
		frmInsertNewLibrarian.getContentPane().add(button_1);
	}
	/**
	 * method validatin the email address and return boolean
	 * @param email the email the user enter sent to this function
	 * @return true or false
	 */
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
