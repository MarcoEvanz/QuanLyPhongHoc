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

public class TeacherFrame extends JFrame {

    private JLabel maGVLabel, tenGVLabel, ngaySinhLabel, diaChiLabel, sdtLabel, usernameLabel, passwordLabel, soNamLabel;
    private JTextField maGVInput, tenGVInput, diaChiInput, sdtInput, usernameInput, passwordInput, soNamInput;
    private JDateChooser ngaySinhInput;
    private JButton addButton, deleteButton, updateButton;
    private JTable table;
    private JScrollPane scrollPane;
    private String username;

    public TeacherFrame(String username) {
        setTitle("Teacher Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1000, 600);
        Container container = getContentPane();
        container.setLayout(null);

        maGVLabel = new JLabel("Mã Giáo Viên:");
        tenGVLabel = new JLabel("Tên Giáo Viên:");
        ngaySinhLabel = new JLabel("Ngày Sinh:");
        diaChiLabel = new JLabel("Địa Chỉ:");
        sdtLabel = new JLabel("SĐT:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        soNamLabel = new JLabel("Số Năm Công Tác:");

        maGVInput = new JTextField();
        tenGVInput = new JTextField();
        ngaySinhInput = new JDateChooser();
        diaChiInput = new JTextField();
        sdtInput = new JTextField();
        usernameInput = new JTextField();
        passwordInput = new JTextField();
        soNamInput = new JTextField();

        addButton = new JButton("Thêm");
        deleteButton = new JButton("Xóa");
        updateButton = new JButton("Sửa");

        table = new JTable(new DefaultTableModel(new Object[]{"maGV", "tenGV", 
            "ngaySinh", "diaChi", "sdt", "soNam", "username", "password"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 750, 350);

        setBounds();
        addComponents(container, username);
        setActions(username);

        populateTable();

        setVisible(true);
    }

    private void setBounds() {
        maGVLabel.setBounds(50, 20, 100, 30);
        maGVInput.setBounds(150, 20, 150, 30);
        tenGVLabel.setBounds(50, 70, 100, 30);
        tenGVInput.setBounds(150, 70, 150, 30);
        ngaySinhLabel.setBounds(50, 120, 100, 30);
        ngaySinhInput.setBounds(150, 120, 150, 30);
        diaChiLabel.setBounds(350, 20, 100, 30);
        diaChiInput.setBounds(450, 20, 150, 30);
        sdtLabel.setBounds(350, 70, 100, 30);
        sdtInput.setBounds(450, 70, 150, 30);
        usernameLabel.setBounds(350, 120, 100, 30);
        usernameInput.setBounds(450, 120, 150, 30);
        passwordLabel.setBounds(650, 20, 100, 30);
        passwordInput.setBounds(750, 20, 150, 30);
        soNamLabel.setBounds(650, 70, 100, 30);
        soNamInput.setBounds(750, 70, 150, 30);

        addButton.setBounds(50, 170, 80, 30);
        deleteButton.setBounds(150, 170, 80, 30);
        updateButton.setBounds(250, 170, 80, 30);

    }

    private void addComponents(Container container, String username) {
        container.add(maGVLabel);
        container.add(maGVInput);
        container.add(tenGVLabel);
        container.add(tenGVInput);
        container.add(ngaySinhLabel);
        container.add(ngaySinhInput);
        container.add(diaChiLabel);
        container.add(diaChiInput);
        container.add(sdtLabel);
        container.add(sdtInput);
        container.add(usernameLabel);
        container.add(usernameInput);
        container.add(passwordLabel);
        container.add(passwordInput);
        container.add(soNamLabel);
        container.add(soNamInput);

        container.add(addButton);
        container.add(deleteButton);
        container.add(updateButton);
        container.add(scrollPane);
    }

private void setActions(String username) {
    AdminDAO teacherDAO = new AdminDAO();

    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GiaoVien giaoVien = new GiaoVien();
            giaoVien.setMaGV(maGVInput.getText());
            giaoVien.setTenGv(tenGVInput.getText());
            giaoVien.setNgaysinh(ngaySinhInput.getDate());
            giaoVien.setDiaChi(diaChiInput.getText());
            giaoVien.setSDT(sdtInput.getText());
            giaoVien.setSonam(Integer.parseInt(soNamInput.getText()));
            giaoVien.setUsername(usernameInput.getText());
            giaoVien.setPassword(passwordInput.getText());

            boolean success = teacherDAO.insertGiaoVien(giaoVien, username);
            if (success) {
                populateTable();
                JOptionPane.showMessageDialog(TeacherFrame.this, "Đã thêm 1 giáo viên mới");
            }
        }
    });

    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                String maGV = maGVInput.getText();
                boolean success = teacherDAO.deleteGiaoVien(maGV, username);
                if (success) {
                    populateTable();
                    JOptionPane.showMessageDialog(TeacherFrame.this, "Đã xóa 1 giáo viên");
                }
            }
    });

    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                GiaoVien giaoVien = new GiaoVien();
                giaoVien.setMaGV(maGVInput.getText());
                giaoVien.setTenGv(tenGVInput.getText());
                giaoVien.setNgaysinh(ngaySinhInput.getDate());
                giaoVien.setDiaChi(diaChiInput.getText());
                giaoVien.setSDT(sdtInput.getText());
                giaoVien.setSonam(Integer.parseInt(soNamInput.getText()));
                giaoVien.setUsername(usernameInput.getText());
                giaoVien.setPassword(passwordInput.getText());

                boolean success = teacherDAO.updateGiaoVien(giaoVien, username);
                if (success) {
                    populateTable();
                    JOptionPane.showMessageDialog(TeacherFrame.this, "Đã sửa 1 giáo viên");
                }
            
        }
    });
}

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        AdminDAO admindao = new AdminDAO();
        ResultSet rs = admindao.selectAllGiaoVien();
        

        if (rs != null) {
            try {
                while (rs.next()) {
                    String maGV = rs.getString("MaGV");
                    String tenGV = rs.getString("TenGV");
                    Date ngaySinh = rs.getDate("NgaySinh");
                    String diaChi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");
                    int soNam = rs.getInt("SoNawmCongTac");
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");

                    model.addRow(new Object[]{maGV, tenGV, ngaySinh, diaChi, sdt, soNam, username, password});
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
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
