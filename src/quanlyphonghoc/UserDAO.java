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
import java.util.ArrayList;
import java.util.Date;
public class UserDAO {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;
    
    public boolean insertLopHoc(LopHoc lopHoc, String username) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String insertQuery = "INSERT INTO `lophoc` (`MaLop`, `TenLop`, `NienKhoa`, `Siso`) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, lopHoc.getMaLop());
            ps.setString(2, lopHoc.getTenLop());
            ps.setString(3, lopHoc.getNienKhoa());
            ps.setInt(4, lopHoc.getSiso());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
                logAction(username, "Đã thêm lớp mới");
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
    public boolean updateLopHoc(LopHoc lopHoc, String username) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String updateQuery = "UPDATE `lophoc` SET `TenLop` = ?, `NienKhoa` = ?, `Siso` = ? WHERE `MaLop` = ?";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, lopHoc.getTenLop());
            ps.setString(2, lopHoc.getNienKhoa());
            ps.setInt(3, lopHoc.getSiso());
            ps.setString(4, lopHoc.getMaLop());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                logAction(username, "Đã sửa lớp" + lopHoc.getTenLop());
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
     public boolean deleteLopHoc(String maLop, String username) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String deleteQuery = "DELETE FROM `lophoc` WHERE `MaLop` = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setString(1, maLop);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
                logAction(username, "Đã sửa lớp " + maLop);
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
     public ResultSet selectAllLopHoc() {
    try {
        con = ConnectionFactory.getConnection();

        String selectQuery = "SELECT * FROM `lophoc`";
        ps = con.prepareStatement(selectQuery);
        rs = ps.executeQuery();

        return rs;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
public int DangKyTH(LichThucHanh lichThucHanh, String username) {
        int result = -1; // Default value for unsuccessful operation

        try {
            con = ConnectionFactory.getConnection();

            // Check for duplicate entry
            String checkDuplicateQuery = "SELECT * FROM `lichth` WHERE `MaPh` = ? AND `Ngay` = ? AND `Thoigian` = ?";
            ps = con.prepareStatement(checkDuplicateQuery);
            ps.setString(1, lichThucHanh.getMaPh());
            ps.setDate(2, new java.sql.Date(lichThucHanh.getNgay().getTime())); // Assuming Ngay is a java.util.Date
            ps.setString(3, lichThucHanh.getThoiGian());

            ResultSet duplicateResultSet = ps.executeQuery();
            if (duplicateResultSet.next()) {
                // Duplicate entry found
                result = -1;
            } else {
                // Insert the new entry
                String insertQuery = "INSERT INTO `lichth` (`MaPh`, `Ngay`, `Thoigian`, `MaLop`) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(insertQuery);
                ps.setString(1, lichThucHanh.getMaPh());
                ps.setDate(2, new java.sql.Date(lichThucHanh.getNgay().getTime())); // Assuming Ngay is a java.util.Date
                ps.setString(3, lichThucHanh.getThoiGian());
                ps.setString(4, lichThucHanh.getMaLop());

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    logAction(username, "Đã đăng ký phòng thực hành");
                    result = 1; // Successful insert
                }
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

        return result;
    }
public ResultSet selectAllLichTH() {
    try {
        con = ConnectionFactory.getConnection();

        String selectQuery = "SELECT * FROM `lichth`";
        ps = con.prepareStatement(selectQuery);
        rs = ps.executeQuery();

        return rs;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
  public String[] getMaPhList() {
        ArrayList<String> maPhList = new ArrayList<>();

        try {
            con = ConnectionFactory.getConnection();

            String selectQuery = "SELECT `MaPh` FROM `phonghoc`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                maPhList.add(rs.getString("MaPh"));
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

        return maPhList.toArray(new String[0]);
    }
  public String[] getMaLopList() {
         ArrayList<String> maLopList = new ArrayList<>();

        try {
            con = ConnectionFactory.getConnection();
            String query = "SELECT `MaLop` FROM `lophoc`";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                maLopList.add(rs.getString("MaLop"));
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
        return maLopList.toArray(new String[0]);
    }
public String[] getThoiGianList(String maPh, java.sql.Date ngay) {
    ArrayList<String> thoiGianList = new ArrayList<>();

    try {
        con = ConnectionFactory.getConnection();
        String query = "SELECT `ThoiGian`, `MaLop` FROM `your_table_name` WHERE `MaPh` = ? AND `Ngay` = ?";
        ps = con.prepareStatement(query);
        ps.setString(1, maPh);
        ps.setDate(2, ngay);
        rs = ps.executeQuery();

        if (rs.next()) {
            thoiGianList.add("Thoi gian: " + rs.getString("ThoiGian") + " Lop: " + rs.getString("MaLop"));
        } else {
            thoiGianList.add("Chua co lich dat");
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

    return thoiGianList.toArray(new String[0]);
}
 private void logAction(String maUser, String noidung) {
        try {
            con = ConnectionFactory.getConnection();
            String insertLogQuery = "INSERT INTO `log` (`MaUser`, `Noidung`, `ThoiGian`) VALUES (?, ?, NOW())";
            ps = con.prepareStatement(insertLogQuery);
            ps.setString(1, maUser);
            ps.setString(2, noidung);
            ps.executeUpdate();
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
    }
}



