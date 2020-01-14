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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import DBcontroller.addBook2ToDB;
import DBcontroller.addBookToDB;
import DBcontroller.getBookAuthorFromDB;
import DBcontroller.getBookTitleFromDB;
import DBcontroller.getScopeFromDB;
import DBcontroller.getSubjectFromDB;
import common.dbInfo;

/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak The
 *         class performs action of add book by the librarian to the system
 */
public class addBook extends JFrame {

	public JComboBox scopeComboBox;
	private addBook addbook;
	public JFrame frmAddNewBook;
	public int chooseSubject = 0;
	public JTextField textFieldNameBook;
	public JTextField textFieldAuthor;
	public JTextField textFieldPrice;
	public int conBookName = 0;
	public int conBookAuthor = 0;
	public int conBookLanguage = 0;
	public int twoAuthors = 0;
	public JTextField textFieldLanguage;
	public dbInfo infoConnectDatabase;
	public JTextField textInsertSecondAuthor;
	public JTextArea textAreaPlot;
	public JTextArea textAreaTBN;
	public String bookPlotText;
	public String bookTableofContents;
	public JTextArea textFieldPlot = new JTextArea();;
	public JTextArea textFieldTBN = new JTextArea();
	private JTextField textFieldDOC;
	private JTextField textFieldPDF;;

	/**
	 * 
	 * @param info - client connection
	 *            The method addBook creates the GUI window
	 *            of add book with the actions that librarian can make
	 */
	public addBook(dbInfo info) {

		this.infoConnectDatabase = info;
		frmAddNewBook = new JFrame();
		frmAddNewBook.setFont(new Font("Dialog", Font.BOLD, 12));
		frmAddNewBook.setTitle("Add New Book");
		frmAddNewBook.setBounds(100, 100, 622, 727);
		frmAddNewBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddNewBook.getContentPane().setLayout(null);

		ArrayList<String> scopeDatabase = new ArrayList<String>();
		try {
			scopeDatabase = (ArrayList<String>) getScopeFromDB.getScopeFromDB(infoConnectDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] scope = new String[scopeDatabase.size()];
		for (int i = 0; i < scopeDatabase.size(); i++) {
			scope[i] = scopeDatabase.get(i);
		}

		String[] messageStringsSubjectsChoice = { "Yes", "No" };
		JComboBox comboBoxChoiceaddSecondAuthor = new JComboBox(messageStringsSubjectsChoice);
		comboBoxChoiceaddSecondAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		comboBoxChoiceaddSecondAuthor.setSelectedIndex(-1);
		comboBoxChoiceaddSecondAuthor.setBounds(185, 196, 67, 26);
		frmAddNewBook.getContentPane().add(comboBoxChoiceaddSecondAuthor);

		JComboBox subjectComboBox = new JComboBox();
		subjectComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		subjectComboBox.setBounds(185, 53, 400, 31);
		frmAddNewBook.getContentPane().add(subjectComboBox);
		subjectComboBox.setVisible(false);

		scopeComboBox = new JComboBox(scope);
		scopeComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		scopeComboBox.setRenderer(new MyComboBoxRenderer("Select Scope"));
		scopeComboBox.setSelectedIndex(-1);
		scopeComboBox.setToolTipText("");
		scopeComboBox.setBounds(185, 16, 400, 26);
		frmAddNewBook.getContentPane().add(scopeComboBox);
		/**
		 * librarian choose to add scope
		 */
		scopeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> SubjectDatabase = new ArrayList<String>();
				try {
					SubjectDatabase = (ArrayList<String>) getSubjectFromDB.getSubjectFromDB(infoConnectDatabase,
							scopeComboBox.getSelectedItem().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String[] subject = new String[SubjectDatabase.size()];
				for (int i = 0; i < SubjectDatabase.size(); i++) {
					subject[i] = SubjectDatabase.get(i);
				}

				DefaultComboBoxModel model = new DefaultComboBoxModel(subject);
				subjectComboBox.setModel(model);
				subjectComboBox.setRenderer(new MyComboBoxRenderer("The scope not have subject"));
				subjectComboBox.setVisible(true);
				
				JOptionPane.showMessageDialog(null,
						"Please select subject from comboBox");

			}
		});



		ArrayList<String> bookTitleDatabase = new ArrayList<String>();
		try {
			bookTitleDatabase = (ArrayList<String>) getBookTitleFromDB.getBookTitleFromDB(infoConnectDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] bookName = new String[bookTitleDatabase.size()];
		for (int i = 0; i < bookTitleDatabase.size(); i++) {
			bookName[i] = bookTitleDatabase.get(i);
		}

		ArrayList<String> bookAuthorDatabase = new ArrayList<String>();
		try {
			bookAuthorDatabase = (ArrayList<String>) getBookAuthorFromDB.getBookAuthorFromDB(infoConnectDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] bookAuthor = new String[bookAuthorDatabase.size()];
		for (int i = 0; i < bookAuthorDatabase.size(); i++) {
			bookAuthor[i] = bookAuthorDatabase.get(i);
		}

		ArrayList<String> bookLanguageDatabase = new ArrayList<String>();
		try {
			bookLanguageDatabase = (ArrayList<String>) getBookAuthorFromDB.getBookAuthorFromDB(infoConnectDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] bookLanguage = new String[bookLanguageDatabase.size()];
		for (int i = 0; i < bookLanguageDatabase.size(); i++) {
			bookLanguage[i] = bookLanguageDatabase.get(i);
		}

		textFieldNameBook = new JTextField();
		textFieldNameBook.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldNameBook.setBounds(185, 100, 400, 26);
		frmAddNewBook.getContentPane().add(textFieldNameBook);
		textFieldNameBook.setColumns(10);

		JLabel labelChooseScope = new JLabel("Choose scope book:");
		labelChooseScope.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelChooseScope.setBounds(15, 19, 155, 20);
		frmAddNewBook.getContentPane().add(labelChooseScope);

		JLabel lblEnterTitleNook = new JLabel("Enter title book:");
		lblEnterTitleNook.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEnterTitleNook.setBounds(15, 103, 132, 20);
		frmAddNewBook.getContentPane().add(lblEnterTitleNook);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		/**
		 * librarian press the button cancel
		 */
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAddNewBook.setVisible(false);
			}
		});
		btnCancel.setBounds(453, 624, 132, 29);
		frmAddNewBook.getContentPane().add(btnCancel);

		textFieldAuthor = new JTextField();
		textFieldAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldAuthor.setBounds(185, 142, 400, 26);
		frmAddNewBook.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);

