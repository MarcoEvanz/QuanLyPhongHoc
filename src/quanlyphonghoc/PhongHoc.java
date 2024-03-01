/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
public class PhongHoc {
    private String MaPhong, TenPhong;
    private int Succhua;

    public PhongHoc() {
    }

    public PhongHoc(String MaPhong, String TenPhong, int Succhua) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.Succhua = Succhua;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public int getSucchua() {
        return Succhua;
    }

    public void setSucchua(int Succhua) {
        this.Succhua = Succhua;
    }
    
}
