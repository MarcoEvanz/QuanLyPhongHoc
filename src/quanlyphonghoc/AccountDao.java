/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AccountDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;
    
   public String checkLogin(String username, String password) {
    String loaiTK = null;

    try {
        con = ConnectionFactory.getConnection();
        String query = "SELECT LoaiTK FROM taikhoan WHERE Username=? AND Password=?";
        ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) {
            loaiTK = rs.getString("LoaiTK");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return loaiTK;
}

    public int countTaiKhoan() {
        int count = 0;

        try {
        con = ConnectionFactory.getConnection();
        String query = "SELECT COUNT(*) AS count FROM `taikhoan`";
        ps = con.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        } catch (SQLException e) {
         e.printStackTrace();
        } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
            }
    }

        return count;
     }
   public boolean insertTaiKhoan(TaiKhoan taiKhoan) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();
        
        // Check if user already exists
        String checkQuery = "SELECT * FROM `taikhoan` WHERE `Username` = ?";
        ps = con.prepareStatement(checkQuery);
        ps.setString(1, taiKhoan.getUsername());
        ResultSet checkResultSet = ps.executeQuery();
        
        if (checkResultSet.next()) {
            // User already exists, return false
            return false;
        }
        
        // User doesn't exist, proceed with insertion
        String insertQuery = "INSERT INTO `taikhoan` (`MaUser`, `Username`, `Password`, `LoaiTK`) VALUES (?, ?, ?, ?)";
        ps = con.prepareStatement(insertQuery);
        ps.setString(1, taiKhoan.getMaUser());
        ps.setString(2, taiKhoan.getUsername());
        ps.setString(3, taiKhoan.getPassword());
        ps.setString(4, taiKhoan.getLaoiTK());

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            success = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return success;
}
}



