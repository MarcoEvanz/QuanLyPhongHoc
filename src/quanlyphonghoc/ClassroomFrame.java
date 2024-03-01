/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomFrame extends JFrame {

    private JLabel maPhongLabel, tenPhongLabel, sucChuaLabel;
    private JTextField maPhongInput, tenPhongInput, sucChuaInput;
    private JButton addButton, deleteButton, updateButton;
    private JTable table;
    private JScrollPane scrollPane;
    private String username;

    public ClassroomFrame(String username) {
        setTitle("Classroom Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 600, 400);
        Container container = getContentPane();
        container.setLayout(null);
        this.username = username;

        maPhongLabel = new JLabel("Mã Phòng:");
        tenPhongLabel = new JLabel("Tên Phòng:");
        sucChuaLabel = new JLabel("Sức Chứa:");

        maPhongInput = new JTextField();
        tenPhongInput = new JTextField();
        sucChuaInput = new JTextField();

        addButton = new JButton("Thêm");
        deleteButton = new JButton("Xóa");
        updateButton = new JButton("Sửa");


        setBounds();
        addComponents(container, username);
        setActions(username);

        populateTable();

        setVisible(true);
    }

    private void setBounds() {
        maPhongLabel.setBounds(50, 20, 100, 30);
        maPhongInput.setBounds(150, 20, 150, 30);
        tenPhongLabel.setBounds(50, 70, 100, 30);
        tenPhongInput.setBounds(150, 70, 150, 30);
        sucChuaLabel.setBounds(50, 120, 100, 30);
        sucChuaInput.setBounds(150, 120, 150, 30);

        addButton.setBounds(50, 170, 80, 30);
        deleteButton.setBounds(150, 170, 80, 30);
        updateButton.setBounds(250, 170, 80, 30);

        table = new JTable(new DefaultTableModel(new Object[]{"MaPh", "TenPh", "Succhua"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 450, 200);
        
    }

    private void addComponents(Container container, String username) {
        container.add(scrollPane);
        container.add(maPhongLabel);
        container.add(maPhongInput);
        container.add(tenPhongLabel);
        container.add(tenPhongInput);
        container.add(sucChuaLabel);
        container.add(sucChuaInput);
        container.add(scrollPane);
        container.add(addButton);
        container.add(deleteButton);
        container.add(updateButton);
    }

    private void setActions(String username) {
        AdminDAO adminDAO = new AdminDAO();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPhong = maPhongInput.getText();
                String tenPhong = tenPhongInput.getText();
                int sucChua = Integer.parseInt(sucChuaInput.getText());

                PhongHoc phongHoc = new PhongHoc(maPhong, tenPhong, sucChua);

                boolean success = adminDAO.insertPhongHoc(phongHoc, username);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Thêm phòng học thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm phòng học thất bại");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPhong = maPhongInput.getText();

                boolean success = adminDAO.deletePhongHoc(maPhong, username);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Xóa phòng học thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa phòng học thất bại");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPhong = maPhongInput.getText();
                String tenPhong = tenPhongInput.getText();
                int sucChua = Integer.parseInt(sucChuaInput.getText());

                PhongHoc phongHoc = new PhongHoc(maPhong, tenPhong, sucChua);

                boolean success = adminDAO.updatePhongHoc(phongHoc, username);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập nhật phòng học thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật phòng học thất bại");
                }
            }
        });
    }
     private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = adminDAO.selectAllPhongHoc();

        if (rs != null) {
            try {
                while (rs.next()) {
                    String maPhong = rs.getString("MaPh");
                    String tenPhong = rs.getString("TenPh");
                    int sucChua = rs.getInt("Succhua");
                    model.addRow(new Object[]{maPhong, tenPhong, sucChua});
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
            java.util.logging.Logger.getLogger(ClassroomFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassroomFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassroomFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassroomFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassroomFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
