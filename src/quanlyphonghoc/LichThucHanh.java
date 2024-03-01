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
public class LichThucHanh {
    private String MaPh, ThoiGian, MaLop;
    private Date Ngay;

    public LichThucHanh(String MaPh, String ThoiGian, String MaLop, Date Ngay) {
        this.MaPh = MaPh;
        this.ThoiGian = ThoiGian;
        this.MaLop = MaLop;
        this.Ngay = Ngay;
    }

    public LichThucHanh() {
    }

    public String getMaPh() {
        return MaPh;
    }

    public void setMaPh(String MaPh) {
        this.MaPh = MaPh;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }
    
}
