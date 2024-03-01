/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AdminDAO {
    Connection con = null;
    PreparedStatement ps = null;
    int st;
    ResultSet rs = null;
    public boolean insertPhongHoc(PhongHoc phongHoc, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String insertQuery = "INSERT INTO `phonghoc` (`MaPh`, `TenPh`, `Succhua`) VALUES (?, ?, ?)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, phongHoc.getMaPhong());
            ps.setString(2, phongHoc.getTenPhong());
            ps.setInt(3, phongHoc.getSucchua());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
                logAction(maUser, "Inserted a new PhongHoc: " + phongHoc.getMaPhong());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean updatePhongHoc(PhongHoc phongHoc, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String updateQuery = "UPDATE `phonghoc` SET `TenPh` = ?, `Succhua` = ? WHERE where `MaPh` = ?  ";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, phongHoc.getTenPhong());
            ps.setInt(2, phongHoc.getSucchua());
            ps.setString(3, phongHoc.getMaPhong());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                logAction(maUser, "Updated PhongHoc: " + phongHoc.getMaPhong());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean deletePhongHoc(String maPh, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String deleteQuery = "DELETE FROM `phonghoc` WHERE `MaPh` = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setString(1, maPh);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
                logAction(maUser, "Deleted PhongHoc: " + maPh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }
     public boolean insertGiaoVien(GiaoVien giaoVien, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String insertQuery = "INSERT INTO `giaovien` (`MaGV`, `TenGV`, `NgaySinh`, `DiaChi`, `SDT`, `SoNawmCongTac`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, giaoVien.getMaGV());
            ps.setString(2, giaoVien.getTenGv());
            ps.setDate(3, new java.sql.Date(giaoVien.getNgaysinh().getTime())); // Assuming NgaySinh is a java.util.Date
            ps.setString(4, giaoVien.getDiaChi());
            ps.setString(5, giaoVien.getSDT());
            ps.setInt(6, giaoVien.getSonam());
            ps.setString(7, giaoVien.getUsername());
            ps.setString(8, giaoVien.getPassword());

            AccountDao accountDao = new AccountDao();
            int count = accountDao.countTaiKhoan();
            String Mauser = "us" + count;
            TaiKhoan taiKhoan = new TaiKhoan(Mauser, giaoVien.getUsername(), giaoVien.getPassword());
            boolean success2 = accountDao.insertTaiKhoan(taiKhoan);
            taiKhoan.setLaoiTK("us");
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
                logAction(maUser, "Inserted a new GiaoVien: " + giaoVien.getMaGV());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean updateGiaoVien(GiaoVien giaoVien, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String updateQuery = "UPDATE `giaovien` SET `TenGV` = ?, `NgaySinh` = ?, `DiaChi` = ?, `SDT` = ?, `SoNawmCongTac` = ?, `Username` = ?, `Password` = ? WHERE `MaGV` = ?";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, giaoVien.getTenGv());
            ps.setDate(2, new java.sql.Date(giaoVien.getNgaysinh().getTime())); // Assuming NgaySinh is a java.util.Date
            ps.setString(3, giaoVien.getDiaChi());
            ps.setString(4, giaoVien.getSDT());
            ps.setInt(5, giaoVien.getSonam());
            ps.setString(6, giaoVien.getUsername());
            ps.setString(7, giaoVien.getPassword());
            ps.setString(8, giaoVien.getMaGV());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                logAction(maUser, "Updated GiaoVien: " + giaoVien.getMaGV());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean deleteGiaoVien(String maGV, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String deleteQuery = "DELETE FROM `giaovien` WHERE `MaGV` = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setString(1, maGV);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
                logAction(maUser, "Deleted GiaoVien: " + maGV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }
     public boolean insertDungCu(DungCu dungCu, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String insertQuery = "INSERT INTO `dungcu` (`MaDC`,`TenDC`, `MaPh`, `SoLuong`) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, dungCu.getMaDc());
            ps.setString(2, dungCu.getTenDc());
            ps.setString(3, dungCu.getMaPh());
            ps.setInt(4, dungCu.getSoluong());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
                logAction(maUser, "Inserted a new DungCu: " + dungCu.getTenDc());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean updateDungCu(DungCu dungCu, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String updateQuery = "UPDATE `dungcu` SET `TenDC` = ?, `MaPh` = ?, `SoLuong` = ? WHERE `MaDC` = ?";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, dungCu.getTenDc());
            ps.setString(2, dungCu.getMaPh());
            ps.setInt(3, dungCu.getSoluong());
            ps.setString(4, dungCu.getMaDc());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                logAction(maUser, "Updated DungCu: " + dungCu.getTenDc());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean deleteDungCu(String maDc, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String deleteQuery = "DELETE FROM `dungcu` WHERE `MaDC` = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setString(1, maDc);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
                logAction(maUser, "Deleted DungCu: " + maDc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }
     public boolean insertHoaDon(HoaDon hoaDon, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String insertQuery = "INSERT INTO `hoadon` (`MaHD`, `NgayLap`, `MaDC`, `SoLuong`, `ThanhTien`) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, hoaDon.getMaHD());
            ps.setDate(2, new java.sql.Date(hoaDon.getNgayNhap().getTime()));
            ps.setString(3, hoaDon.getMaDC());
            ps.setInt(4, hoaDon.getSoLuong());
            ps.setInt(5, hoaDon.getThanhTien());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
                logAction(maUser, "Inserted a new HoaDon: " + hoaDon.getMaHD());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }

    public boolean updateHoaDon(HoaDon hoaDon, String maUser) {
        boolean success = false;

        try {
            con = ConnectionFactory.getConnection();

            String updateQuery = "UPDATE `hoadon` SET `MaDC` = ?, `SoLuong` = ?, `ThanhTien` = ? WHERE `MaHD` = ?";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, hoaDon.getMaDC());
            ps.setInt(2, hoaDon.getSoLuong());
            ps.setInt(3, hoaDon.getThanhTien());
            ps.setString(4, hoaDon.getMaHD());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                logAction(maUser, "Updated HoaDon: " + hoaDon.getMaHD());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ...
        }

        return success;
    }
 public ResultSet selectAllPhongHoc() {
        try {
            con = ConnectionFactory.getConnection();
            String selectQuery = "SELECT * FROM `phonghoc`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectAllGiaoVien() {
        try {
            con = ConnectionFactory.getConnection();

            String selectQuery = "SELECT * FROM `giaovien`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectAllDungCu() {
        try {
            con = ConnectionFactory.getConnection();

            String selectQuery = "SELECT * FROM `dungcu`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectAllHoaDon() {
        try {
            con = ConnectionFactory.getConnection();

            String selectQuery = "SELECT * FROM `hoadon`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet selectAllLogs() {
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            String selectQuery = "SELECT * FROM `log`";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (if needed)
        }
        return rs;
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
            // ...
        }
    }
 public String[] getMaDCList() {
    ArrayList<String> maDCList = new ArrayList<>();

    try {
        con = ConnectionFactory.getConnection();

        String selectQuery = "SELECT `MaDC` FROM `dungcu`";
        ps = con.prepareStatement(selectQuery);
        rs = ps.executeQuery();

        while (rs.next()) {
            maDCList.add(rs.getString("MaDC"));
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
    return maDCList.toArray(new String[0]);
}
}

