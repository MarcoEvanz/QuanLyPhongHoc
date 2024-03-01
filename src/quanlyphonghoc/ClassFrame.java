/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassFrame extends JFrame {


    private JLabel maLopLabel, tenLopLabel, nienKhoaLabel, sisoLabel;
    private JTextField maLopInput, tenLopInput, sisoInput;
    private JComboBox<String> nienKhoaComboBox;
    private JButton addButton, editButton, deleteButton;
    private JTable table;
    private JScrollPane scrollPane;
    private String username;

    public ClassFrame(String username) {
        setTitle("Class Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 800, 600);

        Container container = getContentPane();
        container.setLayout(null);
        this.username = username;

        setBounds();
        addComponents(container);
        addActionListeners();
        
        populateTable();

        setVisible(true);
    }

    private void setBounds() {
        maLopLabel = new JLabel("MaLop:");
        tenLopLabel = new JLabel("TenLop:");
        nienKhoaLabel = new JLabel("NienKhoa:");
        sisoLabel = new JLabel("Siso:");
        maLopInput = new JTextField();
        tenLopInput = new JTextField();
        sisoInput = new JTextField();
        nienKhoaComboBox = new JComboBox<>(new String[]{"2020-2023", "2021-2024", "2022-2025"});
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"MaLop", "TenLop", "NienKhoa", "Siso"}, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        maLopLabel.setBounds(50, 20, 100, 30);
        maLopInput.setBounds(150, 20, 100, 30);
        tenLopLabel.setBounds(50, 70, 100, 30);
        tenLopInput.setBounds(150, 70, 100, 30);
        nienKhoaLabel.setBounds(50, 120, 100, 30);
        nienKhoaComboBox.setBounds(150, 120, 100, 30);
        sisoLabel.setBounds(50, 170, 100, 30);
        sisoInput.setBounds(150, 170, 100, 30);
        addButton.setBounds(50, 220, 80, 30);
        editButton.setBounds(150, 220, 80, 30);
        deleteButton.setBounds(250, 220, 80, 30);
        scrollPane.setBounds(50, 280, 600, 200);
    }

    private void addComponents(Container container) {
        container.add(maLopLabel);
        container.add(maLopInput);
        container.add(tenLopLabel);
        container.add(tenLopInput);
        container.add(nienKhoaLabel);
        container.add(nienKhoaComboBox);
        container.add(sisoLabel);
        container.add(sisoInput);
        container.add(addButton);
        container.add(editButton);
        container.add(deleteButton);
        container.add(scrollPane);
    }

    private void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLop = maLopInput.getText();
                String tenLop = tenLopInput.getText();
                String nienKhoa = (String) nienKhoaComboBox.getSelectedItem();
                int siso = Integer.parseInt(sisoInput.getText());

                // Create a new LopHoc instance
                LopHoc lopHoc = new LopHoc(maLop, tenLop, siso, nienKhoa);

                // Insert the LopHoc instance using UserDAO
                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.insertLopHoc(lopHoc,username);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Tạo lớp thành công");
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear the table
                    populateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLop = maLopInput.getText();
                String tenLop = tenLopInput.getText();
                String nienKhoa = (String) nienKhoaComboBox.getSelectedItem();
                int siso = Integer.parseInt(sisoInput.getText());

                // Create a new LopHoc instance
                LopHoc lopHoc = new LopHoc(maLop, tenLop, siso, nienKhoa);

                // Update the LopHoc instance using UserDAO
                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.updateLopHoc(lopHoc,username);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập nhật lớp thành công");
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear the table
                    populateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLopToDelete = maLopInput.getText();

                // Delete the LopHoc using UserDAO
                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.deleteLopHoc(maLopToDelete,username);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Xóa lớp thành công");
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear the table
                    populateTable();
                    // You can also update the table with the new data after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi");
                }
            }
        });
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        UserDAO userDAO = new UserDAO();
        ResultSet rs = userDAO.selectAllLopHoc();

        if (rs != null) {
            try {
                while (rs.next()) {
                    String maLop = rs.getString("MaLop");
                    String tenLop = rs.getString("TenLop");
                    String nienKhoa = rs.getString("NienKhoa");
                    String siso = rs.getString("Siso");

                    model.addRow(new Object[]{maLop, tenLop, nienKhoa, siso});
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
            .addGap(0, 412, Short.MAX_VALUE)
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
                new ClassFrame("test");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
