/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    JLabel usernamelabel, passwordlabel;
    JTextField usernameTextField;
    JPasswordField passwordField;
    JButton loginButton;
    Container container;
    /**
     * Creates new form LoginFrame
     */
    public Login() {
        usernamelabel = new JLabel("Username");
        usernameTextField = new JTextField();
        passwordlabel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new JButton("login");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListener();
    }
    public void setBounds()
    {
        usernamelabel.setBounds(10,10,100,30);
        usernameTextField.setBounds(100,10,200,30);
        passwordlabel.setBounds(10,50,100,30);
        passwordField.setBounds(100,50,200,30);
        loginButton.setBounds(100,100,200,30);
    }
    public void addComponents()
    {
       container.add(usernamelabel);
       container.add(usernameTextField);
       container.add(passwordlabel);
       container.add(passwordField);
       container.add(loginButton);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void addActionListener() {
        loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("event called");
            if (e.getSource() == loginButton) {
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                System.out.println(username + " " + password);

        AccountDao accountDao = new AccountDao();
        String kq = accountDao.checkLogin(username, password);

        if (kq!=null) {
            if (kq.equals("ad")) {
                System.out.println("Logged in as admin");
                new AdminFrame(username).setVisible(true);
                dispose();
            } else {
                System.out.println("Logged in as user");
                new UserFrame(username).setVisible(true);
                dispose();
            }
        } else {
            System.out.println("Unable to login");
            JOptionPane.showMessageDialog(null, "User ID or password is wrong");
            return;
        }
    }
}
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login frame = new Login();
                frame.setTitle("Login Form");
                frame.setVisible(true);
                frame.setBounds(250, 250, 370, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

