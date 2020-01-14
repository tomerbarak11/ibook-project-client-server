package common;

import Librarian.librarianGUI;
import manager.managerGUI;
import reader.readerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class responsible about the log in to the system
 *
 * @author Team 11 - Dima Spektor,Hadi Maaruf,Nadav Rosenfeld,Tomer Barak
 */
public class LogInGui extends JFrame {

    private static final long serialVersionUID = 1L;
    public dbInfo info;


    private JPanel contentPane; // the panel
    private JTextField usrID;
    private JPasswordField pass;
    private JButton signin;
    public JLabel wronglbl;
    private String password, id;
    public initConnection con;

    /**
     * After we connected to the server we use the log in GUI
     *
     * @param info get the info from the dbinfo class
     */
    public LogInGui(dbInfo info) {
        this.info = info;
		/*
		 Create the only  frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 435, 273);
        setTitle("LogIn");
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
		
		/*
		 Add the userID label*/
        JLabel UserID = new JLabel("User ID:");
        UserID.setForeground(Color.BLACK);
        UserID.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        UserID.setBounds(10, 22, 91, 26);
        contentPane.add(UserID);
		
		/*
		 Add the userID  text field */
        usrID = new JTextField();
        usrID.setForeground(Color.BLACK);
        usrID.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
        usrID.setBounds(154, 26, 124, 20);
        contentPane.add(usrID);
		
		/*
		 Add the password label*/
        JLabel UserPass = new JLabel("Password: ");
        UserPass.setForeground(Color.BLACK);
        UserPass.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        UserPass.setBounds(10, 64, 80, 26);
        contentPane.add(UserPass);
		
		/*
		 Add the Password text field */
        pass = new JPasswordField();
        pass.setForeground(Color.BLACK);
        pass.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
        pass.setBounds(154, 68, 124, 20);
        contentPane.add(pass);
		
		/*
		 Add the wrong label */
        wronglbl = new JLabel("*One or more inputs are wrong");
        wronglbl.setForeground(Color.RED);
        wronglbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
        wronglbl.setBounds(112, 113, 200, 20);
        wronglbl.setVisible(false);
        contentPane.add(wronglbl);
        LogInGui lgin = this;
		/*
		 Add the login button text field */
        signin = new JButton("Log In");
        signin.setForeground(Color.BLACK);
        signin.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
        signin.setBounds(10, 113, 80, 20);
        signin.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
            /**
             * event when the log in button pressed, search in the data base user and password
             * make validation and connect to the right user by the his access level
             *
             */
            public void actionPerformed(ActionEvent arg0) {

                id = usrID.getText();
                password = new String(pass.getPassword());
                try {
                    con = new initConnection(info);
                    if (id.isEmpty() && password.isEmpty())
                        JOptionPane.showMessageDialog(null, "userID and Password is empty");
                    else if (id.isEmpty())
                        JOptionPane.showMessageDialog(null, "UserID is empty");
                    else if (password.isEmpty())
                        JOptionPane.showMessageDialog(null, "Password is empty");
                    else {
                        Statement myStmt = (Statement) con.connect.createStatement();
                        ResultSet myRs = myStmt.executeQuery("Select userName,userID,password,accessLevel,ibookStatus,loginStatus FROM users WHERE userID='" + id + "' AND password='" + password + "';");

                        myRs.next();
                        /**
                         * check if user already logged in
                         */
                        if (myRs.getInt("loginStatus") == 1)
                            throw new Exception();
                        //access level: 1=reader 2=librarian 3=manager 4=wait for confirmation
                        /**
                         * access level: 1=reader 2=librarian 3=manager 4=wait for confirmation
                         */
                        int access_level = myRs.getInt("accessLevel");
                        switch (access_level) {
                            /**
                             * connect to the reader menu
                             */
                            case 1: {
                                new user(myRs.getInt("userID"), myRs.getString("userName"), myRs.getInt("accessLevel"), myRs.getInt("ibookStatus"));
                                int userID = user.userID;
                                myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '1' WHERE `users`.`userID` = '" + userID + "';");
                                myRs.close();
                                myStmt.close();
                                con.closeConnection();
                                readerGUI readerGUI = new readerGUI(info);
                                dispose();
                                readerGUI.frame.setVisible(true);
                                break;
                            }
                            /**
                             * connect to the librarian menu
                             */
                            case 2: {
                                new user(myRs.getInt("userID"), myRs.getString("userName"), myRs.getInt("accessLevel"), myRs.getInt("ibookStatus"));
                                int userID = user.userID;
                                myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '1' WHERE `users`.`userID` = '" + userID + "';");
                                myRs.close();
                                myStmt.close();
                                con.closeConnection();
                                librarianGUI librarianGUI = new librarianGUI(info);
                                dispose();
                                librarianGUI.frame.setVisible(true);
                                break;
                            }
                            /**
                             * connect to the manager menu
                             */
                            case 3: {
                                new user(myRs.getInt("userID"), myRs.getString("userName"), myRs.getInt("accessLevel"), myRs.getInt("ibookStatus"));
                                int userID = user.userID;
                                myStmt.executeUpdate("UPDATE `users` SET `loginStatus` = '1' WHERE `users`.`userID` = '" + userID + "';");
                                myRs.close();
                                myStmt.close();
                                con.closeConnection();
                                managerGUI managerGUI = new managerGUI(info);
                                dispose();
                                managerGUI.frame.setVisible(true);
                                break;
                            }

                        }
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "Error login,user or password doesn't exist");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "User is already log in");
                }


            }


        });

        contentPane.add(signin);


    }
}
