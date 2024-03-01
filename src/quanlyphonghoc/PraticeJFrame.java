/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlyphonghoc;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class PraticeJFrame extends JFrame {

    private JLabel maPhLabel, thoiGianLabel, maLopLabel, ngayLabel;
    private JComboBox<String> maPhComboBox, thoiGianComboBox,maLopComboBox;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addButton,kiemTraButton;
    private JDateChooser ngayChooser;
    private String username;

    public PraticeJFrame(String username) {
        setTitle("Practice Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 800, 600);
        Container container = getContentPane();
        container.setLayout(null);
        this.username = username;
        table = new JTable(new DefaultTableModel(new Object[]{"MaPh", "ThoiGian", "MaLop", "Ngay"}, 0));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 750, 350);
        
        setBounds();
        addComponents(container);
        addActionListeners();
        setVisible(true);
    }

    private void setBounds() {
        maPhLabel = new JLabel("MaPh:");
        thoiGianLabel = new JLabel("ThoiGian:");
        maLopLabel = new JLabel("MaLop:");
        ngayLabel = new JLabel("Ngay:");
        ngayChooser = new JDateChooser();

        UserDAO userDAO = new UserDAO();
        maPhComboBox = new JComboBox<>(userDAO.getMaPhList());
        thoiGianComboBox = new JComboBox<>(new String[]{"Tiet 1-3", "Tiet 4-6", "Tiet 7-9", "Tiet 10-12"});
        maLopComboBox = new JComboBox<>(userDAO.getMaLopList());

        addButton = new JButton("Đăng ký lịch");


        maPhLabel.setBounds(50, 20, 100, 30);
        maPhComboBox.setBounds(150, 20, 100, 30);
        thoiGianLabel.setBounds(50, 70, 100, 30);
        thoiGianComboBox.setBounds(150, 70, 100, 30);
        maLopLabel.setBounds(50, 120, 100, 30);
        maLopComboBox.setBounds(150, 120, 100, 30);
        ngayLabel.setBounds(50, 170, 100, 30);
        ngayChooser.setBounds(150, 170, 100, 30);
        addButton.setBounds(150, 220, 200, 30);
        scrollPane.setBounds(50, 280, 600, 200);
    }

    private void addComponents(Container container) {
        container.add(maPhLabel);
        container.add(maPhComboBox);
        container.add(thoiGianLabel);
        container.add(thoiGianComboBox);
        container.add(maLopLabel);
        container.add(maLopComboBox);
        container.add(ngayLabel);
        container.add(ngayChooser);
        container.add(addButton);
        container.add(scrollPane);
    }

    private void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPh = (String) maPhComboBox.getSelectedItem();
                String thoiGian = (String) thoiGianComboBox.getSelectedItem();
                String maLop = (String) maLopComboBox.getSelectedItem();
                Date ngay = new Date(ngayChooser.getDate().getTime()); // Replace this with your Date input logic

                // Create a new LichThucHanh instance
                LichThucHanh lichThucHanh = new LichThucHanh(maPh, thoiGian, maLop, ngay);

                // Insert the LichThucHanh instance using UserDAO
                UserDAO userDAO = new UserDAO();
                int success = userDAO.DangKyTH(lichThucHanh,username);

                if (success == 1) {
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{maPh, thoiGian, maLop, ngay});
                } else {
                    JOptionPane.showMessageDialog(null, "An error occurred");
                }
            }
        });
  
       
    }
    
    private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);

    UserDAO userDAO = new UserDAO();
    ResultSet rs = userDAO.selectAllLichTH();

    if (rs != null) {
        try {
            while (rs.next()) {
                String maPh = rs.getString("MaPh");
                String thoiGian = rs.getString("ThoiGian");
                String maLop = rs.getString("MaLop");
                Date ngay = rs.getDate("Ngay");
                model.addRow(new Object[]{maPh, thoiGian, maLop, ngay});
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PraticeJFrame("test");
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
