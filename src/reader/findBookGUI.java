package reader;

import common.MyComboBoxRenderer;
import common.dbInfo;
import common.initConnection;
import common.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * find book progress
 *
 * @author Tomer
 */
public class findBookGUI extends JFrame {

    public JFrame frmfindBook;
    private JTextField titleField;
    private JLabel lblsearchByAuthor;
    private JLabel lblsearchByLanguage;
    private JLabel lblsearchBySummary;
    private JLabel lblsearchByContents;
    private JLabel lblsearchByScope;
    private JLabel lblsearchBySubject;
    private JTextField languageField;
    private JTextField summaryField;
    private JTextField contentsField;
    private JLabel lblsearchByKeywords;
    private JTextField authorField;
    private JTextField keywordsField;
    private initConnection con;
    private PreparedStatement statement;
    private ResultSet result;
    private JTextArea fromServer;
    /**
     * building query
     */
    public String search = "SELECT DISTINCT book.bookID , book.author ,book.title , book.plot , book.price , book.Table_of_Contents,book.author2,book.language"
            + " FROM book ,bookscope ,scope,subjectscope";
    /**
     * temp query
     */
    public String tempSearch;
    /**
     * counting number of books in order
     */
    public static int numOfBooks = 0;
    /**
     * array of books in order
     */
    public static String[] booksOrder;
    public static String[] booksNamesOrder;
    /**
     * flag for checking if found any results
     */
    private int resultsFlag;
    private JTextField authorField2;
    public dbInfo info;
    private int flag;
    private int searchClicked;

