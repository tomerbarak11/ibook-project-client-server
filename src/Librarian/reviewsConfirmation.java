package Librarian;

import java.awt.Component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import DBcontroller.deleteReviewsFromDB;
import DBcontroller.getBookTitleFromDB;
import DBcontroller.getReviewsBookFromDB;
import DBcontroller.setReviewsStatus;
import Librarian.editInfo.MyComboBoxRenderer;
import common.dbInfo;
import common.initConnection;
/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class shows the reviews that need to be confirmed by the librarian
 * or can be edited or can be deleted
 */
public class reviewsConfirmation extends JFrame {

	public int conditionAddText = 1;
	public reviewsConfirmation reviewConfirm;
	public JComboBox comboBoxReviews = new JComboBox();
	public JFrame frmConfirmReview;
	public JTextField textFieldUpdateReview;
	private initConnection con;
	public dbInfo info;
	public int conditionToEdit = 0;

	/**
	 * @param info - client connection
	 * The method reviewsConfirmation creates the GUI window of confirming,editing or deleting the reviews with the actions that librarian can make
	 */
    public reviewsConfirmation(dbInfo info) {
		this.info = info;
		frmConfirmReview = new JFrame();
		frmConfirmReview.setTitle("Confirm review");
		frmConfirmReview.setBounds(100, 100, 1441, 388);
		frmConfirmReview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConfirmReview.getContentPane().setLayout(null);
		
		JLabel lblPleaseChooseTitle = new JLabel("Please choose which book to edit reviews: ");
		lblPleaseChooseTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPleaseChooseTitle.setBounds(15, 16, 371, 20);
		frmConfirmReview.getContentPane().add(lblPleaseChooseTitle);
		
		ArrayList<String> titleDatabase = new ArrayList<String>();
		try {
			titleDatabase = (ArrayList<String>) getBookTitleFromDB.getBookTitleFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] title = new String[titleDatabase.size()];
		for (int i = 0; i < titleDatabase.size(); i++) {
			title[i] = titleDatabase.get(i);
		}
		//1441 , 1389
		JComboBox comboBoxTitle = new JComboBox(title);
		comboBoxTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxTitle.setRenderer(new MyComboBoxRenderer("Select title:"));
		comboBoxTitle.setSelectedIndex(-1);
		comboBoxTitle.setToolTipText("");
		comboBoxTitle.setBounds(15, 52, 1389, 26);
		frmConfirmReview.getContentPane().add(comboBoxTitle);
		
		JLabel labelSelectReviews = new JLabel("Please select the reviews that you wish to un/confirm or update:");
		labelSelectReviews.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelSelectReviews.setBounds(15, 94, 533, 20);
		frmConfirmReview.getContentPane().add(labelSelectReviews);
		labelSelectReviews.setVisible(false);
				
		JLabel labelEditReview = new JLabel("Do you want to edit the review?");
		labelEditReview.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelEditReview.setBounds(15, 177, 319, 20);
		frmConfirmReview.getContentPane().add(labelEditReview);
		labelEditReview.setVisible(false);

		String[] messageStringsSubjectsChoice = { "Yes", "No" };
		JComboBox comboBoxChoice = new JComboBox(messageStringsSubjectsChoice);
		comboBoxChoice.setSelectedIndex(-1);
		comboBoxChoice.setToolTipText("");
		comboBoxChoice.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxChoice.setBounds(272, 174, 81, 26);
		frmConfirmReview.getContentPane().add(comboBoxChoice);
		comboBoxChoice.setVisible(false);
	
		JButton btnDeleteReview = new JButton("Delete review");
		btnDeleteReview.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnDeleteReview.setBounds(608, 291, 163, 29);
		frmConfirmReview.getContentPane().add(btnDeleteReview);
		btnDeleteReview.setVisible(false);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnConfirm.setBounds(15, 291, 160, 29);
		frmConfirmReview.getContentPane().add(btnConfirm);
		btnConfirm.setVisible(false);
	
		JLabel lblPleaseUpdateThe = new JLabel("Edit the review:");
		lblPleaseUpdateThe.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPleaseUpdateThe.setBounds(15, 213, 348, 20);
		frmConfirmReview.getContentPane().add(lblPleaseUpdateThe);
		lblPleaseUpdateThe.setVisible(false);
		
		textFieldUpdateReview = new JTextField();
		textFieldUpdateReview.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldUpdateReview.setBounds(15, 249, 1389, 26);
		frmConfirmReview.getContentPane().add(textFieldUpdateReview);
		textFieldUpdateReview.setColumns(10);
		textFieldUpdateReview.setVisible(false);
		
		comboBoxReviews.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		comboBoxReviews.setRenderer(new MyComboBoxRenderer("There no reviews to book"));
		comboBoxReviews.setSelectedIndex(-1);
		comboBoxReviews.setToolTipText("");
		comboBoxReviews.setBounds(15, 135, 1389, 26);
		frmConfirmReview.getContentPane().add(comboBoxReviews);
		comboBoxReviews.setVisible(false);
		
		comboBoxTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> reviewsDatabase = new ArrayList<String>();
				try {
					reviewsDatabase = (ArrayList<String>) getReviewsBookFromDB.getReviewsBookFromDB(comboBoxTitle.getSelectedItem().toString(),info);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
						    
				String[] reviews = new String[reviewsDatabase.size()];
				for (int i = 0; i < reviewsDatabase.size(); i++) {
					reviews[i] = reviewsDatabase.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(reviews);
				comboBoxReviews.setModel( model );
				comboBoxReviews.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						labelEditReview.setVisible(true);
						comboBoxChoice.setVisible(true);
						btnDeleteReview.setVisible(true);
						btnConfirm.setVisible(true);
					}
				});
				labelSelectReviews.setVisible(true);
				comboBoxReviews.setVisible(true);
				labelEditReview.setVisible(false);
				comboBoxChoice.setVisible(false);
				btnDeleteReview.setVisible(false);
				btnConfirm.setVisible(false);
				lblPleaseUpdateThe.setVisible(false);
				textFieldUpdateReview.setVisible(false);
				
