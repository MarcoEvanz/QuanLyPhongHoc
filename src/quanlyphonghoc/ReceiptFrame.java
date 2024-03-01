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
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class ReceiptFrame extends JFrame {

    private JLabel maHDLabel, maDCLabel, ngayNhapLabel, soLuongLabel, thanhTienLabel;
    private JComboBox<String> maDCComboBox;
    private JDateChooser ngayNhapInput;
    private JTextField maHDInput, soLuongInput, thanhTienInput;
    private JButton addButton, updateButton;
    private JTable table;
    private JScrollPane scrollPane;
    private String username;
    

    public ReceiptFrame(String username) {
        setTitle("Receipt Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1000, 600);
        Container container = getContentPane();
        container.setLayout(null);
        AdminDAO adminDAO = new AdminDAO();

        maHDLabel = new JLabel("Mã Hóa Đơn:");
        maDCLabel = new JLabel("Mã Dung Cu:");
        ngayNhapLabel = new JLabel("Ngày Nhập:");
        soLuongLabel = new JLabel("Số Lượng:");
        thanhTienLabel = new JLabel("Thành Tiền:");

        maHDInput = new JTextField();
        maDCComboBox = new JComboBox<>(adminDAO.getMaDCList()); // Load data from AdminDAO's getMaDCList method
        ngayNhapInput = new JDateChooser();
        soLuongInput = new JTextField();
        thanhTienInput = new JTextField();

        addButton = new JButton("Thêm");
        updateButton = new JButton("Sửa");

        table = new JTable(new DefaultTableModel(new Object[]{"MaHD", "MaDC", "NgayNhap", "SoLuong", "ThanhTien"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 750, 350);

        setBounds();
        addComponents(container);
        setActions(username);

        populateTable();

        setVisible(true);
    }

    private void setBounds() {
        maHDLabel.setBounds(50, 20, 100, 30);
        maHDInput.setBounds(150, 20, 150, 30);
        maDCLabel.setBounds(50, 70, 100, 30);
        maDCComboBox.setBounds(150, 70, 150, 30);
        ngayNhapLabel.setBounds(50, 120, 100, 30);
        ngayNhapInput.setBounds(150, 120, 150, 30);
        soLuongLabel.setBounds(350, 20, 100, 30);
        soLuongInput.setBounds(450, 20, 150, 30);
        thanhTienLabel.setBounds(350, 70, 100, 30);
        thanhTienInput.setBounds(450, 70, 150, 30);

        addButton.setBounds(50, 170, 80, 30);
        updateButton.setBounds(150, 170, 80, 30);
    }

    private void addComponents(Container container) {
        container.add(maHDLabel);
        container.add(maHDInput);
        container.add(maDCLabel);
        container.add(maDCComboBox);
        container.add(ngayNhapLabel);
        container.add(ngayNhapInput);
        container.add(soLuongLabel);
        container.add(soLuongInput);
        container.add(thanhTienLabel);
        container.add(thanhTienInput);

        container.add(addButton);
        container.add(updateButton);
        container.add(scrollPane);
    }

    private void setActions(String username) {
        AdminDAO adminDAO = new AdminDAO();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD = maHDInput.getText();
                String maDC = (String) maDCComboBox.getSelectedItem();
                Date ngayNhap = ngayNhapInput.getDate();
                int soLuong = Integer.parseInt(soLuongInput.getText());
                int thanhTien = Integer.parseInt(thanhTienInput.getText());

                boolean success = adminDAO.insertHoaDon(new HoaDon(maHD, maDC, ngayNhap, soLuong, thanhTien), username);
                if (success) {
                    populateTable();
                    JOptionPane.showMessageDialog(ReceiptFrame.this, "Đã thêm 1 hóa đơn mới");
                } else {
                    JOptionPane.showMessageDialog(ReceiptFrame.this, "Không thể thêm hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String maHD = (String) table.getValueAt(selectedRow, 0);
                    String maDC = (String) maDCComboBox.getSelectedItem();
                    Date ngayNhap = ngayNhapInput.getDate();
                    int soLuong = Integer.parseInt(soLuongInput.getText());
                    int thanhTien = Integer.parseInt(thanhTienInput.getText());

                    boolean success = adminDAO.updateHoaDon(new HoaDon(maHD, maDC, ngayNhap, soLuong, thanhTien), username);
                    if (success) {
                        populateTable();
                        JOptionPane.showMessageDialog(ReceiptFrame.this, "Đã sửa 1 hóa đơn");
                    } else {
                        JOptionPane.showMessageDialog(ReceiptFrame.this, "Không thể sửa hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = adminDAO.selectAllHoaDon();

        if (rs != null) {
            try {
                while (rs.next()) {
                    String maHD = rs.getString("MaHD");
                    String maDC = rs.getString("MaDC");
                    Date ngayNhap = rs.getDate("NgayNhap");
                    int soLuong = rs.getInt("SoLuong");
                    int thanhTien = rs.getInt("ThanhTien");

                    model.addRow(new Object[]{maHD, maDC, ngayNhap, soLuong, thanhTien});
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
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceiptFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
