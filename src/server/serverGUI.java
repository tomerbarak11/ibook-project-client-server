package server;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import common.*;

import javax.swing.border.MatteBorder;
/**
 * 
 * @author Hadi
 * SERVER GUI provide connection to the dataBase
 */
public class serverGUI extends JFrame {

	private JTextArea serverConsole;
	private JLabel lblDbName;
	private JTextField textFieldDBName;
	private JLabel lblIp;
	private JTextField textFieldPort;
	private JButton btnConnect;
	private JTextField textFieldUser;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField textFieldPass;
	public static JLabel lblNewLabel;
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public initConnection con;
	public static dbInfo info;
	private LogInGui LogInGui;
	// mainServer
	public static void main(String[] args) {
		serverGUI serverGUI = new serverGUI();
		serverGUI.frame.setVisible(true);
	}
/**
 * initialize serverGUI
 */
	public serverGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 397, 306);
		frame.setTitle("iBook Controller");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
		scrollPane.setBounds(123, 181, 231, 64);
		frame.getContentPane().add(scrollPane);

		serverConsole = new JTextArea();
		serverConsole.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(serverConsole);
		serverConsole.setEditable(false);

		lblDbName = new JLabel("DB Name:");
		lblDbName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblDbName.setBounds(12, 34, 120, 14);
		frame.getContentPane().add(lblDbName);

		textFieldDBName = new JTextField("ibook");
		textFieldDBName.setForeground(new Color(139, 0, 0));
		textFieldDBName.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDBName.setBounds(142, 31, 139, 20);
		frame.getContentPane().add(textFieldDBName);
		textFieldDBName.setColumns(10);

		lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblIp.setBounds(12, 68, 64, 14);
		frame.getContentPane().add(lblIp);

		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldPort.setForeground(new Color(165, 42, 42));
		textFieldPort.setBounds(142, 66, 139, 20);
		frame.getContentPane().add(textFieldPort);
		textFieldPort.setColumns(10);

		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnConnect.setBounds(12, 181, 99, 27);
		
		/**
		 * 		
		 * user press connect button 
		 * addActionListener checks if user select all fields
		 * and checks data with dataBase
		 * the connection shows in the same frame
		 */

		btnConnect.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String dbname, usrname, pass, ip;
				try {

					dbname = textFieldDBName.getText();
					 ip=textFieldPort.getText();
					usrname = textFieldUser.getText();
					pass = new String(textFieldPass.getPassword());

					if (!(dbname.isEmpty() || ip.isEmpty() || usrname.isEmpty() || pass.isEmpty())) {
						try {
							info=new dbInfo(dbname, usrname, pass, ip);
							con=new initConnection(info);
							display("SQL connection Successed");
							frame.dispose();
							 LogInGui=new LogInGui(info);
							 LogInGui.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							display("SQL connection Failed");
							e.toString();


						}
						
					} else
						display("You must Fill all the fields");

				} catch (Exception ex1) {
					display("SQL connection Faild");
					e.toString();

				}
			}

		});
		frame.getContentPane().add(btnConnect);

		textFieldUser = new JTextField("root");
		textFieldUser.setForeground(new Color(165, 42, 42));
		textFieldUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldUser.setBounds(142, 97, 139, 20);
		frame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblUsername.setBounds(12, 100, 101, 14);
		frame.getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(12, 133, 101, 14);
		frame.getContentPane().add(lblPassword);

		textFieldPass = new JPasswordField();
		textFieldPass.setForeground(new Color(165, 42, 42));
		textFieldPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldPass.setBounds(142, 130, 139, 20);
		frame.getContentPane().add(textFieldPass);
		textFieldPass.setColumns(10);

		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setBounds(0, 0, 379, 261);
		frame.getContentPane().add(lblNewLabel);
	}

	/**
	 * 
	 * @param s for the connection status result
	 * it show in the server frame (successes or failed to connect)
	 */
	public void display(String s) {
		serverConsole.append(s + "\n");
	}
}