		textFieldPrice = new JTextField();
		textFieldPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldPrice.setBounds(185, 385, 400, 26);
		frmAddNewBook.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);

		JLabel lblEnterAuthor = new JLabel("Enter name author:");
		lblEnterAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEnterAuthor.setBounds(15, 145, 153, 20);
		frmAddNewBook.getContentPane().add(lblEnterAuthor);

		JLabel lblEnterPrice = new JLabel("Enter price book:");
		lblEnterPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEnterPrice.setBounds(15, 388, 155, 20);
		frmAddNewBook.getContentPane().add(lblEnterPrice);

		JLabel lblEnterlanguage = new JLabel("Enter language book:");
		lblEnterlanguage.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEnterlanguage.setBounds(15, 241, 168, 20);
		frmAddNewBook.getContentPane().add(lblEnterlanguage);

		textFieldLanguage = new JTextField();
		textFieldLanguage.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldLanguage.setBounds(185, 238, 400, 26);
		frmAddNewBook.getContentPane().add(textFieldLanguage);
		textFieldLanguage.setColumns(10);

		JLabel labelPlotBook = new JLabel("Enter plot book:");
		labelPlotBook.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelPlotBook.setBounds(15, 280, 145, 20);
		frmAddNewBook.getContentPane().add(labelPlotBook);

		JLabel lblEntenTableOfContents = new JLabel("Enter table of ");
		lblEntenTableOfContents.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEntenTableOfContents.setBounds(15, 432, 132, 20);
		frmAddNewBook.getContentPane().add(lblEntenTableOfContents);

		JLabel lblToInsertSecondAuthor = new JLabel("To insert second");
		lblToInsertSecondAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblToInsertSecondAuthor.setBounds(15, 186, 168, 20);
		frmAddNewBook.getContentPane().add(lblToInsertSecondAuthor);

		JLabel lblEntenTableOfContents2 = new JLabel("contents:");
		lblEntenTableOfContents2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEntenTableOfContents2.setBounds(15, 451, 92, 20);
		frmAddNewBook.getContentPane().add(lblEntenTableOfContents2);

		textInsertSecondAuthor = new JTextField();
		textInsertSecondAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textInsertSecondAuthor.setBounds(267, 196, 318, 26);
		frmAddNewBook.getContentPane().add(textInsertSecondAuthor);
		textInsertSecondAuthor.setColumns(10);
		textInsertSecondAuthor.setVisible(false);

		JLabel chooseOptionSecondAuthor = new JLabel("author - choose YES:");
		chooseOptionSecondAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		chooseOptionSecondAuthor.setBounds(15, 202, 192, 20);
		frmAddNewBook.getContentPane().add(chooseOptionSecondAuthor);
        /**
         * librarian choose to add second author or not
         */
		comboBoxChoiceaddSecondAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxChoiceaddSecondAuthor.getSelectedItem().toString().equals("Yes")) {
					textInsertSecondAuthor.setVisible(true);
					twoAuthors = 1;
				} else {
					twoAuthors = 0;
					textInsertSecondAuthor.setVisible(false);
				}
			}
		});

		JScrollPane scrollPanePDF = new JScrollPane((Component) null);
		scrollPanePDF.setBounds(185, 587, 400, 26);
		scrollPanePDF.setVisible(true);
		frmAddNewBook.getContentPane().add(scrollPanePDF);

		textFieldPDF = new JTextField();
		textFieldPDF.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		scrollPanePDF.setViewportView(textFieldPDF);
		
		textFieldPDF.setColumns(10);

		JScrollPane scrollPaneDOC = new JScrollPane((Component) null);
		scrollPaneDOC.setBounds(185, 547, 400, 26);
		scrollPaneDOC.setVisible(true);
		frmAddNewBook.getContentPane().add(scrollPaneDOC);

		textFieldDOC = new JTextField();
		textFieldDOC.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		scrollPaneDOC.setViewportView(textFieldDOC);
		textFieldDOC.setColumns(10);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(185, 277, 400, 95);
		scrollPane.setVisible(true);
		frmAddNewBook.getContentPane().add(scrollPane);
		JScrollPane scrollPane1 = new JScrollPane((Component) null);
		scrollPane1.setBounds(185, 427, 400,102);
		scrollPane1.setVisible(true);
		frmAddNewBook.getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(textFieldTBN);
		textFieldTBN.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldTBN.setColumns(10);

		textFieldPlot.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	
		scrollPane.setViewportView(textFieldPlot);

		JLabel lblUrlDocPlace = new JLabel("URL doc place:");
		lblUrlDocPlace.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblUrlDocPlace.setBounds(15, 547, 155, 20);
		frmAddNewBook.getContentPane().add(lblUrlDocPlace);

		JLabel lblUrlPdfPlace = new JLabel("URL Pdf place:");
		lblUrlPdfPlace.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblUrlPdfPlace.setBounds(15, 589, 155, 20);
		frmAddNewBook.getContentPane().add(lblUrlPdfPlace);

		JLabel lblChooseSubject = new JLabel("Choose subject book:");
		lblChooseSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblChooseSubject.setBounds(15, 67, 168, 14);
		frmAddNewBook.getContentPane().add(lblChooseSubject);
		
		
		
		JButton btnOk = newBtnMyFirstButton(scopeComboBox, subjectComboBox, bookName, bookAuthor, bookLanguage,
				bookPlotText, bookTableofContents,textFieldPDF.getText(),textFieldDOC.getText());
		btnOk.setBounds(15, 624, 114, 29);
		btnOk.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		frmAddNewBook.getContentPane().add(btnOk);
		btnOk.setVisible(false);
		/**
		 * librarian choose subject from comboBox
		 */
		scopeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOk.setVisible(true);
			}
		});
		
	}

	/**
	 * 
	 * The method MyComboBoxRenderer is changes the title of the combobox on on
	 * the program
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
 *  The method newBtnMyFirstButton is mentioned the action listener that will
	 * happen when user will press the button OK
	 * 
	 * @param scopeComboBox
	 *            - The combobox that shows all scopes
	 * @param subjectComboBox
	 *            - The combobox that shows all subjects
	 * @param bookName
	 *            - The variable that mentioned name of book
	 * @param bookAuthor
	 *            - The variable that mentioned name of author of the book
	 * @param bookLanguage
	 *            - The variable that mentioned name of language of the book
	 * @param bookPlotText
	 *            - The variable that mentioned name of plot of the book
	 * @param bookTableofContents
	 *            - The variable that mentioned table of contents of the book
	 * @param textFieldPDF1
	 *             - The variable that mentioned PDF document of book
	 * @param textFieldDOC1
	 *              - The variable that mentioned DOC document of book
	 * @return - the button of the action listener
  */
	private JButton newBtnMyFirstButton(JComboBox scopeComboBox, JComboBox subjectComboBox,
			String[] bookName, String[] bookAuthor,String[] bookLanguage,String bookPlotText,String bookTableofContents,String textFieldPDF1,String textFieldDOC1) {
		JButton btnOK = new JButton("OK");
        /**
         * librarian press the button OK
         */
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int conSubject = 0;
				String bookNameText = textFieldNameBook.getText();
				String bookAuthorText = textFieldAuthor.getText();
				String priceBook = textFieldPrice.getText();
				String bookLanguageText = textFieldLanguage.getText();
				String bookAuthor2Text = textInsertSecondAuthor.getText();
				String bookTBN = textFieldTBN.getText();
				String bookPlot = textFieldPlot.getText();
				String urlDOC = textFieldDOC.getText();
				String urlPDF = textFieldPDF.getText();
				String scopeString;
				String subjectString;
				for (int i = 0; i < bookName.length; i++) 
					if (bookName[i].equals(bookNameText))
						conBookName = 1;
				for (int j = 0; j < bookAuthor.length; j++) 
					if (bookAuthor[j].equals(bookAuthorText))
						conBookAuthor = 1;
			if(conBookName == 0){
					if (textFieldNameBook.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter name\nPlease enter name");
					} else if (textFieldAuthor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter author\nPlease enter author");
					} else if (textInsertSecondAuthor.getText().isEmpty() && twoAuthors == 1) {
						JOptionPane.showMessageDialog(null, "You did not enter second author\nPlease enter secont author");
					} else if (textFieldLanguage.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter language\nPlease enter language");
					} else if (textFieldPlot.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter plot\nPlease enter plot");
					} else if (textFieldPrice.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter price\nPlease enter price");
					} else if (textFieldTBN.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter table of contents\nPlease enter table of contents");
					} else if (textFieldDOC.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter url DOC\nPlease enter url DOC");
					} else if (textFieldPDF.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "You did not enter url PDF\nPlease enter url PDF");
					} 
					else {
						if (scopeComboBox.getSelectedIndex() == -1){
							scopeString = " ";
						}
						else
							scopeString = scopeComboBox.getSelectedItem().toString();
						if (subjectComboBox.getSelectedIndex() == -1){
							conSubject = 1;
							subjectString = " ";
						}
						else
							subjectString = subjectComboBox.getSelectedItem().toString();
						try {
							if(twoAuthors == 0){
							addBook2ToDB.addBook2ToDB(bookAuthorText,bookNameText,bookLanguageText,bookPlot,subjectString,scopeString,
									priceBook,bookTBN," ",textFieldPDF.getText(),textFieldDOC.getText(),infoConnectDatabase);
							}
							else{							   
								addBook2ToDB.addBook2ToDB(bookAuthorText,bookNameText,bookLanguageText,bookPlot,subjectString,scopeString,
										   priceBook,bookTBN,bookAuthor2Text,textFieldPDF.getText(),textFieldDOC.getText(),infoConnectDatabase);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(conSubject == 0){
						JOptionPane.showMessageDialog(null,
								"You add new book\n Scope:" + scopeString + "\n Title:" + subjectString + "\n Name:"
										+ bookNameText + "\n Author:" + bookAuthorText 
										+ "\n Price:" + priceBook);
						}
						else
							JOptionPane.showMessageDialog(null,
									"You add new book\n Scope:" + scopeString + "\n Name:"
											+ bookNameText + "\n Author:" + bookAuthorText 
											+ "\n Price:" + priceBook);
						frmAddNewBook.dispose();
						addbook = new addBook(infoConnectDatabase);
						addbook.frmAddNewBook.setVisible(true);
					}
				conBookName = 0;
				conBookAuthor = 0;
			}else
			     JOptionPane.showMessageDialog(null, "The book title exist\nPlease enter another title");
			conBookName = 0;
		}
				
		});
		return btnOK;
	}

	public static void main(String[] args) {
	}
}
