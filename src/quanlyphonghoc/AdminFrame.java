/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminFrame extends JFrame {

    private JLabel helloLabel, titleLabel;
    private JButton logoutButton;
    private JButton phongHocButton, giaoVienButton, dungCuButton, hoaDonButton;
    private String username;
    private JTable table;
    private JScrollPane scrollPane;

    public AdminFrame(String username) {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 800, 600);
        Container container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents(container);
        setActions();
        populateTable();
        setVisible(true);
        this.username = username;

        helloLabel.setText("Hello " + username);
    }

    private void setBounds() {
        helloLabel = new JLabel();
        helloLabel.setBounds(20, 10, 200, 30);

        logoutButton = new JButton("Đăng xuất");
        logoutButton.setBounds(650, 10, 100, 30);

        titleLabel = new JLabel("Trang chủ Admin");
        titleLabel.setBounds(320, 60, 200, 30);
        
        phongHocButton = new JButton("Quản lý phòng học");
        phongHocButton.setBounds(150, 150, 150, 50);

        giaoVienButton = new JButton("Quản lý giáo viên");
        giaoVienButton.setBounds(400, 150, 150, 50);

        dungCuButton = new JButton("Quản lý dụng cụ");
        dungCuButton.setBounds(150, 250, 150, 50);

        hoaDonButton = new JButton("Quản lý hóa đơn");
        hoaDonButton.setBounds(400, 250, 150, 50);
        
        table = new JTable(new DefaultTableModel(new Object[]{"MaUser", "Noidung", "ThoiGian"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 320, 700, 200);
    }

    private void addComponents(Container container) {
        container.add(helloLabel);
        container.add(logoutButton);
        container.add(titleLabel);
        container.add(phongHocButton);
        container.add(giaoVienButton);
        container.add(dungCuButton);
        container.add(hoaDonButton);
        container.add(scrollPane);
    }

    private void setActions() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminFrame.this, "Đăng xuất!");
                new Login().setVisible(true);
            }
        });

        phongHocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminFrame.this, "Chào mừng đến quản lý phòng học");
                new ClassroomFrame(username).setVisible(true);
            }
        });

        giaoVienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminFrame.this, "Chào mừng đến quản lý giáo viên");
                new TeacherFrame(username).setVisible(true);
            }
        });

        dungCuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminFrame.this, "Chào mừng đến quản lý dụng cụ");
                new EquipmentFrame(username).setVisible(true);
            }
        });

        hoaDonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Quản lý hóa đơn" button action here
                // Open the corresponding management window or perform an action
            }
        });
    }
     private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = adminDAO.selectAllLogs();

        if (rs != null) {
            try {
                while (rs.next()) {
                    String maUser = rs.getString("MaUser");
                    String noidung = rs.getString("Noidung");
                    Date thoiGian = rs.getTimestamp("ThoiGian");

                    model.addRow(new Object[]{maUser, noidung, thoiGian});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
           SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
