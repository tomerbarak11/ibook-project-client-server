package Librarian;

import java.awt.Component;

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

import Librarian.addNewScope.MyComboBoxRenderer;
import common.dbInfo;
import javax.swing.event.AncestorListener;

import DBcontroller.addAuthorToDB;
import DBcontroller.addNameToDB;
import DBcontroller.addScopeToDB;
import DBcontroller.addSubjectToDB;
import DBcontroller.getBookAuthorFromDB;
import DBcontroller.getBookTitleFromDB;
import DBcontroller.getScopeFromDB;
import DBcontroller.getSubjectFromDB;

import javax.swing.event.AncestorEvent;
import java.awt.Font;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class performs action of changing the information of the titles of the book,authors,scopes,titles 
 * and have option to open GUI of changing details of reviews and all the details of single book 
 */
public class editInfo extends JFrame{

	public editInfo editbook;
	public dbInfo info;
	public JFrame frmEditInformation;
	private JTextField textFieldUpdate;
    private String whichInfoChange;
    private int selectNumber;
    private showBookInfo showbookinfo;
	/**
	 * @param info - client connection
	 * The method editInfo creates the GUI window of changing the information with the actions with the actions that librarian can make
	 */
	public editInfo(dbInfo info) {
		this.info = info;
		frmEditInformation = new JFrame();
		frmEditInformation.setTitle("Edit Information");
		frmEditInformation.setBounds(100, 100, 427, 387);
		frmEditInformation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditInformation.getContentPane().setLayout(null);
		
		ArrayList<String> AuthorDatabase = new ArrayList<String>();
		try {
			AuthorDatabase = (ArrayList<String>) getBookAuthorFromDB.getBookAuthorFromDB(info);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] Author = new String[AuthorDatabase.size()];
		for (int i = 0; i < AuthorDatabase.size(); i++) {
			Author[i] = AuthorDatabase.get(i);
		}
		
		ArrayList<String> SubjectDatabase = new ArrayList<String>();
		try {
			SubjectDatabase = (ArrayList<String>) getSubjectFromDB.getSubjectFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] subject = new String[SubjectDatabase.size()];
		for (int i = 0; i < SubjectDatabase.size(); i++) {
			subject[i] = SubjectDatabase.get(i);
		}
						
		ArrayList<String> ScopeDatabase = new ArrayList<String>();
		try {
			ScopeDatabase = (ArrayList<String>) getScopeFromDB.getScopeFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] scope = new String[ScopeDatabase.size()];
		for (int i = 0; i < ScopeDatabase.size(); i++) {
			scope[i] = ScopeDatabase.get(i);
		}
		
		ArrayList<String> TitleDatabase = new ArrayList<String>();
		try {
			TitleDatabase = (ArrayList<String>) getBookTitleFromDB.getBookTitleFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] title = new String[TitleDatabase.size()];
		for (int i = 0; i < TitleDatabase.size(); i++) {
			title[i] = TitleDatabase.get(i);
		}
				
		JComboBox comboBoxUpdate = new JComboBox();
		comboBoxUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxUpdate.setRenderer(new MyComboBoxRenderer("Select what you wanna change:"));
		comboBoxUpdate.addItem("author");
		comboBoxUpdate.addItem("name");
		comboBoxUpdate.addItem("scope");
		comboBoxUpdate.addItem("subject");
		comboBoxUpdate.addItem("change book info");
		comboBoxUpdate.setSelectedIndex(-1);
		comboBoxUpdate.setToolTipText("");
		comboBoxUpdate.setBounds(15, 55, 375, 26);
		frmEditInformation.getContentPane().add(comboBoxUpdate);
		
		JLabel labelChooseInformation = new JLabel("Please choose exist database you wanna change:");
		labelChooseInformation.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelChooseInformation.setBounds(15, 97, 404, 20);
		frmEditInformation.getContentPane().add(labelChooseInformation);
		labelChooseInformation.setVisible(false);
		
		JComboBox comboBoxChoose = new JComboBox();
		comboBoxChoose.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxChoose.setBounds(15, 133, 375, 26);
		frmEditInformation.getContentPane().add(comboBoxChoose);
		comboBoxChoose.setVisible(false);
		
		JButton buttonUpdateInfo = new JButton("Update Info");
		buttonUpdateInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		buttonUpdateInfo.setVisible(false);
		buttonUpdateInfo.setBounds(15, 250, 375, 29);
		frmEditInformation.getContentPane().add(buttonUpdateInfo);
		
		JLabel labelUpdateTheInformation = new JLabel("Please update the one you choose:");
		labelUpdateTheInformation.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelUpdateTheInformation.setBounds(15, 162, 341, 45);
		frmEditInformation.getContentPane().add(labelUpdateTheInformation);
		labelUpdateTheInformation.setVisible(false);
		
		textFieldUpdate = new JTextField();
		textFieldUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldUpdate.setBounds(15, 208, 375, 26);
		frmEditInformation.getContentPane().add(textFieldUpdate);
		textFieldUpdate.setColumns(10);
		textFieldUpdate.setVisible(false);
		
		/**
		 * librarian choose which information to update
		 */
		comboBoxUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				selectNumber = comboBoxUpdate.getSelectedIndex();
				whichInfoChange = comboBoxUpdate.getSelectedItem().toString();
				buttonUpdateInfo.setVisible(false);
				textFieldUpdate.setVisible(false);
				labelUpdateTheInformation.setVisible(false);
				if (comboBoxUpdate.getSelectedIndex() == 0){
					labelChooseInformation.setVisible(true);
					comboBoxChoose.setVisible(true);
					JOptionPane.showMessageDialog(null,
							"Please select author book from comboBox");
					DefaultComboBoxModel model = new DefaultComboBoxModel(Author);
					comboBoxChoose.setModel( model );
		        }  
			
				else if (comboBoxUpdate.getSelectedIndex() == 1){
					labelChooseInformation.setVisible(true);
					comboBoxChoose.setVisible(true);
					JOptionPane.showMessageDialog(null,
							"Please select title book from comboBox");
					DefaultComboBoxModel model = new DefaultComboBoxModel(title);
					comboBoxChoose.setModel( model );
			    }
				
				
				else if (comboBoxUpdate.getSelectedIndex() == 2){
					labelChooseInformation.setVisible(true);
					comboBoxChoose.setVisible(true);
					JOptionPane.showMessageDialog(null,
							"Please select scope from comboBox");
					DefaultComboBoxModel model = new DefaultComboBoxModel(scope);
					comboBoxChoose.setModel( model );
			    }
				
				else if (comboBoxUpdate.getSelectedIndex() == 3){
					labelChooseInformation.setVisible(true);
					comboBoxChoose.setVisible(true);
					JOptionPane.showMessageDialog(null,
							"Please select subject from comboBox");
					DefaultComboBoxModel model = new DefaultComboBoxModel(subject);
					comboBoxChoose.setModel( model );
			    }
								
				else if (comboBoxUpdate.getSelectedIndex() == 4){
					showbookinfo = new showBookInfo(info);
					showbookinfo.frameShowBookInfo.setVisible(true);
				}
				
			}
		});
		
		/**
		 * librarian choose which information to update
		 */
		comboBoxChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonUpdateInfo.setVisible(true);
			}
		});
		JLabel lblWhatDoYou = new JLabel("What do you wanna update?");
		lblWhatDoYou.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblWhatDoYou.setBounds(15, 16, 232, 38);
		frmEditInformation.getContentPane().add(lblWhatDoYou);
		
			
		JButton btnNewButton = new JButton("Exit Menu");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press the button exit
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditInformation.setVisible(false);
			}
		});
		btnNewButton.setBounds(15, 295, 375, 29);
		frmEditInformation.getContentPane().add(btnNewButton);
		
		String[] messageStringsSubjectsChoice = { "Yes", "No" };
		/**
		 * librarian choose to update the information
		 */
		comboBoxChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				labelUpdateTheInformation.setVisible(true);
				textFieldUpdate.setVisible(true);
			}
		});
		/**
		 * librarian press the button update info
		 */
		buttonUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textFieldUpdate.getText().isEmpty()) && !(comboBoxChoose.getSelectedItem().toString().isEmpty())){
				if(whichInfoChange.equals("author")){
					try {
						addAuthorToDB.addAuthorToDB(textFieldUpdate.getText(),comboBoxChoose.getSelectedItem().toString(),info);
						JOptionPane.showMessageDialog(null, "You updated " + comboBoxChoose.getSelectedItem().toString() + " to " + textFieldUpdate.getText());
						frmEditInformation.dispose();
						editbook = new editInfo(info);
						editbook.frmEditInformation.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if(whichInfoChange.equals("name")){
					try {
						addNameToDB.addNameToDB(textFieldUpdate.getText(),comboBoxChoose.getSelectedItem().toString(),info);
						JOptionPane.showMessageDialog(null, "You updated " + comboBoxChoose.getSelectedItem().toString() + " to " + textFieldUpdate.getText());
						frmEditInformation.dispose();
						editbook = new editInfo(info);
						editbook.frmEditInformation.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if(whichInfoChange.equals("scope")){
					try {
						addScopeToDB.editScopeToDB(textFieldUpdate.getText(),comboBoxChoose.getSelectedItem().toString(),info);
						JOptionPane.showMessageDialog(null, "You updated " + comboBoxChoose.getSelectedItem().toString() + " to " + textFieldUpdate.getText());
						frmEditInformation.dispose();
						editbook = new editInfo(info);
						editbook.frmEditInformation.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if(whichInfoChange.equals("subject")){
					try {
						addSubjectToDB.editSubjectToDB(textFieldUpdate.getText(),comboBoxChoose.getSelectedItem().toString(),info);
						JOptionPane.showMessageDialog(null, "You updated " + comboBoxChoose.getSelectedItem().toString() + " to " + textFieldUpdate.getText());
						frmEditInformation.dispose();
						editbook = new editInfo(info);
						editbook.frmEditInformation.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			  }
				
				else if(textFieldUpdate.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Please enter text to the text to update");
				}
			}
		});
		
		
	}
	/**
	 * 
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
