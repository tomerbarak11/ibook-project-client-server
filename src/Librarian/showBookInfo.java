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

import DBcontroller.deleteBookFromDB;
import DBcontroller.editInfoBook;
import DBcontroller.getBookAuthorFromDB;
import DBcontroller.getBookTitleFromDB;
import DBcontroller.getDetailsOfBookFromDB;
import DBcontroller.getReviewsBookFromDB;
import common.dbInfo;

import javax.swing.JPasswordField;
/**
 * 
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 * The class performs action of changing the information of single book:
 * His title,author,second author,plot,price and table of contents
 */
public class showBookInfo extends JFrame{

	public showBookInfo showbookinfo;
	public int conBookName = 0;
	public int conBookAuthor = 0;
	public int bookAuthorText = 0;
	String nameBook = "dima";
	String details[];
	public JFrame frameShowBookInfo;
	private JTextField textFieldTitle;
	private JTextField textFieldAuthor;
	private JTextField textFieldPrice;
    private JButton plotNewButton;
    private JButton tableOfContentsNewButton;
    private JComboBox comboBoxTitle;
    private int ConPlot = 0;
    private int ConTableOfContents = 0;
    public dbInfo info;
    public JTextField textFieldPDF;
    public JTextField textFieldDOC;
/**
 * @param info - client connection
 * The method reviewsConfirmation creates the GUI window of changing details on single book with the actions that librarian can make
 */
	public showBookInfo(dbInfo info){
		this.info = info;
		frameShowBookInfo = new JFrame();
		frameShowBookInfo.getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		frameShowBookInfo.setTitle("Change book details");
		
		frameShowBookInfo.setBounds(100, 100, 589, 131);
		frameShowBookInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameShowBookInfo.getContentPane().setLayout(null);
		
		JLabel lblChooseWhichBook = new JLabel("Choose which book you wanna to change details:");
		lblChooseWhichBook.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblChooseWhichBook.setBounds(6, 0, 479, 20);
		frameShowBookInfo.getContentPane().add(lblChooseWhichBook);
		
		JLabel lblBookTitle = new JLabel("Book title:");
		lblBookTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblBookTitle.setBounds(6, 77, 172, 20);
		frameShowBookInfo.getContentPane().add(lblBookTitle);
		lblBookTitle.setVisible(false);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldTitle.setBounds(5, 108, 558, 26);
		frameShowBookInfo.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		textFieldTitle.setVisible(false);
		
		JLabel labelBookAuthor = new JLabel("Book author:");
		labelBookAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelBookAuthor.setBounds(6, 137, 186, 26);
		frameShowBookInfo.getContentPane().add(labelBookAuthor);
		labelBookAuthor.setVisible(false);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldAuthor.setBounds(6, 164, 557, 26);
		frameShowBookInfo.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setVisible(false);
		
		JLabel labelBookPlot = new JLabel("Book plot:");
		labelBookPlot.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelBookPlot.setBounds(6, 198, 213, 20);
		frameShowBookInfo.getContentPane().add(labelBookPlot);
		labelBookPlot.setVisible(false);
		
		JLabel labelBookPrice = new JLabel("Book price:");
		labelBookPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelBookPrice.setBounds(6, 353, 276, 20);
		frameShowBookInfo.getContentPane().add(labelBookPrice);
		labelBookPrice.setVisible(false);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldPrice.setBounds(6, 384, 557, 26);
		frameShowBookInfo.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);
		textFieldPrice.setVisible(false);
		
		JLabel labelBookTableOfContents = new JLabel("Book table of contents:");
		labelBookTableOfContents.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelBookTableOfContents.setBounds(6, 421, 160, 20);
		frameShowBookInfo.getContentPane().add(labelBookTableOfContents);
		labelBookTableOfContents.setVisible(false);
		
		JLabel lblToUpdateDetails = new JLabel("To update details - ");
		lblToUpdateDetails.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblToUpdateDetails.setBounds(6, 52, 160, 20);
		frameShowBookInfo.getContentPane().add(lblToUpdateDetails);
		lblToUpdateDetails.setVisible(false);
		
