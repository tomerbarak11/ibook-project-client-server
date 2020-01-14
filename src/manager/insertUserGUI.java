package manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import common.*;
public class insertUserGUI extends JFrame {

	public JFrame frmInsertNewUser;
	public dbInfo info;

	/**
	 * class GUI for inserting new user, the manager choose between inserting librarian or reader
	 * @param info get the info from sever
	 */
	public insertUserGUI(dbInfo info) {
		this.info=info;
		frmInsertNewUser = new JFrame();
		frmInsertNewUser.setTitle("Insert new user");
		frmInsertNewUser.setBounds(100, 100, 367, 191);
		frmInsertNewUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInsertNewUser.getContentPane().setLayout(null);
		/**
		 * button for inserting new librarian
		 */
		JButton btnNewButton = new JButton("Insert new librarian");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertLibrarianGUI insertLibrarianGUI= new insertLibrarianGUI(info);
				frmInsertNewUser.dispose();
				insertLibrarianGUI.frmInsertNewLibrarian.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 22, 151, 93);
		frmInsertNewUser.getContentPane().add(btnNewButton);
		/**
		 * button for inserting new reader
		 */
		JButton btnNewButton_1 = new JButton("Insert new reader");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertReaderGUI InsertReaderGUI= new InsertReaderGUI(info);
				frmInsertNewUser.dispose();
				InsertReaderGUI.frmInsertNewReader.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(181, 22, 145, 93);
		frmInsertNewUser.getContentPane().add(btnNewButton_1);
	}
}
