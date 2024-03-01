/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
public class DungCu {
    private String MaDc, TenDc, MaPh;
    private int Soluong;

    public DungCu(String MaDC,String TenDc, String MaPh, int Soluong) {
        this.MaDc= MaDC;
        this.TenDc = TenDc;
        this.MaPh = MaPh;
        this.Soluong = Soluong;
    }

    public String getMaDc() {
        return MaDc;
    }

    public void setMaDc(String MaDc) {
        this.MaDc = MaDc;
    }

    public String getTenDc() {
        return TenDc;
    }

    public void setTenDc(String TenDc) {
        this.TenDc = TenDc;
    }

    public String getMaPh() {
        return MaPh;
    }

    public void setMaPh(String MaPh) {
        this.MaPh = MaPh;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }
    
}
