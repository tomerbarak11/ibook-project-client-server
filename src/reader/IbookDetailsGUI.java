package reader;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.initConnection;
import common.user;
/**
 * when the user have ibook, it shows the details of the ibook membership
 * @author Nadav
 *
 */
public class IbookDetailsGUI extends JFrame {

	public JFrame frame;
	private JTextField txtName;
	private JTextField txtIbook;

	public IbookDetailsGUI() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblName.setForeground(Color.RED);
		lblName.setBounds(29, 32, 86, 52);
		frame.getContentPane().add(lblName);
		
		JLabel lblIbookType = new JLabel("Ibook Type:");
		lblIbookType.setForeground(Color.RED);
		lblIbookType.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblIbookType.setBounds(29, 105, 140, 52);
		frame.getContentPane().add(lblIbookType);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		txtName.setForeground(Color.BLACK);
		txtName.setBounds(125, 51, 209, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		txtName.setText(user.userName);
		
		txtIbook = new JTextField();
		txtIbook.setEditable(false);
		txtIbook.setHorizontalAlignment(SwingConstants.CENTER);
		txtIbook.setFont(new Font("Arial Black", Font.PLAIN, 16));
		txtIbook.setForeground(Color.BLACK);
		txtIbook.setBounds(179, 124, 160, 33);
		frame.getContentPane().add(txtIbook);
		txtIbook.setColumns(10);
		txtIbook.setText(readerFunc.returnIbookType());
	}
}
