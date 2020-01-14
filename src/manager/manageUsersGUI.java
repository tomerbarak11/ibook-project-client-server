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
/**
 * manage customers accounts
 * @author Hadi
 *
 */
public class manageUsersGUI extends JFrame{
	public static JFrame frame;
	public dbInfo info;
	public initConnection con;
	public int userID= user.userID;
	public manageUsersGUI(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setBounds(100, 100, 289, 241);
		frame.setTitle("Manage Users");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUserOrder = new JButton("Insert New User");
		btnUserOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			insertUserGUI insertUserGUI=new insertUserGUI(info);
			insertUserGUI.frmInsertNewUser.setVisible(true);
			}
		});
		btnUserOrder.setBounds(10, 115, 225, 23);
		frame.getContentPane().add(btnUserOrder);
		
		JButton btnShowWorkersList = new JButton("Show Workers List");
		btnShowWorkersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showWorkersGUI showWorkersGUI = new showWorkersGUI(info);
				showWorkersGUI.frame.setVisible(true);
			}
		});
		btnShowWorkersList.setBounds(10, 25, 225, 23);
		frame.getContentPane().add(btnShowWorkersList);
	}
}

