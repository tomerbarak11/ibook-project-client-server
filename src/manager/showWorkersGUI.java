package manager;

import common.dbInfo;
import common.initConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * class for showing the workers detail in a table
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 *
 */
public class showWorkersGUI extends JFrame{

	public JFrame frame;
	private JTable table;
	private initConnection con;
	public dbInfo info;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	/**
	 * Initialize the contents of the frame.
	 */
	public showWorkersGUI(dbInfo info) {
		this.info=info;
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 432);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLoadTheWorkers = new JButton("Load the workers list");
		btnLoadTheWorkers.addActionListener(new ActionListener() {
			
			/**
			 * load the table of the workers
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = new initConnection(info);
					Statement myStmt=(Statement) con.connect.createStatement();
					ResultSet myRs= myStmt.executeQuery("SELECT * from workers");
					String query="SELECT * from workers";
					table.setModel(DbUtils.resultSetToTableModel(myRs));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnLoadTheWorkers.setBounds(356, 32, 182, 23);
		frame.getContentPane().add(btnLoadTheWorkers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 694, 281);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
