package reader;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.dbInfo;

import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * sending message for confirmation after openning new ibook membership
 * @author Nadav
 *
 */
public class IbookMessage extends JFrame {

	public JFrame frame;
	public dbInfo info;

	public IbookMessage(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readerGUI readerGUI= new readerGUI(info);
				frame.dispose();
				readerGUI.frame.setVisible(true);
			}
		});
		btnOk.setBounds(186, 202, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JTextPane txtpnThankYouFor = new JTextPane();
		txtpnThankYouFor.setBackground(Color.BLACK);
		txtpnThankYouFor.setForeground(Color.RED);
		txtpnThankYouFor.setFont(new Font("Arial", Font.BOLD, 26));
		txtpnThankYouFor.setText("thank you for filling your details, please wait for confirmation!");
		txtpnThankYouFor.setBounds(62, 29, 309, 107);
		frame.getContentPane().add(txtpnThankYouFor);
	}
}