		JLabel lblClickOnTheText = new JLabel("Click on the text:");
		lblClickOnTheText.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblClickOnTheText.setBounds(143, 52, 150, 20);
		frameShowBookInfo.getContentPane().add(lblClickOnTheText);
		lblClickOnTheText.setVisible(false);
		
		JButton btnExitUpdateBook = new JButton("Exit");
		btnExitUpdateBook.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		/**
		 * librarian press the button Exit
		 */
		btnExitUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameShowBookInfo.setVisible(false);
			}
		});
		btnExitUpdateBook.setBounds(384, 701, 179, 29);
		frameShowBookInfo.getContentPane().add(btnExitUpdateBook);
		
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
		
		ArrayList<String> bookAuthorDatabase = new ArrayList<String>();
		try {
			bookAuthorDatabase = (ArrayList<String>) getBookAuthorFromDB.getBookAuthorFromDB(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] bookAuthor = new String[bookAuthorDatabase.size()];
		for (int i = 0; i < bookAuthorDatabase.size(); i++) {
			bookAuthor[i] = bookAuthorDatabase.get(i);
		}
		
		JComboBox comboBoxTitle = new JComboBox();
		comboBoxTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		DefaultComboBoxModel model = new DefaultComboBoxModel(title);
		comboBoxTitle.setModel( model );
		comboBoxTitle.setRenderer(new MyComboBoxRenderer("Select book title:"));
		comboBoxTitle.setSelectedIndex(-1);
		comboBoxTitle.setToolTipText("");
		comboBoxTitle.setBounds(6, 22, 557, 26);
		frameShowBookInfo.getContentPane().add(comboBoxTitle);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(6, 229, 557, 113);
		scrollPane.setVisible(false);
		frameShowBookInfo.getContentPane().add(scrollPane);
		JTextArea textAreaPlot = new JTextArea();
		scrollPane.setViewportView(textAreaPlot);
		textAreaPlot.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textAreaPlot.setVisible(false);
		frameShowBookInfo.setVisible(false);
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(6, 452, 557, 147);
		frameShowBookInfo.getContentPane().add(scrollPane_1);
		JTextArea textAreaTableOfContents = new JTextArea();
		scrollPane_1.setViewportView(textAreaTableOfContents);
		textAreaTableOfContents.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textAreaTableOfContents.setVisible(false);
		scrollPane_1.setVisible(false);
		JButton buttonUpdateInformation = new JButton("Update Info");
		buttonUpdateInformation.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		buttonUpdateInformation.setBounds(6, 701, 172, 29);
		frameShowBookInfo.getContentPane().add(buttonUpdateInformation);
		buttonUpdateInformation.setVisible(false);
		
		JButton deleteBtnButton = new JButton("Delete Book");
		deleteBtnButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		deleteBtnButton.setBounds(188, 701, 186, 29);
		frameShowBookInfo.getContentPane().add(deleteBtnButton);
				  
		deleteBtnButton.setVisible(false);
		
		JScrollPane scrollPanePDF = new JScrollPane((Component) null);
		scrollPanePDF.setBounds(97, 610, 466, 29);
		frameShowBookInfo.getContentPane().add(scrollPanePDF);
		scrollPanePDF.setVisible(false);
		textFieldPDF = new JTextField();
		scrollPanePDF.setViewportView(textFieldPDF);
		textFieldPDF.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldPDF.setColumns(10);
		textFieldPDF.setVisible(false);
		JLabel lblBookUrlPdf = new JLabel("Book url pdf:");
		lblBookUrlPdf.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblBookUrlPdf.setBounds(6, 610, 108, 26);
		frameShowBookInfo.getContentPane().add(lblBookUrlPdf);
		lblBookUrlPdf.setVisible(false);
		JLabel lblBookUrlDoc = new JLabel("Book url doc:");
		lblBookUrlDoc.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblBookUrlDoc.setBounds(6, 647, 108, 26);
		frameShowBookInfo.getContentPane().add(lblBookUrlDoc);
		lblBookUrlDoc.setVisible(false);
		JScrollPane scrollPaneDOC = new JScrollPane((Component) null);
		scrollPaneDOC.setBounds(97, 644, 466, 29);
		scrollPaneDOC.setVisible(false);
		frameShowBookInfo.getContentPane().add(scrollPaneDOC);
		
		textFieldDOC = new JTextField();
		scrollPaneDOC.setViewportView(textFieldDOC);
		textFieldDOC.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textFieldDOC.setColumns(10);
		textFieldDOC.setVisible(false);
		
		comboBoxTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameShowBookInfo.setBounds(100, 100, 589, 862);
				//frameShowBookInfo.setBounds(100, 100, 589, 896);
				scrollPanePDF.setVisible(true);
				textFieldPDF.setVisible(true);
				lblBookUrlPdf.setVisible(true);
				lblBookUrlDoc.setVisible(true);
				scrollPaneDOC.setVisible(true);
				lblBookTitle.setVisible(true);
				textFieldDOC.setVisible(true);
				textFieldTitle.setVisible(true);
				labelBookAuthor.setVisible(true);
				textFieldTitle.setVisible(true);
				labelBookPlot.setVisible(true);
				labelBookPrice.setVisible(true);
				textFieldPrice.setVisible(true);
				labelBookTableOfContents.setVisible(true);
				lblToUpdateDetails.setVisible(true);
				lblClickOnTheText.setVisible(true);
				textFieldAuthor.setVisible(true);
				frameShowBookInfo.setVisible(true);
				textAreaPlot.setVisible(true);
				textAreaTableOfContents.setVisible(true);
				buttonUpdateInformation.setVisible(true);
				scrollPane_1.setVisible(true);
				scrollPane.setVisible(true);
				deleteBtnButton.setVisible(true);
				nameBook = comboBoxTitle.getSelectedItem().toString();
				  ArrayList<String> detailsDatabase = new ArrayList<String>();
				  try {
						detailsDatabase = (ArrayList<String>) getDetailsOfBookFromDB.getDetailsOfBookFromDB(nameBook,info);
				  } catch (Exception e1) {
						e1.printStackTrace();
				  }
				  details = new String[detailsDatabase.size()];
				  for (int i = 0; i < detailsDatabase.size(); i++) {
						details[i] = detailsDatabase.get(i);
				  }
								   
					textFieldTitle.setText(details[1]);
					textFieldAuthor.setText(details[0]);
					textFieldPrice.setText(details[3]);
					textAreaPlot.setText(details[2]);
					textAreaTableOfContents.setText(details[4]);
					textFieldPDF.setText(details[5]);
					textFieldDOC.setText(details[6]);
					
			}
		});
		/**
		 * librarian press the button delete book
		 */
		deleteBtnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteBookFromDB.deleteBookFromDB(nameBook,info);
					JOptionPane.showMessageDialog(null, "You delete the book " + nameBook);
					frameShowBookInfo.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		for (int i = 0; i < title.length; i++) 
			if (title[i].equals(textFieldTitle.getText()))
				conBookName = 1;
		/**
		 * librarian press the button Update Info
		 */
		buttonUpdateInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(conBookName == 0){
						try {
							editInfoBook.editInfoBook(textFieldAuthor.getText(), textFieldTitle.getText(), textAreaPlot.getText(), 
									textFieldPrice.getText(), textAreaTableOfContents.getText(),textFieldPDF.getText(),textFieldDOC.getText(),nameBook,info);
							JOptionPane.showMessageDialog(null, "You updated the book " + textFieldTitle.getText());
							frameShowBookInfo.dispose();
							showbookinfo = new showBookInfo(info);
							showbookinfo.frameShowBookInfo.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		        }
				else
				     JOptionPane.showMessageDialog(null, "The book title exist\nPlease edit another title");
				conBookName = 0;
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
	
	public static void main(String[] args){
	}
	
	
}
