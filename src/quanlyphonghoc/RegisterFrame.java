/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegisterFrame extends JFrame {

    private JLabel usernameLabel, passwordLabel, messageLabel, reconfirmPasswordLabel;
    private JTextField usernameInput, reconfirmPasswordInput;
    private JPasswordField passwordInput;
    private JButton registerButton;
    private Container container;

    public RegisterFrame() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 500, 350);

        container = getContentPane();
        container.setLayout(null);

        setBound();
        addComponents();

        setVisible(true);
    }

   public void setBound() {
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 10, 600, 30);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);

        usernameInput = new JTextField();
        usernameInput.setBounds(150, 50, 100, 30);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);

        passwordInput = new JPasswordField();
        passwordInput.setBounds(150, 100, 100, 30);

        reconfirmPasswordLabel = new JLabel("Reconfirm Password:");
        reconfirmPasswordLabel.setBounds(50, 150, 140, 30);

        reconfirmPasswordInput = new JTextField();
        reconfirmPasswordInput.setBounds(190, 150, 100, 30);

        registerButton = new JButton("Register");
        registerButton.setBounds(50, 200, 100, 30);
    }

    public void addComponents() {
        container.add(messageLabel);
        container.add(usernameLabel);
        container.add(usernameInput);
        container.add(passwordLabel);
        container.add(passwordInput);
        container.add(reconfirmPasswordLabel);
        container.add(reconfirmPasswordInput);
        container.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameInput.getText();
            char[] passwordChars = passwordInput.getPassword();
            String password = new String(passwordChars);
            String confirmPassword = reconfirmPasswordInput.getText();

            if (!password.equals(confirmPassword)) {
                messageLabel.setText("Password does not match");
                return;
            }

            AccountDao accountDao = new AccountDao();
            int count = accountDao.countTaiKhoan();
            String Mauser = "us" + count;
            TaiKhoan taiKhoan = new TaiKhoan(Mauser, username, password);
            taiKhoan.setLaoiTK("us");

             // You should replace this with the appropriate way of creating an AccountDao instance

            boolean success = accountDao.insertTaiKhoan(taiKhoan);

            if (success) {
                messageLabel.setText("Register completed");
                new Login().setVisible(true);
                dispose();
            } else {
                messageLabel.setText("User existed");
            }
        }
       });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RegisterFrame frame = new RegisterFrame();
                frame.setTitle("Student Register Form");
                frame.setBounds(500, 100, 500, 350);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
            }
        });
    }
}
