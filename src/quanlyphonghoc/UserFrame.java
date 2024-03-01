/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {

    private JLabel helloLabel;
    private JButton practiceButton, classButton,logoutButton ;
    private String username;

    public UserFrame(String username) {
        setTitle("User Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 500, 400);
        Container container = getContentPane();
        container.setLayout(null);
        this.username = username;
        setBound();
        addComponents(container, username);
        setVisible(true);
    }

 public void setBound() {
        helloLabel = new JLabel();
        helloLabel.setBounds(10, 10, 150, 30);

        practiceButton = new JButton("Quản lý thực hành");
        practiceButton.setBounds(50, 150, 150, 30);

        classButton = new JButton("Xếp Lớp");
        classButton.setBounds(220, 150, 100, 30);

        logoutButton = new JButton("Đăng xuất");
        logoutButton.setBounds(350, 10, 100, 30);
    }

    public void addComponents(Container container, String username) {
        helloLabel.setText("Hello, " + username);

        container.add(helloLabel);
        container.add(practiceButton);
        container.add(classButton);
        container.add(logoutButton);

        practiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UserFrame.this, "Chào mừng đến quản lý thực hành");
                new PraticeJFrame(username).setVisible(true);
            }
        });

        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UserFrame.this, "chào mừng đến xếp Lớp");
                new ClassFrame(username).setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logout logic here
                // For example, return to login screen or perform other actions
                JOptionPane.showMessageDialog(UserFrame.this, "Đăng xuất clicked!");
                new Login().setVisible(true);
                dispose(); // Close the UserFrame
            }
        });
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserFrame("User123");
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
