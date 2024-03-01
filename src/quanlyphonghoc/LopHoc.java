/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
public class LopHoc {
    private String MaLop, TenLop, NienKhoa;
    private int Siso;

    public LopHoc() {
    }

    public LopHoc(String MaLop, String TenLop, int Siso, String NienKhoa) {
        this.MaLop = MaLop;
        this.TenLop = TenLop;
        this.Siso = Siso;
        this.NienKhoa = NienKhoa;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public int getSiso() {
        return Siso;
    }

    public void setSiso(int Siso) {
        this.Siso = Siso;
    }

    public String getNienKhoa() {
        return NienKhoa;
    }

    public void setNienKhoa(String NienKhoa) {
        this.NienKhoa = NienKhoa;
    }
    
}
