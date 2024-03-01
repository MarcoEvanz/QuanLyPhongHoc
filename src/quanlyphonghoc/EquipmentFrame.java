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
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentFrame extends JFrame {

    private JLabel tenDcLabel, maPhLabel, soLuongLabel, maDcLabel;
    private JTextField tenDcInput, soLuongInput, maDcInput;
    private JComboBox<String> maPhComboBox;
    private JButton addButton, deleteButton, updateButton;
    private JTable table;
    private JScrollPane scrollPane;
    private String username;
    private AdminDAO adminDAO = new AdminDAO() ;

    public EquipmentFrame(String username) {
        this.username = username;
        UserDAO userdao = new UserDAO();

        setTitle("Equipment Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 800, 600);
        Container container = getContentPane();
        container.setLayout(null);

        tenDcLabel = new JLabel("Tên Dụng Cụ:");
        maPhLabel = new JLabel("Mã Phòng:");
        soLuongLabel = new JLabel("Số Lượng:");
        maDcLabel = new JLabel("Mã Dụng Cụ:");

        tenDcInput = new JTextField();
        soLuongInput = new JTextField();
        maDcInput = new JTextField();
        maPhComboBox = new JComboBox<>(userdao.getMaPhList());

        addButton = new JButton("Thêm");
        deleteButton = new JButton("Xóa");
        updateButton = new JButton("Sửa");

        table = new JTable(new DefaultTableModel(new Object[]{"MaDc", "TenDc", "MaPh", "SoLuong"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 650, 300);

        setBounds();
        addComponents(container);
        setActions();

        populateTable();

        setVisible(true);
    }

    private void setBounds() {
        tenDcLabel.setBounds(50, 20, 100, 30);
        tenDcInput.setBounds(150, 20, 150, 30);
        maPhLabel.setBounds(50, 70, 100, 30);
        maPhComboBox.setBounds(150, 70, 150, 30);
        soLuongLabel.setBounds(50, 120, 100, 30);
        soLuongInput.setBounds(150, 120, 150, 30);
        maDcLabel.setBounds(350, 20, 100, 30);
        maDcInput.setBounds(450, 20, 150, 30);

        addButton.setBounds(50, 170, 80, 30);
        deleteButton.setBounds(150, 170, 80, 30);
        updateButton.setBounds(250, 170, 80, 30);
    }

    private void addComponents(Container container) {
        container.add(tenDcLabel);
        container.add(tenDcInput);
        container.add(maPhLabel);
        container.add(maPhComboBox);
        container.add(soLuongLabel);
        container.add(soLuongInput);
        container.add(maDcLabel);
        container.add(maDcInput);

        container.add(addButton);
        container.add(deleteButton);
        container.add(updateButton);
        container.add(scrollPane);
    }

    private void setActions() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DungCu dungCu = new DungCu(maDcInput.getText(),tenDcInput.getText(), (String) maPhComboBox.getSelectedItem(),
                        Integer.parseInt(soLuongInput.getText()) );

                boolean success = adminDAO.insertDungCu(dungCu, username);
                if (success) {
                    populateTable();
                    JOptionPane.showMessageDialog(EquipmentFrame.this, "Đã thêm một dụng cụ mới");
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maDc = maDcInput.getText();

                if (maDc != null) {
                    boolean success = adminDAO.deleteDungCu(maDc, username);
                    if (success) {
                        populateTable();
                        JOptionPane.showMessageDialog(EquipmentFrame.this, "Đã xóa một dụng cụ");
                    } else {
                        JOptionPane.showMessageDialog(EquipmentFrame.this, "Không thể xóa dụng cụ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(EquipmentFrame.this, "Vui lòng chọn một dụng cụ để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maDc = maDcInput.getText();

                if (maDc != null) {
                     DungCu dungCu = new DungCu(maDc,tenDcInput.getText(), (String) maPhComboBox.getSelectedItem(),
                        Integer.parseInt(soLuongInput.getText()) );


                    boolean success = adminDAO.updateDungCu(dungCu, username);
                    if (success) {
                        populateTable();
                        JOptionPane.showMessageDialog(EquipmentFrame.this, "Đã sửa một dụng cụ");
                    } else {
                        JOptionPane.showMessageDialog(EquipmentFrame.this, "Không thể sửa dụng cụ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(EquipmentFrame.this, "Vui lòng chọn một dụng cụ để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
    ResultSet rs = adminDAO.selectAllDungCu();

    if (rs != null) {
        try {
            while (rs.next()) {
                String maDC = rs.getString("MaDC");
                String tenDC = rs.getString("TenDC");
                String maPh = rs.getString("MaPh");
                int soLuong = rs.getInt("SoLuong");

                model.addRow(new Object[]{maDC, tenDC, maPh, soLuong});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
            java.util.logging.Logger.getLogger(EquipmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EquipmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EquipmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EquipmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EquipmentFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
