package Librarian;

import java.awt.Component;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import DBcontroller.addSubjectToDB;
import DBcontroller.getScopeFromDB;
import DBcontroller.getSubjectFromDB;
import common.dbInfo;
import java.awt.Font;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class performs action of add new subject by the librarian to the system
 */
public class addNewSubject extends JFrame{

	public addNewSubject addnewsubject;
	public JFrame frmAddNewSubject;
	private JTextField textFieldTitle;
	private int ConAddSubject = 0;
	private JComboBox comboBoxFieldScope;
	public dbInfo info;
    /**
     * @param info - client connection
     * The method addNewBook creates the GUI window of add new subject with the actions that librarian can make
     */
	public addNewSubject(dbInfo info) {
		this.info = info;
		frmAddNewSubject = new JFrame();
		frmAddNewSubject.setTitle("Add New Subject");
		frmAddNewSubject.setBounds(100, 100, 455, 197);
		frmAddNewSubject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddNewSubject.getContentPane().setLayout(null);

		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		buttonCancel.setBounds(296, 98, 123, 29);
		/**
		 * librarian press the button cancel
		 */
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAddNewSubject.setVisible(false);
			}
		});
		frmAddNewSubject.getContentPane().add(buttonCancel);

		JLabel lblNameSubject = new JLabel("Add new subject:");
		lblNameSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNameSubject.setBounds(15, 62, 144, 20);
		frmAddNewSubject.getContentPane().add(lblNameSubject);
		lblNameSubject.setVisible(false);

		ArrayList<String> scopeSubject = new ArrayList<String>();
		try {
			scopeSubject = (ArrayList<String>) getSubjectFromDB.getSubjectFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] title = new String[scopeSubject.size()];
		for (int i = 0; i < scopeSubject.size(); i++) {
			title[i] = scopeSubject.get(i);
		}

		JButton buttonOK = newBtnMyFirstButton(title);
		buttonOK.setBounds(15, 98, 95, 29);
		frmAddNewSubject.getContentPane().add(buttonOK);

		
		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldTitle.setBounds(160, 60, 259, 26);
		frmAddNewSubject.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		textFieldTitle.setVisible(false);
		
		
		ArrayList<String> scopeDatabase = new ArrayList<String>();
		try {
			scopeDatabase = (ArrayList<String>) getScopeFromDB.getScopeFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] scope = new String[scopeDatabase.size()];
		for (int i = 0; i < scopeDatabase.size(); i++) {
			scope[i] = scopeDatabase.get(i);
		}

		comboBoxFieldScope = new JComboBox(scope);
		comboBoxFieldScope.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxFieldScope.setRenderer(new MyComboBoxRenderer("Select Scope"));
		comboBoxFieldScope.setSelectedIndex(-1);
		comboBoxFieldScope.setToolTipText("");
		comboBoxFieldScope.setBounds(15, 16, 404, 30);
		frmAddNewSubject.getContentPane().add(comboBoxFieldScope);
		/**
		 * librarian choose the scope at comboBox
		 */
		comboBoxFieldScope.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldTitle.setVisible(true);
				lblNameSubject.setVisible(true);
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
/**
 * The method newBtnMyFirstButton is mentioned the action listener that will happen when user will press
 * the button OK
 * @param subject - The array shows the subjects
 * @return - the button of the action listener
 */
	private JButton newBtnMyFirstButton(String subject[]) {
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press the button OK
		 */
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String scopeName = comboBoxFieldScope.getSelectedItem().toString();
				String subjectName = textFieldTitle.getText();
				for (int i = 0; i < subject.length; i++) {
					if (subject[i].equals(subjectName))
						ConAddSubject = 1;
				}
				if (ConAddSubject == 0) {
					if (textFieldTitle.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter subject\nPlease enter subject");
					}
					else{
						try {
							addSubjectToDB.addSubjectToDB(scopeName,subjectName,info);
						} catch (Exception e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "You add new subject:" + subjectName);
						frmAddNewSubject.dispose();
						addnewsubject = new addNewSubject(info);
						addnewsubject.frmAddNewSubject.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, subjectName + " is a subject that already exist\nPlease enter another subject");
				}
				ConAddSubject = 0;
			}
		});
		return btnOK;
	}

}
