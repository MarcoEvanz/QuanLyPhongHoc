/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import java.util.Date;
public class HoaDon {
    private String MaHD, MaDC;
    private Date NgayNhap;
    private int SoLuong, ThanhTien;

    public HoaDon(String MaHD, String MaDC, int ThanhTien) {
        this.MaHD = MaHD;
        this.MaDC = MaDC;
        this.ThanhTien = ThanhTien;
    }

    public HoaDon(String MaHD, String MaDC, Date NgayNhap, int SoLuong, int ThanhTien) {
        this.MaHD = MaHD;
        this.MaDC = MaDC;
        this.NgayNhap = NgayNhap;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaDC() {
        return MaDC;
    }

    public void setMaDC(String MaDC) {
        this.MaDC = MaDC;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
