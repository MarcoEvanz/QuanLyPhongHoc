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

        String insertProcedure = "{CALL InsertPhongHoc(?, ?, ?, ?)}";
        ps = con.prepareCall(insertProcedure);
        ps.setString(1, phongHoc.getMaPhong());
        ps.setString(2, phongHoc.getTenPhong());
        ps.setInt(3, phongHoc.getSucchua());
        ps.setString(4, maUser);

        success = ps.executeUpdate() > 0;
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }

    return success;
}


// For updatePhongHoc
public boolean updatePhongHoc(PhongHoc phongHoc, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String updateProcedure = "{CALL UpdatePhongHoc(?, ?, ?, ?)}";
        ps = con.prepareCall(updateProcedure);
        ps.setString(1, phongHoc.getMaPhong());
        ps.setString(2, phongHoc.getTenPhong());
        ps.setInt(3, phongHoc.getSucchua());
        ps.setString(4, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}

// For deletePhongHoc
public boolean deletePhongHoc(String maPh, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String deleteProcedure = "{CALL DeletePhongHoc(?, ?)}";
        ps = con.prepareCall(deleteProcedure);
        ps.setString(1, maPh);
        ps.setString(2, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}


// For insertGiaoVien
public boolean insertGiaoVien(GiaoVien giaoVien, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String insertProcedure = "{CALL InsertGiaoVien(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        ps = con.prepareCall(insertProcedure);
        ps.setString(1, giaoVien.getMaGV());
        ps.setString(2, giaoVien.getTenGv());
        ps.setDate(3, new java.sql.Date(giaoVien.getNgaysinh().getTime())); 
        ps.setString(4, giaoVien.getDiaChi());
        ps.setString(5, giaoVien.getSDT());
        ps.setInt(6, giaoVien.getSonam());
        ps.setString(7, giaoVien.getUsername());
        ps.setString(8, giaoVien.getPassword());
        ps.setString(9, maUser);

        success = ps.executeUpdate() > 0;
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    return success;
}


  public boolean updateGiaoVien(GiaoVien giaoVien, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String updateProcedure = "{CALL UpdateGiaoVien(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        ps = con.prepareCall(updateProcedure);
        ps.setString(1, giaoVien.getMaGV());
        ps.setString(2, giaoVien.getTenGv());
        ps.setDate(3, new java.sql.Date(giaoVien.getNgaysinh().getTime())); 
        ps.setString(4, giaoVien.getDiaChi());
        ps.setString(5, giaoVien.getSDT());
        ps.setInt(6, giaoVien.getSonam());
        ps.setString(7, giaoVien.getUsername());
        ps.setString(8, giaoVien.getPassword());
        ps.setString(9, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}

// For deleteGiaoVien
public boolean deleteGiaoVien(String maGV, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String deleteProcedure = "{CALL DeleteGiaoVien(?, ?)}";
        ps = con.prepareCall(deleteProcedure);
        ps.setString(1, maGV);
        ps.setString(2, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return success;
}
public boolean insertDungCu(DungCu dungCu, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String insertProcedure = "{CALL InsertDungCu(?, ?, ?, ?, ?)}";
        ps = con.prepareCall(insertProcedure);
        ps.setString(1, dungCu.getMaDc());
        ps.setString(2, dungCu.getTenDc());
        ps.setString(3, dungCu.getMaPh());
        ps.setInt(4, dungCu.getSoluong());
        ps.setString(5, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}
public boolean updateDungCu(DungCu dungCu, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String updateProcedure = "{CALL UpdateDungCu(?, ?, ?, ?, ?)}";
        ps = con.prepareCall(updateProcedure);
        ps.setString(1, dungCu.getMaDc());
        ps.setString(2, dungCu.getTenDc());
        ps.setString(3, dungCu.getMaPh());
        ps.setInt(4, dungCu.getSoluong());
        ps.setString(5, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}

public boolean deleteDungCu(String maDc, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String deleteProcedure = "{CALL DeleteDungCu(?, ?)}";
        ps = con.prepareCall(deleteProcedure);
        ps.setString(1, maDc);
        ps.setString(2, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}

     public boolean insertHoaDon(HoaDon hoaDon, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String insertProcedure = "{CALL InsertHoaDon(?, ?, ?, ?, ?, ?)}";
        ps = con.prepareCall(insertProcedure);
        ps.setString(1, hoaDon.getMaHD());
        ps.setDate(2, new java.sql.Date(hoaDon.getNgayNhap().getTime()));
        ps.setString(3, hoaDon.getMaDC());
        ps.setInt(4, hoaDon.getSoLuong());
        ps.setInt(5, hoaDon.getThanhTien());
        ps.setString(6, maUser);

        success = ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return success;
}


public boolean updateHoaDon(HoaDon hoaDon, String maUser) {
    boolean success = false;

    try {
        con = ConnectionFactory.getConnection();

        String updateProcedure = "{CALL UpdateHoaDon(?, ?, ?, ?, ?)}";
        ps = con.prepareCall(updateProcedure);
        ps.setString(1, hoaDon.getMaHD());
        ps.setString(2, hoaDon.getMaDC());
        ps.setInt(3, hoaDon.getSoLuong());
        ps.setInt(4, hoaDon.getThanhTien());
        ps.setString(5, maUser);

        success = ps.executeUpdate() > 0;
    } 
    catch (SQLException e) {
        e.printStackTrace();
    } 

    return success;
}

public ResultSet selectAllPhongHoc() {
    try {
        con = ConnectionFactory.getConnection();
        String selectQuery = "SELECT * FROM phonghoc";
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
    } 
    return maDCList.toArray(new String[0]);
}
}