    public findBookGUI(dbInfo info) {

        try {
            this.con = new initConnection(info);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.info = info;

        frmfindBook = new JFrame();
        frmfindBook.setTitle("Find Book");
        frmfindBook.setBounds(100, 100, 754, 582);
        frmfindBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmfindBook.getContentPane().setLayout(null);
        frmfindBook.setVisible(true);

        titleField = new JTextField();
        titleField.setBounds(195, 24, 150, 20);
        frmfindBook.getContentPane().add(titleField);
        titleField.setColumns(10);

        JLabel lblTypeBooksName = new JLabel("search by title");
        lblTypeBooksName.setBounds(25, 27, 150, 14);
        frmfindBook.getContentPane().add(lblTypeBooksName);

        lblsearchByAuthor = new JLabel("search by author");
        lblsearchByAuthor.setBounds(25, 52, 150, 20);
        frmfindBook.getContentPane().add(lblsearchByAuthor);

        lblsearchByLanguage = new JLabel("search by language");
        lblsearchByLanguage.setBounds(25, 83, 150, 14);
        frmfindBook.getContentPane().add(lblsearchByLanguage);

        lblsearchBySummary = new JLabel("search by plot");
        lblsearchBySummary.setBounds(25, 108, 150, 14);
        frmfindBook.getContentPane().add(lblsearchBySummary);

        lblsearchByContents = new JLabel("search by contents");
        lblsearchByContents.setBounds(25, 133, 150, 14);
        frmfindBook.getContentPane().add(lblsearchByContents);

        lblsearchByScope = new JLabel("search by scope");
        lblsearchByScope.setBounds(25, 158, 150, 14);
        frmfindBook.getContentPane().add(lblsearchByScope);

        lblsearchBySubject = new JLabel("search by Subject");
        lblsearchBySubject.setBounds(25, 183, 150, 14);
        frmfindBook.getContentPane().add(lblsearchBySubject);

        languageField = new JTextField();
        languageField.setBounds(195, 80, 150, 20);
        frmfindBook.getContentPane().add(languageField);
        languageField.setColumns(10);

        summaryField = new JTextField();
        summaryField.setBounds(195, 105, 150, 20);
        frmfindBook.getContentPane().add(summaryField);
        summaryField.setColumns(10);

        contentsField = new JTextField();
        contentsField.setBounds(195, 130, 150, 20);
        frmfindBook.getContentPane().add(contentsField);
        contentsField.setColumns(10);

        lblsearchByKeywords = new JLabel("search by keywords");
        lblsearchByKeywords.setBounds(25, 211, 150, 14);
        frmfindBook.getContentPane().add(lblsearchByKeywords);

        authorField = new JTextField();
        authorField.setBounds(195, 55, 150, 20);
        frmfindBook.getContentPane().add(authorField);
        authorField.setColumns(10);


        ArrayList<String> subject = getSubjectFromDB("SELECT * FROM subjectscope");
        String[] sub = new String[subject.size()];
        for (int i = 0; i < subject.size(); i++)
            sub[i] = subject.get(i);
        try {
            this.con = new initConnection(info);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ArrayList<String> scope = getScopeFromDB("SELECT * FROM scope");
        String[] sco = new String[scope.size()];
        for (int i = 0; i < scope.size(); i++)
            sco[i] = scope.get(i);

        JComboBox scopeCombo = new JComboBox(sco);
        scopeCombo.setBounds(195, 155, 150, 20);
        scopeCombo.setRenderer(new MyComboBoxRenderer("Select scope"));
        scopeCombo.setSelectedIndex(-1);
        frmfindBook.getContentPane().add(scopeCombo);

        JComboBox subjectCombo = new JComboBox(sub);
        subjectCombo.setBounds(195, 183, 150, 20);
        subjectCombo.setRenderer(new MyComboBoxRenderer("Select subject"));
        subjectCombo.setSelectedIndex(-1);
        frmfindBook.getContentPane().add(subjectCombo);

        JComboBox resultsCombo = new JComboBox();
        resultsCombo.setBounds(109, 249, 273, 20);
        resultsCombo.setRenderer(new MyComboBoxRenderer("Results:"));
        resultsCombo.setSelectedIndex(-1);
        frmfindBook.getContentPane().add(resultsCombo);

        keywordsField = new JTextField();
        keywordsField.setBounds(195, 208, 150, 20);
        frmfindBook.getContentPane().add(keywordsField);
        keywordsField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(35, 280, 557, 252);
        frmfindBook.getContentPane().add(scrollPane);
        fromServer = new JTextArea();
        scrollPane.setViewportView(fromServer);
        fromServer.setEditable(false);

        tempSearch = search;
        /**
         * build query to find books
         * connects strings from text fields to build query
         */
        JButton btnsearch = new JButton("search");
        btnsearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                searchClicked = 1;
                try {
                    flag = 0;
                    search = tempSearch;
                    con = new initConnection(info);
                    fromServer.setText("");

                    search += " Where ";
                    if (titleField.getText().toString().isEmpty() == false) {
                        search = search + " book.title = '" + titleField.getText().toString() + "'";
                        flag = 1;
                    }
                    if (authorField.getText().toString().isEmpty() == false) {
                        if (flag == 1)
                            search = search + " And";
                        search += " book.author = '" + authorField.getText().toString() + "'";
                        flag = 1;
                    }
                    if (authorField2.getText().toString().isEmpty() == false) {
                        if (flag == 1 && !authorField.getText().toString().equals(authorField2.getText().toString()))
                            search = search + " And";
                        if (authorField.getText().toString().isEmpty() == false)
                            search += " book.author = '" + authorField2.getText().toString() + "'";
                        else {
                            if (!authorField.getText().toString().equals(authorField2.getText().toString()))
                                search += " book.author2 = '" + authorField2.getText().toString() + "'";
                        }
                        flag = 1;
                    }

                    if (authorField.getText().toString().isEmpty() == false && authorField2.getText().toString().isEmpty() == false
                            & !authorField.getText().toString().equals(authorField2.getText().toString())) {
                        search += " OR (";

                        search += " book.author = '" + authorField2.getText().toString() + "'";
                        search += " AND (";
                        search += " book.author2 = '" + authorField.getText().toString() + "'";

                        search += ")";
                        flag = 1;

                    }
                    if (languageField.getText().toString().isEmpty() == false) {
                        if (flag == 1)
                            search = search + " And";
                        search = search + " book.language = '" + languageField.getText().toString() + "'";
                        flag = 1;
                    }
                    if (scopeCombo.getSelectedItem() != null) {
                        if (flag == 1)
                            search += " AND ";
                        search += "bookscope.ScopeName = '" + scopeCombo.getSelectedItem().toString() + "'";
                        search += "AND bookscope.bookTitle=book.title";

                        flag = 1;
                    }

                    if (subjectCombo.getSelectedItem() != null) {
                        if (flag == 1)
                            search += " AND ";
                        search += " bookscope.subjectName = '" + subjectCombo.getSelectedItem().toString() + "'";
                        search += "AND bookscope.bookTitle=book.title";
                        flag = 1;
                    }


                    if (summaryField.getText().toString().isEmpty() == false) {
                        if (flag == 1)
                            search = search + " AND";
                        search += " book.plot LIKE '%" + summaryField.getText().toString() + "%'";
                        flag = 1;
                    }

                    if (contentsField.getText().toString().isEmpty() == false) {
                        if (flag == 1)
                            search = search + " AND";
                        search += " book.Table_of_Contents LIKE '%" + contentsField.getText().toString() + "%'";
                        flag = 1;
                    }

                    if (keywordsField.getText().toString().isEmpty() == false) {
                        if (flag == 1)
                            search = search + " AND";
                        search += " book.title LIKE '%" + keywordsField.getText().toString() + "%'";
                        flag = 1;
                    }


                    if (flag == 1) {
                        search += " AND ";
                        search += "book.status='" + "1" + "'";
                        ArrayList<String> str = getDataFromDB(search);
                        try {
                            con = new initConnection(info);
                            insertIntoDB("UPDATE ibook.book SET numberOfSearches=numberOfSearches+1 WHERE title='" + titleField.getText().toString() + "'");

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        try {
                            con = new initConnection(info);

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        ArrayList<String> results = getTitleFromDB(search);
                        int resSize = results.size();
                        String[] res = new String[resSize + 1];
                        res[0] = "Choose a book";
                        for (int i = 1; i < results.size() + 1; i++)
                            res[i] = results.get(i - 1);

                        DefaultComboBoxModel model = new DefaultComboBoxModel(res);
                        resultsCombo.setModel(model);
                        frmfindBook.getContentPane().add(resultsCombo);


                        String[] order = new String[str.size()];
                        if (str.size() == 0) {
                            JOptionPane.showMessageDialog(frmfindBook, "0 results", "Message", JOptionPane.PLAIN_MESSAGE);
                            titleField.setText("");
                            authorField.setText("");
                            authorField2.setText("");
                            languageField.setText("");
                            summaryField.setText("");
                            contentsField.setText("");
                            languageField.setText("");
                            keywordsField.setText("");
                            authorField.setText("");
                            scopeCombo.setRenderer(new MyComboBoxRenderer("Select scope"));
                            scopeCombo.setSelectedIndex(-1);

                            subjectCombo.setRenderer(new MyComboBoxRenderer("Select subject"));
                            subjectCombo.setSelectedIndex(-1);

                            resultsCombo.setRenderer(new MyComboBoxRenderer("Results:"));
                            resultsCombo.setSelectedIndex(-1);

                        } else if (str.size() == 1 && titleField.getText().toString().isEmpty() == false) {
                            try {
                                con = new initConnection(info);
                                Date dNow = new Date();
                                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                String date = ft.format(dNow);
                                addSearchesToDB(titleField.getText().toString(), date);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                        for (int i = 0; i < str.size(); i++) {
                            order[i] = str.get(i);
                            fromServer.append(i + 1 + ". " + order[i] + " \n");
                        }
                        resultsFlag = 1;
                    } else {
                        scopeCombo.setRenderer(new MyComboBoxRenderer("Select scope"));
                        scopeCombo.setSelectedIndex(-1);

                        subjectCombo.setRenderer(new MyComboBoxRenderer("Select subject"));
                        subjectCombo.setSelectedIndex(-1);

                        resultsCombo.setRenderer(new MyComboBoxRenderer("Results:"));
                        resultsCombo.setSelectedIndex(-1);
                        JOptionPane.showMessageDialog(frmfindBook, "You must fill at least 1 field", "Message", JOptionPane.PLAIN_MESSAGE);
                        resultsFlag = 0;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (flag == 0)
                    JOptionPane.showMessageDialog(null, "You must search for a book");

            }
        });
        btnsearch.setBounds(10, 248, 89, 23);
        frmfindBook.getContentPane().add(btnsearch);

        /**
         * goes to books options
         */
        JButton btnCheckBookOptions = new JButton("Check Book options");
        btnCheckBookOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    con = new initConnection(info);
                    insertIntoDB("UPDATE ibook.book SET numberOfSearches=numberOfSearches+1 WHERE title='" + titleField.getText().toString() + "'");

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (resultsFlag == 1 && !resultsCombo.getSelectedItem().toString().equals("Choose a book")) {

                    try {
                        con = new initConnection(info);
                        ArrayList<String> price = getPriceFromDB("SELECT book.price from ibook.book WHERE book.title= '" + resultsCombo.getSelectedItem().toString() + "'");


                        String priceStr = price.get(0);
                        Float f = new Float("20.75f");
                        float priceFloat = f.parseFloat(priceStr);
                        try {
                            con = new initConnection(info);

                            ArrayList<String> discount = getDiscountFromDB("SELECT users.ibookstatus from ibook.users WHERE users.userName= '" + user.userName + "'");

                            System.out.println(discount.size());
                            String discountStr = discount.get(0);
                            int discountInt = Integer.parseInt(discountStr);
                            if (discountInt == 2) {
                                priceFloat = (priceFloat * 9) / 10;
                            }
                            if (discountInt == 3) {
                                priceFloat = (priceFloat * 8) / 10;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                            // TODO: handle exception
                        }
                        if (numOfBooks == 0) {
                            booksOrder = new String[100];
                            booksNamesOrder = new String[100];
                        }
                        int notTwice = 1;
                        for (int i = 0; i < booksOrder.length; i++) {
                            if (resultsCombo.getSelectedItem().toString().equals(booksNamesOrder[i])) {
                                JOptionPane.showMessageDialog(null, "This book is already ordered.");
                                notTwice = 0;
                            }
                        }
                        if (notTwice == 1) {
                            frmfindBook.dispose();
                            booksNamesOrder[numOfBooks] = resultsCombo.getSelectedItem().toString();
                            booksOrder[numOfBooks] = "Book name= " + resultsCombo.getSelectedItem().toString();
                            specificBookMenuGUI spec = new specificBookMenuGUI(info, resultsCombo.getSelectedItem().toString(), priceFloat);
                        }
                        searchClicked = 0;
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                } else if (searchClicked == 0) {
                    JOptionPane.showMessageDialog(null, "You must click search first");

                } else {
                    if (searchClicked == 0)
                        JOptionPane.showMessageDialog(null, "You must choose a book for search");
                }
            }
        });
        btnCheckBookOptions.setBounds(392, 248, 200, 23);
        frmfindBook.getContentPane().add(btnCheckBookOptions);

        authorField2 = new JTextField();
        authorField2.setBounds(362, 55, 150, 20);
        frmfindBook.getContentPane().add(authorField2);
        authorField2.setColumns(10);

    }

    /**
     * get the results of founded books
     *
     * @param s=query
     * @return
     */
    private ArrayList<String> getDataFromDB(String s) {

        ArrayList<String> users = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next()) {
                if (result.getString(7).toString().isEmpty() == false)
                    users.add("Book name:" + result.getString(3) + " ,author1: " + result.getString(2) + " ,author2: " + result.getString(7) + " ,language: " + result.getString(8) + ",price: " + result.getString(5));
                else
                    users.add("Book name:" + result.getString(3) + " ,author1: " + result.getString(2) + " ,language: " + result.getString(8) + ",price: " + result.getString(5));
            }

            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return users;
    }

    /**
     * get all subjects from database
     *
     * @param s=query
     * @return
     */
    private ArrayList<String> getSubjectFromDB(String s) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next())
                data.add(result.getString(2));
            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return data;
    }

    /**
     * get all scopes from database
     *
     * @param s=query
     * @return
     */
    private ArrayList<String> getScopeFromDB(String s) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next())
                data.add(result.getString(1));

            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return data;
    }

    /**
     * update num of searches - increasing by 1
     *
     * @param Query
     * @throws SQLException
     */
    public void insertIntoDB(String Query) throws SQLException {
        PreparedStatement statement = (PreparedStatement) con.connect.prepareStatement(Query);
        statement.executeUpdate();
        statement.close();
        con.connect.close();
    }

    /**
     * get books title from database
     *
     * @param s=query
     * @return
     */
    private ArrayList<String> getTitleFromDB(String s) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next())
                data.add(result.getString(3));
            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return data;
    }

    /**
     * check if user deserves a discount during purchase
     *
     * @param s
     * @return
     */
    private ArrayList<String> getDiscountFromDB(String s) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next())
                data.add(result.getString(1));
            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return data;
    }

    /**
     * get books price from database
     *
     * @param s = query
     * @return
     */
    private ArrayList<String> getPriceFromDB(String s) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            statement = con.connect.prepareStatement(s);
            result = statement.executeQuery();
            while (result.next())
                data.add(result.getString(1));
            result.close();
            statement.close();
            con.connect.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return data;
    }

    /**
     * add date to database when specific book is searched by users
     *
     * @param name of book
     * @param date
     */
    public void addSearchesToDB(String name, String date) {
        try {
            con = new initConnection(info);
            String insertSql = "INSERT INTO ibook.search VALUES(?,?)";
            PreparedStatement statement = (PreparedStatement) con.connect.prepareStatement(insertSql);
            statement.setString(1, name);
            statement.setString(2, date);
            statement.executeUpdate();
            statement.close();
            con.connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
