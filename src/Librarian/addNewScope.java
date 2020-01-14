package Librarian;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import DBcontroller.addScopeToDB;
import DBcontroller.addSubjectToDB;
import DBcontroller.getScopeFromDB;
import DBcontroller.getSubjectFromDB;
import common.dbInfo;
import java.awt.Font;
/**
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class performs action of add new scope by the librarian to the system
 */
public class addNewScope extends JFrame{

	public int conBookScope = 0;
	public addNewScope addnewscope;
	public JFrame frmAddNewScope;
	private JTextField textFieldScope;
	private JTextField textFieldTitle;
	private JTextField textFieldTitleChoise;
	private int ConAddScope = 0;
	private int ConAddSubject = 0;
    public dbInfo info;
    /**
     * @param info - client connection
     * The method addNewBook creates the GUI window of add new scope with the actions that librarian can make
     */
	public addNewScope(dbInfo info) {
		this.info = info;
		frmAddNewScope = new JFrame();
		frmAddNewScope.setTitle("Add New Scope\r\n");
		frmAddNewScope.setBounds(100, 100, 538, 223);
		frmAddNewScope.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddNewScope.getContentPane().setLayout(null);

		JLabel lblAddScope = new JLabel("Enter new scope name:");
		lblAddScope.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblAddScope.setBounds(21, 16, 225, 20);
		frmAddNewScope.getContentPane().add(lblAddScope);

		textFieldScope = new JTextField();
		textFieldScope.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldScope.setBounds(206, 14, 295, 26);
		frmAddNewScope.getContentPane().add(textFieldScope);
		textFieldScope.setColumns(10);

		JLabel lblDoYouWanna = new JLabel("Do you wanna add subject to the scope?");
		lblDoYouWanna.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblDoYouWanna.setBounds(21, 52, 316, 20);
		frmAddNewScope.getContentPane().add(lblDoYouWanna);

		JLabel lblEnterNewSubject = new JLabel("Enter new subject:");
		lblEnterNewSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEnterNewSubject.setBounds(21, 88, 177, 20);
		frmAddNewScope.getContentPane().add(lblEnterNewSubject);
		lblEnterNewSubject.setVisible(false);

		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldTitle.setBounds(176, 86, 325, 26);
		frmAddNewScope.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		textFieldTitle.setVisible(false);
		
		String[] messageStringsSubjectsChoice = { "Yes", "No" };
		JComboBox comboBoxChoiceAddSubject = new JComboBox(messageStringsSubjectsChoice);
		comboBoxChoiceAddSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian select option to add subject or not
		 */
		comboBoxChoiceAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxChoiceAddSubject.getSelectedIndex() == 0) {
					lblEnterNewSubject.setVisible(true);
					textFieldTitle.setVisible(true);
					
				} else {
					lblEnterNewSubject.setVisible(false);
					textFieldTitle.setVisible(false);
				}
			}
		});
		
		comboBoxChoiceAddSubject.setRenderer(new MyComboBoxRenderer(""));
		comboBoxChoiceAddSubject.setSelectedIndex(-1);
		comboBoxChoiceAddSubject.setToolTipText("");
		comboBoxChoiceAddSubject.setBounds(340, 50, 56, 26);
		frmAddNewScope.getContentPane().add(comboBoxChoiceAddSubject);

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
	

		JButton buttonOK = newBtnMyFirstButton();
		buttonOK.setBounds(21, 128, 115, 29);
		frmAddNewScope.getContentPane().add(buttonOK);

		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press button cancel
		 */
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddNewScope.setVisible(false);
			}
		});
		buttonCancel.setBounds(386, 128, 115, 29);
		frmAddNewScope.getContentPane().add(buttonCancel);
		
		for (int j = 0; j < scope.length; j++) 
			if (scope[j].equals(textFieldScope.getText()))
				conBookScope = 1;
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
 * The method newBtnMyFirstButton is mentioned the action listener that will happen when librarian will press
 * the button OK
 * @return - the button of the action listener
 */
	private JButton newBtnMyFirstButton() {
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press the button OK 
		 */
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				
				String nameScope = textFieldScope.getText();
				String nameTitle = textFieldTitle.getText();
				for (int i = 0; i < scope.length; i++) {
					if (scope[i].equals(nameScope))
						ConAddScope = 1;
				}
	
				if (ConAddScope == 0) {
					if (textFieldScope.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter scope\nPlease enter scope");
					} else {
						if (textFieldTitle.getText().equals("")) {
							try {
								addScopeToDB.addScopeToDB(nameScope,info);
							} catch (Exception e) {
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "You add new scope:" + nameScope);
							frmAddNewScope.dispose();
							addnewscope = new addNewScope(info);
							addnewscope.frmAddNewScope.setVisible(true);
						} else {
							try {
								addScopeToDB.addScopeToDB(nameScope,info);
								ArrayList<String> subjectNameScopeDatabase = new ArrayList<String>();
								try {
									subjectNameScopeDatabase = (ArrayList<String>) getSubjectFromDB.getSubjectFromDB(info,nameScope);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								String[] subjectByScope = new String[subjectNameScopeDatabase.size()];
								for (int i = 0; i < subjectNameScopeDatabase.size(); i++) {
									subjectByScope[i] = subjectNameScopeDatabase.get(i);
								}
								
								for (int i = 0; i < subjectByScope.length; i++) {
									if (subjectByScope[i].equals(nameTitle))
										ConAddSubject = 1;
								}
							if(conBookScope == 0){
								if(ConAddSubject == 0){
									try {
							     	addSubjectToDB.addSubjectToDB(nameScope,nameTitle,info);
									}catch (Exception e) {
										e.printStackTrace();
									}
							     	JOptionPane.showMessageDialog(null,
											"You add new scope:" + nameScope + " with subject:" + nameTitle);
							     	ConAddSubject = 0;
									frmAddNewScope.dispose();
									addnewscope = new addNewScope(info);
									addnewscope.frmAddNewScope.setVisible(true);
								}
								else
									JOptionPane.showMessageDialog(null,
											nameTitle + " is a subject that already exist\nPlease enter another subject");
							}
							else{
								JOptionPane.showMessageDialog(null,
										nameTitle + "scope is already added,please return to menu and press add subject");
							}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							nameScope + " is a scope that already exist\nPlease enter another scope");
				}
				ConAddScope = 0;
			}
		});

		return btnOK;
	}

}