				conditionToEdit = 1;
				JOptionPane.showMessageDialog(null,
						"Please select review from comboBox");
			  }	
			
		});
				
		comboBoxChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxChoice.getSelectedItem().toString().equals("Yes")){
					lblPleaseUpdateThe.setVisible(true);
					textFieldUpdateReview.setVisible(true);
					conditionAddText = 0;
					}
				else {
					lblPleaseUpdateThe.setVisible(false);
					textFieldUpdateReview.setVisible(false);
					conditionAddText = 1;
				}
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(conditionToEdit == 1){
						if(!(textFieldUpdateReview.getText().equals(""))){
							try {
								setReviewsStatus.setReviewsStatus(textFieldUpdateReview.getText(),comboBoxReviews.getSelectedItem().toString(),info);
								JOptionPane.showMessageDialog(null, "You confirm the review");
								frmConfirmReview.dispose();
								reviewConfirm = new reviewsConfirmation(info);
								reviewConfirm.frmConfirmReview.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} 
						else
							try {
								if(conditionAddText == 1){
							  	    setReviewsStatus.setReviewsStatus(comboBoxReviews.getSelectedItem().toString(),comboBoxReviews.getSelectedItem().toString(),info);
								    JOptionPane.showMessageDialog(null, "You confirm the review");
								    frmConfirmReview.dispose();
								    reviewConfirm = new reviewsConfirmation(info);
								    reviewConfirm.frmConfirmReview.setVisible(true);
								}
								else
									JOptionPane.showMessageDialog(null, "You did not enter text to change the review");
							} catch (Exception e) {
								e.printStackTrace();
							}
				} 
				
			}
		});
				
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press button Exit
		 */
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConfirmReview.setVisible(false);
			}
		});
		btnExit.setBounds(1235, 291, 169, 29);
		frmConfirmReview.getContentPane().add(btnExit);
		
		/**
		 * librarian press the button delete review
		 */
		btnDeleteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, "You delete the review\n" + comboBoxReviews.getSelectedItem().toString());
					deleteReviewsFromDB.deleteReviewsFromDB(comboBoxReviews.getSelectedItem().toString(),info);
					frmConfirmReview.dispose();
					reviewConfirm = new reviewsConfirmation(info);
					reviewConfirm.frmConfirmReview.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	/**
	 * The method MyComboBoxRenderer is changes the title of 
	 * the combobox on on the program
	 */
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}
}
