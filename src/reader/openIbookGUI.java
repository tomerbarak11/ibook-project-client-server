package reader;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.StatementImpl;

import common.dbInfo;
import common.initConnection;
import common.user;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;

/**
 * this class open new Ibook
 * @author Nadav
 *
 */
public class openIbookGUI extends JFrame {

	public JFrame frame;
	private JTextField textFieldCard;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public initConnection con;
	public dbInfo info;

	public openIbookGUI(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 754, 582);
		/**
		 * for openning regular ibook
		 */
		JRadioButton rdbtnRegular = new JRadioButton("Regular");
		buttonGroup.add(rdbtnRegular);
		rdbtnRegular.setSelected(true);
		rdbtnRegular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnRegular.setForeground(Color.BLACK);
		rdbtnRegular.setBounds(32, 71, 109, 23);
		frame.getContentPane().add(rdbtnRegular);
		/**
		 * label for openning ibook membership
		 */
		JLabel lblNewLabel = new JLabel("Options for openning Ibook membership");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(49, 11, 337, 53);
		frame.getContentPane().add(lblNewLabel);
		/**
		 * for openning Monthly ibook
		 */
		JRadioButton rdbtnMonthlyMember = new JRadioButton("Monthly member");
		buttonGroup.add(rdbtnMonthlyMember);
		rdbtnMonthlyMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMonthlyMember.setBounds(32, 101, 135, 23);
		frame.getContentPane().add(rdbtnMonthlyMember);
		/**
		 * for openning Yearly ibook
		 */
		JRadioButton rdbtnYearlyMember = new JRadioButton("Yearly member");
		buttonGroup.add(rdbtnYearlyMember);
		rdbtnYearlyMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnYearlyMember.setBounds(32, 131, 135, 23);
		frame.getContentPane().add(rdbtnYearlyMember);
		
		JLabel lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setForeground(Color.BLACK);
		lblCardNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblCardNumber.setBounds(32, 166, 109, 26);
		frame.getContentPane().add(lblCardNumber);
		
		textFieldCard = new JTextField();
		/**
		 * for input of only numbers and 16 digits
		 */
		textFieldCard.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE){
					arg0.consume();
				}
			}
		});
		textFieldCard.setForeground(Color.BLACK);
		textFieldCard.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		textFieldCard.setBounds(158, 172, 124, 20);
		frame.getContentPane().add(textFieldCard);
		
		JLabel lblDigits = new JLabel("*16 digits");
		lblDigits.setBounds(292, 175, 66, 14);
		frame.getContentPane().add(lblDigits);
		
		JButton btnOk = new JButton("OK");
		/**
		 * when the user click ok there is validation and openning new ibook and sending confirm message
		 */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textFieldCard.getText().length()!=16)
						throw new Exception();
					con=new initConnection(info);
					Statement myStmt=(Statement) con.connect.createStatement();
					
					if(rdbtnRegular.isSelected()){
						int userID= user.userID;
						myStmt.executeUpdate("UPDATE `users` SET `ibookStatus` = '4',`cardNumber`='"+textFieldCard.getText()+"', `ibookconfirm` = '1' WHERE `users`.`userID` = '"+userID+"';");
								
					}
					if(rdbtnMonthlyMember.isSelected()){
						int userID= user.userID;
						myStmt.executeUpdate("UPDATE `users` SET `ibookStatus` = '4',`cardNumber`='"+textFieldCard.getText()+"', `ibookconfirm` = '2' WHERE `users`.`userID` ='"+userID+"';");
					}
					if(rdbtnYearlyMember.isSelected()){
						int userID= user.userID;
						myStmt.executeUpdate("UPDATE `users` SET `ibookStatus` = '4',`cardNumber`='"+textFieldCard.getText()+"', `ibookconfirm` = '3' WHERE `users`.`userID` = '"+userID+"';");
					}
					IbookMessage IbookMessage= new IbookMessage(info);
					frame.dispose();
					IbookMessage.frame.setVisible(true);
					
				}
				 catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Must input 16 digits in card number");
				}
			 
			}
		});
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		btnOk.setBounds(42, 213, 80, 20);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readerGUI readerGUI= new readerGUI(info);
				frame.dispose();
				readerGUI.frame.setVisible(true);				
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		btnCancel.setBounds(154, 214, 80, 20);
		frame.getContentPane().add(btnCancel);
	}
}
